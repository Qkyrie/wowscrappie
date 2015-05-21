package com.deswaef.weakauras.usermanagement.service;

import com.deswaef.weakauras.ui.macros.domain.MacroConfigRating;
import com.deswaef.weakauras.ui.macros.service.MacroService;
import com.deswaef.weakauras.ui.rating.service.ConfigRatingService;
import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhenConfigRating;
import com.deswaef.weakauras.ui.tellmewhen.service.TellMeWhenService;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAuraConfigRating;
import com.deswaef.weakauras.ui.weakauras.service.WeakAuraService;
import com.deswaef.weakauras.usermanagement.controller.admin.dto.CreateInvitationDto;
import com.deswaef.weakauras.usermanagement.controller.dto.RegistrationByInvitationDto;
import com.deswaef.weakauras.usermanagement.domain.Role;
import com.deswaef.weakauras.usermanagement.domain.RoleEnum;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import com.deswaef.weakauras.usermanagement.repository.RoleRepository;
import com.deswaef.weakauras.usermanagement.repository.UserRepository;
import com.deswaef.weakauras.usermanagement.util.FacebookUser;
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
    public ScrappieUser createNewFacebookuser(FacebookUser facebookUser) {
        ScrappieUser user = new ScrappieUser()
                .setAuthorities(getDefaultAuthorities())
                .setEnabled(true)
                .setFacebookId(facebookUser.getFacebookId())
                .setUsername(facebookUser.getUsername())
                .setEmail(facebookUser.getEmail())
                .setGeneratedUsername(true);
        return userRepository.save(user);
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
                return userRepository.save(new ScrappieUser()
                                .setEmail(createInvitationDto.getEmail())
                                .setEnabled(true)
                                .setActivationCode(standardPasswordEncoder.encode(String.valueOf(System.currentTimeMillis())).substring(0, 39))
                                .setAuthorities(getDefaultAuthorities())
                                .setUsername(String.format("tbd%d", System.currentTimeMillis()))
                                .setGeneratedUsername(true)
                );
            } catch (Exception ex) {
                throw new IllegalArgumentException("Something went wrong, and we don't even know what :(");
            }

        }
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
