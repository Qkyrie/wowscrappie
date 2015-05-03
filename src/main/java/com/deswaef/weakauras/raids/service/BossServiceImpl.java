package com.deswaef.weakauras.raids.service;

import com.deswaef.weakauras.raids.domain.Boss;
import com.deswaef.weakauras.raids.domain.Raid;
import com.deswaef.weakauras.raids.domain.RaidBoss;
import com.deswaef.weakauras.raids.repository.BossRepository;
import com.deswaef.weakauras.raids.repository.RaidBossRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BossServiceImpl implements BossService {

    @Autowired
    private RaidBossRepository raidBossRepository;
    @Autowired
    private BossRepository bossRepository;

    @Override
    @Transactional(readOnly = true)
    public List<RaidBoss> findByRaid(Raid raid) {
        return raidBossRepository.findByRaid(raid);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RaidBoss> findById(long id) {
        return raidBossRepository.findOne(id);
    }

    @Override
    public Optional<Boss> findByWarcraftlogsEncounterId(Long warcraftlogsEncounterId) {
        return bossRepository.findByWarcraftlogsEncounterId(warcraftlogsEncounterId);
    }
}
