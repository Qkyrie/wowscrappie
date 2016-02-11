package com.deswaef.wowscrappie.usermanagement.service;

import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import com.deswaef.wowscrappie.usermanagement.repository.RoleRepository;
import com.deswaef.wowscrappie.usermanagement.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    public static final long ADMIN_ID = 1L;
    public static final long UNKNOWN_USER_ID = -999L;
    public static final String EXISTING_FACEBOOK_ID = "existing_facebook_id";
    public static final String UNEXISTING_FACEBOOK_ID = "unexisting_facebook_id";
    public static final String ACTIVATION_CODE = "activation_code";
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;

    @Mock
    private ScrappieUser admin;


    @Test
    public void findByIdAndExists() {
        when(userRepository.findOne(ADMIN_ID))
                .thenReturn(Optional.of(admin));
        assertThat(userService.findById(ADMIN_ID).get())
                .isEqualTo(admin);
    }

    @Test
    public void findByIdAndDoesntExist() {
        when(userRepository.findOne(UNKNOWN_USER_ID))
                .thenReturn(Optional.empty());
        assertThat(userService.findById(UNKNOWN_USER_ID).isPresent())
                .isFalse();
    }

    @Test
    public void findByFacebookId() {
        when(userRepository.findByFacebookId(EXISTING_FACEBOOK_ID))
                .thenReturn(Optional.of(admin));
        assertThat(userService.findByFacebookId(EXISTING_FACEBOOK_ID).get())
                .isEqualTo(admin);
    }

    @Test
    public void findByFacebookIdAndDoesntExist() {
        when(userRepository.findByFacebookId(UNEXISTING_FACEBOOK_ID))
                .thenReturn(Optional.empty());
        assertThat(userService.findByFacebookId(UNEXISTING_FACEBOOK_ID).isPresent())
                .isFalse();
    }

    @Test
    public void testCount() {
        long countValue = 90L;
        when(userRepository.count())
                .thenReturn(countValue);
        assertThat(userService.count())
                .isEqualTo(countValue);
    }

    @Test
    public void findByInvitationCodeAndExists() {
        when(userRepository.findByActivationCode(ACTIVATION_CODE))
                .thenReturn(Optional.of(admin));
        assertThat(userService.findByInvitationCode(ACTIVATION_CODE).get())
                .isEqualTo(admin);
    }

    @Test
    public void findByInvitationCodeAndDoesntExist() {
        when(userRepository.findByActivationCode(ACTIVATION_CODE))
                .thenReturn(Optional.empty());
        assertThat(userService.findByInvitationCode(ACTIVATION_CODE).isPresent())
                .isFalse();
    }
}