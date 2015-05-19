package com.deswaef.weakauras.ui.rating.service;

import com.deswaef.weakauras.ui.macros.domain.Macro;
import com.deswaef.weakauras.ui.macros.domain.MacroConfigRating;
import com.deswaef.weakauras.ui.macros.repository.MacroConfigRatingRepository;
import com.deswaef.weakauras.ui.macros.repository.MacroRepository;
import com.deswaef.weakauras.ui.rating.domain.Rating;
import com.deswaef.weakauras.ui.rating.repository.ConfigRatingRepository;
import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhen;
import com.deswaef.weakauras.ui.tellmewhen.domain.TellMeWhenConfigRating;
import com.deswaef.weakauras.ui.tellmewhen.repository.TellMeWhenConfigRatingRepository;
import com.deswaef.weakauras.ui.tellmewhen.repository.TellMeWhenRepository;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAura;
import com.deswaef.weakauras.ui.weakauras.domain.WeakAuraConfigRating;
import com.deswaef.weakauras.ui.weakauras.repository.WeakAuraConfigRatingRepository;
import com.deswaef.weakauras.ui.weakauras.repository.WeakAuraRepository;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ConfigRatingServiceImpl implements ConfigRatingService {

    @Autowired
    private ConfigRatingRepository configRatingRepository;
    @Autowired
    private WeakAuraConfigRatingRepository weakAuraConfigRatingRepository;
    @Autowired
    private TellMeWhenConfigRatingRepository tellMeWhenConfigRatingRepository;
    @Autowired
    private MacroConfigRatingRepository macroConfigRatingRepository;
    @Autowired
    private TellMeWhenRepository tellMeWhenRepository;
    @Autowired
    private WeakAuraRepository weakAuraRepository;
    @Autowired
    private MacroRepository macroRepository;

    @Override
    @Transactional
    public void createTMWRating(TellMeWhen tellMeWhen) {
        if(!tellMeWhenConfigRatingRepository.findByTellMeWhen(tellMeWhen).isPresent()) {
            configRatingRepository.save(
                    new TellMeWhenConfigRating()
                            .setTellMeWhen(tellMeWhen));
        }
    }

    @Override
    @Transactional
    public void createWARating(WeakAura weakAura) {
        if(!weakAuraConfigRatingRepository.findByWeakAura(weakAura).isPresent()){
            configRatingRepository.save(
                    new WeakAuraConfigRating()
                            .setWeakAura(weakAura)
            );
        }
    }


    @Override
    @Transactional
    public void createMacroRating(Macro macro) {
        if (!macroConfigRatingRepository.findByMacro(macro).isPresent()) {
            configRatingRepository.save(
                    new MacroConfigRating()
                            .setMacro(macro)
            );
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TellMeWhenConfigRating> findByTellMeWhen(Long tellMeWhen) {
        Optional<TellMeWhen> tmw = tellMeWhenRepository.findOne(tellMeWhen);
        if(!tmw.isPresent()) {
            return Optional.empty();
        } else {
            return tellMeWhenConfigRatingRepository.findByTellMeWhen(tmw.get());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MacroConfigRating> findByMacro(Long macro) {
        Optional<Macro> mc = macroRepository.findOne(macro);
        if(!mc.isPresent()) {
            return Optional.empty();
        } else {
            return macroConfigRatingRepository.findByMacro(mc.get());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WeakAuraConfigRating> findByWeakAura(Long weakaura) {
        Optional<WeakAura> wa = weakAuraRepository.findOne(weakaura);
        if (!wa.isPresent()) {
            return Optional.empty();
        } else {
            return weakAuraConfigRatingRepository.findByWeakAura(wa.get());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Rating getPersonalRatingWA(Long weakAura, ScrappieUser user) {
       return Rating.NONE;
    }

    @Override
    @Transactional(readOnly = true)
    public Rating getPersonalRatingTMW(Long tmw, ScrappieUser user) {
        return Rating.NONE;

    }

    @Override
    @Transactional(readOnly = true)
    public Rating getPersonalRatingMacro(Long macro, ScrappieUser user) {
        return Rating.NONE;
    }

    @Override
    @Transactional
    public void voteTMW(Long tmw, Rating rating, ScrappieUser user) {
       //todo: implement
    }

    @Override
    @Transactional
    public void voteWA(Long wa, Rating rating, ScrappieUser user) {
       //todo: implement
    }

    @Override
    @Transactional
    public void voteMacro(Long macro, Rating rating, ScrappieUser user) {
        //todo: implement
    }

    @Override
    @Transactional
    public void deleteWeakAuraConfigRating(Long weakAura) {
        Optional<WeakAuraConfigRating> byWeakAura = findByWeakAura(weakAura);
        if (byWeakAura.isPresent()) {
            weakAuraConfigRatingRepository.delete(byWeakAura.get());
        }
    }

    @Override
    @Transactional
    public void deleteTellMeWhenConfigRating(Long tellMeWhen) {
        Optional<TellMeWhenConfigRating> byTMW = findByTellMeWhen(tellMeWhen);
        if (byTMW.isPresent()) {
            tellMeWhenConfigRatingRepository.delete(byTMW.get());
        }
    }

    @Override
    @Transactional
    public void deleteMacroConfigRating(Long macro) {
        Optional<MacroConfigRating> byMacro = findByMacro(macro);
        if (byMacro.isPresent()) {
            macroConfigRatingRepository.delete(byMacro.get());
        }
    }
}
