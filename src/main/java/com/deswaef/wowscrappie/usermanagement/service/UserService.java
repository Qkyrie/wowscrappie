package com.deswaef.wowscrappie.usermanagement.service;

import com.deswaef.wowscrappie.usermanagement.controller.admin.dto.CreateInvitationDto;
import com.deswaef.wowscrappie.usermanagement.controller.dto.RegistrationByInvitationDto;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<ScrappieUser> findById(Long id);

    List<ScrappieUser> findAll();

    Optional<ScrappieUser> findByFacebookId(String facebookId);

    Long count();

    Optional<ScrappieUser> findByUsername(String username);

    Optional<ScrappieUser> findByInvitationCode(String invitationcode);

    ScrappieUser createInvitation(CreateInvitationDto createInvitationDto);

    ScrappieUser acceptInvitation(RegistrationByInvitationDto registrationByInvitationDto);

    void changeUsername(ScrappieUser principal, String newName);

    void setEnabled(Long id, boolean b);

    long calculateUserScore(ScrappieUser scrappieUser);
}
