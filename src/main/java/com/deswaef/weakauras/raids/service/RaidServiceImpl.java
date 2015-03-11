package com.deswaef.weakauras.raids.service;

import com.deswaef.weakauras.raids.domain.Raid;
import com.deswaef.weakauras.raids.repository.RaidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
