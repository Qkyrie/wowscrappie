package com.deswaef.weakauras.usermanagement.service;

import com.deswaef.weakauras.usermanagement.controller.dto.RegistrationDto;
import com.deswaef.weakauras.usermanagement.domain.InvitationRequest;

import java.util.List;

public interface InvitationRequestService {
    List<InvitationRequest> findAll();

    RegistrationDto create(RegistrationDto registrationDto);

    void delete(Long id);
}
