package com.deswaef.wowscrappie.usermanagement.service;

import com.deswaef.wowscrappie.infrastructure.service.MailService;
import com.deswaef.wowscrappie.notifications.domain.Notification;
import com.deswaef.wowscrappie.notifications.service.NotificationService;
import com.deswaef.wowscrappie.usermanagement.controller.admin.dto.CreateInvitationDto;
import com.deswaef.wowscrappie.usermanagement.controller.dto.RegistrationDto;
import com.deswaef.wowscrappie.usermanagement.domain.InvitationRequest;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import com.deswaef.wowscrappie.usermanagement.repository.InvitationRequestRepository;
import com.deswaef.wowscrappie.usermanagement.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;
    @Value("${com.deswaef.scrappie.mailstore}")
    private String mailStore;
    @Value("${com.deswaef.scrappie.fullbaseurl}")
    private String fullBaseUrl;
    @Autowired
    private UserRepository userRepository;

    @Override
    public RegistrationDto create(RegistrationDto registrationDto) {

        Optional<ScrappieUser> byEmail = userRepository.findByEmail(registrationDto.getEmail());
        if (byEmail.isPresent()) {
            try {
                if(!StringUtils.isEmpty(byEmail.get().getActivationCode())) {
                    mailInvitationcode(byEmail.get());
                    return registrationDto
                            .setHasErrors(true)
                            .setErrorMessage("An invitation request for that email is already pending, we've resent you your activationcode!");
                } else {
                    return registrationDto
                            .setHasErrors(true)
                            .setErrorMessage("A user with that account already exists!");
                }
            } catch (Exception ex) {
                return registrationDto
                        .setHasErrors(true)
                        .setErrorMessage("Unable to create request, please try again later");
            }
        } else {
            try {
                 ScrappieUser invitation = userService.createInvitation(new CreateInvitationDto()
                        .setEmail(registrationDto.getEmail()));
                mailInvitationcode(invitation);
                notificationService.putOnAdminQueue(Notification.create(String.format("%s has registered!", registrationDto.getEmail())));
                return registrationDto;
            } catch (Exception ex) {
                return registrationDto
                        .setHasErrors(true)
                        .setErrorMessage("Unable to create request, please try again later");
            }
        }
    }

    private void mailInvitationcode(ScrappieUser invitation) throws IOException {
        mailService
                .createMail()
                .htmlBody(readFromEmails("testemail.html")
                                .replace("FULL_BASE_URL", fullBaseUrl)
                                .replace("REGISTRATION_CODE", invitation.getActivationCode())
                )
                .to(invitation.getEmail())
                .subject("WowScrappie - Activate your Registration")
                .send();
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

    public static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }


    public String readFromEmails(String s) throws IOException {
        File htmlFile = new File(String.format("%s%s%s", mailStore, File.separator, s));
        return readFile(URLDecoder.decode(htmlFile.getPath()), Charset.defaultCharset());
    }
}
