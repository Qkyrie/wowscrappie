package com.deswaef.weakauras.usermanagement.service;

import com.deswaef.weakauras.notifications.domain.Notification;
import com.deswaef.weakauras.notifications.service.NotificationService;
import com.deswaef.weakauras.usermanagement.controller.dto.RequestInvitationDto;
import com.deswaef.weakauras.usermanagement.domain.InvitationRequest;
import com.deswaef.weakauras.usermanagement.repository.InvitationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private NotificationService notificationService;

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

                notificationService.putOnAdminQueue(Notification.create(String.format("%s has requested an invitation!", requestInvitationDto.getEmail())));
                return requestInvitationDto;
            } catch (Exception ex) {
                return requestInvitationDto
                        .setHasErrors(true)
                        .setErrorMessage("Unable to create request, please try again later");
            }
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<InvitationRequest> one = invitationRequestRepository.findOne(id);
        if (one.isPresent()) {
            try {
                invitationRequestRepository.delete(id);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Couldn't delete it");
            }
        } else {
            throw new IllegalArgumentException("That id wasnt found");
        }
    }
}
