package com.deswaef.wowscrappie.requests.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.requests.domain.ConfigRequest;

/**
 * User: Quinten
 * Date: 27-5-2015
 * Time: 21:18
 *
 * @author Quinten De Swaef
 */
public interface ConfigRequestRepository extends JpaRepository<ConfigRequest, Long> {

}
