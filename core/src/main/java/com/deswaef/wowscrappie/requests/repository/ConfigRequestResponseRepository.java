package com.deswaef.wowscrappie.requests.repository;

import com.deswaef.wowscrappie.infrastructure.repository.JpaRepository;
import com.deswaef.wowscrappie.requests.domain.ConfigRequest;
import com.deswaef.wowscrappie.requests.domain.ConfigRequestResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConfigRequestResponseRepository extends JpaRepository<ConfigRequestResponse, Long>{
    List<ConfigRequestResponse> findByConfigRequest(@Param("configRequest") ConfigRequest configRequest);
    List<ConfigRequestResponse> findByInResponseTo(@Param("configRequest") ConfigRequest configRequest);
}
