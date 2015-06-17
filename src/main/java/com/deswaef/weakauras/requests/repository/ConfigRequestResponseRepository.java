package com.deswaef.weakauras.requests.repository;

import com.deswaef.weakauras.infrastructure.repository.JpaRepository;
import com.deswaef.weakauras.requests.domain.ConfigRequest;
import com.deswaef.weakauras.requests.domain.ConfigRequestResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConfigRequestResponseRepository extends JpaRepository<ConfigRequestResponse, Long>{
    List<ConfigRequestResponse> findByConfigRequest(@Param("configRequest") ConfigRequest configRequest);
    List<ConfigRequestResponse> findByInResponseTo(@Param("configRequest") ConfigRequest configRequest);
}
