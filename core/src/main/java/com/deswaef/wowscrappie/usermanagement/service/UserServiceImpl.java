package com.deswaef.wowscrappie.usermanagement.service;

import com.deswaef.wowscrappie.ui.macros.domain.MacroConfigRating;
import com.deswaef.wowscrappie.ui.macros.service.MacroService;
import com.deswaef.wowscrappie.ui.rating.service.ConfigRatingService;
import com.deswaef.wowscrappie.ui.tellmewhen.domain.TellMeWhenConfigRating;
import com.deswaef.wowscrappie.ui.tellmewhen.service.TellMeWhenService;
import com.deswaef.wowscrappie.ui.weakauras.domain.WeakAuraConfigRating;
import com.deswaef.wowscrappie.ui.weakauras.service.WeakAuraService;
import com.deswaef.wowscrappie.usermanagement.domain.Role;
import com.deswaef.wowscrappie.usermanagement.domain.RoleEnum;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import com.deswaef.wowscrappie.usermanagement.repository.RoleRepository;
import com.deswaef.wowscrappie.usermanagement.repository.UserRepository;
import com.deswaef.wowscrappie.usermanagement.service.dto.CreateInvitationDto;
import com.deswaef.wowscrappie.usermanagement.service.dto.RegistrationByInvitationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MacroService macroService;
    @Autowired
    private WeakAuraService weakAuraService;
    @Autowired
    private TellMeWhenService tellMeWhenService;
    @Autowired
    private ConfigRatingService configRatingService;


    private PasswordEncoder standardPasswordEncoder;

    @PostConstruct
    public void init() {
        this.standardPasswordEncoder = new StandardPasswordEncoder();
    }

    @Override
    public Optional<ScrappieUser> findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<ScrappieUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ScrappieUser> findByFacebookId(String facebookId) {
        return userRepository.findByFacebookId(facebookId);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "amountscache", key = "'userCount'")
    public Long count() {
        return userRepository.count();
    }

    @Override
    @Cacheable(value = "amountscache", key = "'userScore-' + #scrappieUser.id")
    public long calculateUserScore(ScrappieUser scrappieUser) {
        long waCount = weakAuraService.findAllFromUser(scrappieUser)
                .stream()
                .mapToLong(x -> {
                    Optional<WeakAuraConfigRating> byWeakAura = configRatingService.findByWeakAura(x.getId());
                    if (byWeakAura.isPresent()) {
                        return byWeakAura.get().calculateEffectiveRating();
                    } else {
                        return 0;
                    }
                }).sum();
        long tmwCount = tellMeWhenService.findAllFromUser(scrappieUser)
                .stream()
                .mapToLong(x -> {
                    Optional<TellMeWhenConfigRating> byTellMeWhen = configRatingService.findByTellMeWhen(x.getId());
                    if (byTellMeWhen.isPresent()) {
                        return byTellMeWhen.get().calculateEffectiveRating();
                    } else {
                        return 0;
                    }
                }).sum();
        long macroCount = macroService.findAllFromUser(scrappieUser)
                .stream()
                .mapToLong(x -> {
                    Optional<MacroConfigRating> byMacro = configRatingService.findByMacro(x.getId());
                    if (byMacro.isPresent()) {
                        return byMacro.get().calculateEffectiveRating();
                    } else {
                        return 0;
                    }
                }).sum();
        return waCount + tmwCount + macroCount;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ScrappieUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<ScrappieUser> findByInvitationCode(String invitationcode) {
        return userRepository.findByActivationCode(invitationcode);
    }

    @Override
    public ScrappieUser createInvitation(CreateInvitationDto createInvitationDto) {
        Optional<ScrappieUser> byUsername = userRepository.findByUsername(createInvitationDto.getEmail());
        if (byUsername.isPresent()) {
            throw new IllegalArgumentException("A user with that email address already exists");
        } else {
            try {
                String activationCode = encode(String.valueOf(System.currentTimeMillis()) + getRandomlyGeneratedUUID()).substring(0, 39);
                return userRepository.save(new ScrappieUser()
                        .setEmail(createInvitationDto.getEmail())
                        .setEnabled(true)
                        .setActivationCode(activationCode)
                        .setAuthorities(getDefaultAuthorities())
                        .setUsername(String.format("tbd%s", activationCode))
                        .setGeneratedUsername(true)
                );
            } catch (Exception ex) {
                throw new IllegalArgumentException("Something went wrong, and we don't even know what :(");
            }

        }
    }

    private String getRandomlyGeneratedUUID() {
        return UUID.randomUUID().toString();
    }

    private String encode(String toEncode) {
        return standardPasswordEncoder.encode(toEncode);
    }

    @Override
    public ScrappieUser acceptInvitation(RegistrationByInvitationDto registrationByInvitationDto) {
        if (userRepository.findByUsername(registrationByInvitationDto.getNewName()).isPresent()) {
            throw new IllegalArgumentException("A user with that username is already registered");
        }
        Optional<ScrappieUser> byEmail = userRepository.findByEmail(registrationByInvitationDto.getEmail());
        if (byEmail.isPresent()) {
            try {
                return userRepository.save(
                        byEmail.get()
                                .setPassword(standardPasswordEncoder.encode(registrationByInvitationDto.getPassword()))
                                .setUsername(registrationByInvitationDto.getNewName())
                                .setActivationCode("")
                                .setEnabled(true)
                );
            } catch (Exception ex) {
                throw new IllegalArgumentException("Something went wrong, and we have no idea what :(");
            }
        } else {
            throw new IllegalArgumentException("A user with that email was not found");
        }
    }

    @Override
    @Transactional
    public void changeUsername(ScrappieUser principal, String newName) {
        Optional<ScrappieUser> byId = findById(principal.getId());
        if (byId.isPresent()) {
            try {
                ScrappieUser scrappieUser = byId.get();
                if (newName != null && !newName.equals("")) {
                    scrappieUser.setUsername(newName);
                }
                scrappieUser.setGeneratedUsername(false);
                userRepository.save(scrappieUser);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Could not change username");
            }
        } else {
            throw new IllegalArgumentException("Principal was not found");
        }
    }

    @Override
    @Transactional
    public void setEnabled(Long id, boolean enabled) {
        Optional<ScrappieUser> byId = findById(id);
        if (byId.isPresent()) {
            userRepository.save(byId.get().setEnabled(enabled));
        } else {
            throw new IllegalArgumentException("User with that id was not found");
        }
    }

    private Set<Role> getDefaultAuthorities() {
        Set<Role> defaultRoles = new HashSet<>();
        defaultRoles.add(roleRepository.findOne(RoleEnum.USER_ROLE.getId()).get());
        return defaultRoles;
    }
}
