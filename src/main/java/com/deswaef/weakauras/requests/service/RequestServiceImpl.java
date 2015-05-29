package com.deswaef.weakauras.requests.service;

import com.deswaef.weakauras.requests.domain.ConfigRequest;
import com.deswaef.weakauras.requests.repository.ConfigRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Quinten
 * Date: 27-5-2015
 * Time: 21:08
 *
 * @author Quinten De Swaef
 */
@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private ConfigRequestRepository configRequestRepository;

    @Override
    public Page<ConfigRequest> findAll(Pageable pageable) {
        return configRequestRepository.findAll(pageable);
    }
}
