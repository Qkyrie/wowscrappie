package com.deswaef.weakauras.usermanagement.service;

import com.deswaef.weakauras.usermanagement.controller.admin.dto.CreateInvitationDto;
import com.deswaef.weakauras.usermanagement.controller.dto.RegistrationByInvitationDto;
import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import com.deswaef.weakauras.usermanagement.util.FacebookUser;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<ScrappieUser> findById(Long id);
    List<ScrappieUser> findAll();
    Optional<ScrappieUser> findByFacebookId(String facebookId);
    ScrappieUser createNewFacebookuser(FacebookUser facebookUser);
    Long count();
    Optional<ScrappieUser> findByUsername(String username);
    Optional<ScrappieUser> findByInvitationCode(String invitationcode);
    ScrappieUser createInvitation(CreateInvitationDto createInvitationDto);

    ScrappieUser acceptInvitation(RegistrationByInvitationDto registrationByInvitationDto);

    void changeUsername(ScrappieUser principal, String newName);

    void setEnabled(Long id, boolean b);

    long calculateUserScore(ScrappieUser scrappieUser);
}
