package com.deswaef.weakauras.usermanagement.service;

import com.deswaef.weakauras.usermanagement.controller.dto.RequestInvitationDto;
import com.deswaef.weakauras.usermanagement.domain.InvitationRequest;
import com.deswaef.weakauras.usermanagement.repository.InvitationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvitationRequestServiceImpl implements InvitationRequestService {

    @Autowired
    private InvitationRequestRepository invitationRequestRepository;

    @Override
    public List<InvitationRequest> findAll() {
        return invitationRequestRepository.findAll();
    }

    @Override
    public RequestInvitationDto create(RequestInvitationDto requestInvitationDto) {

        Optional<InvitationRequest> byEmail = invitationRequestRepository.findByEmail(requestInvitationDto.getEmail());
        if (byEmail.isPresent()) {
            return requestInvitationDto
                    .setHasErrors(true)
                    .setErrorMessage("An invitation request for that email is already pending!");
        } else {
            try {
                invitationRequestRepository.save(
                        new InvitationRequest()
                                .setEmail(requestInvitationDto.getEmail())
                                .setReason(requestInvitationDto.getReason())
                );
                return requestInvitationDto;
            } catch (Exception ex) {
                return requestInvitationDto
                        .setHasErrors(true)
                        .setErrorMessage("Unable to create request, please try again later");
            }
        }
    }
}
