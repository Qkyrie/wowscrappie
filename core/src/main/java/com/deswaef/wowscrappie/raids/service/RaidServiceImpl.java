package com.deswaef.wowscrappie.raids.service;

import com.deswaef.wowscrappie.raids.domain.Raid;
import com.deswaef.wowscrappie.raids.repository.RaidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RaidServiceImpl implements RaidService {

    @Autowired
    private RaidRepository raidRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Raid> findById(Long id) {
        return raidRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Raid> findAll() {
        return raidRepository.findAll();
    }

}
