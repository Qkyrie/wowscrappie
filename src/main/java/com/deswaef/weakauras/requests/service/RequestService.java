package com.deswaef.weakauras.requests.service;

import com.deswaef.weakauras.requests.domain.ConfigRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * User: Quinten
 * Date: 27-5-2015
 * Time: 21:05
 *
 * @author Quinten De Swaef
 */
public interface RequestService {
    Page<ConfigRequest> findAll(Pageable pageable);
}
