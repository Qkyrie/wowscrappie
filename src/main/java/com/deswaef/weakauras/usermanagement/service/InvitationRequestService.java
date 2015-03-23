package com.deswaef.weakauras.usermanagement.service;

import com.deswaef.weakauras.usermanagement.controller.dto.RequestInvitationDto;
import com.deswaef.weakauras.usermanagement.domain.InvitationRequest;

import java.util.List;

public interface InvitationRequestService {
    List<InvitationRequest> findAll();

    RequestInvitationDto create(RequestInvitationDto requestInvitationDto);
}
