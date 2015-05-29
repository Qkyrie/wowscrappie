package com.deswaef.weakauras.requests.repository;

import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.requests.domain.ConfigRequest;

/**
 * User: Quinten
 * Date: 27-5-2015
 * Time: 21:18
 *
 * @author Quinten De Swaef
 */
public interface ConfigRequestRepository extends JpaRepository<ConfigRequest, Long> {

}
