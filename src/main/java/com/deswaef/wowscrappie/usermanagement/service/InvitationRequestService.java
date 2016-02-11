package com.deswaef.wowscrappie.usermanagement.service;

import com.deswaef.wowscrappie.usermanagement.controller.dto.RegistrationDto;
import com.deswaef.wowscrappie.usermanagement.domain.InvitationRequest;

import java.util.List;

public interface InvitationRequestService {
    List<InvitationRequest> findAll();

    RegistrationDto create(RegistrationDto registrationDto);

    void delete(Long id);
}
