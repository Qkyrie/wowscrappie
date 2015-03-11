package com.deswaef.weakauras.usermanagement.util;

import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import com.deswaef.weakauras.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthenticationSecurityInjectorImpl implements AuthenticationSecurityInjector {

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public void injectFacebookId(FacebookUser facebookUser) {
        Optional<ScrappieUser> byFacebookId = userService.findByFacebookId(facebookUser.getFacebookId());
        if (byFacebookId.isPresent()) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(byFacebookId.get(), null, byFacebookId.get().getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            ScrappieUser newFacebookuser = userService.createNewFacebookuser(facebookUser);
            Authentication authentication = new UsernamePasswordAuthenticationToken(newFacebookuser.getFacebookId(), null, newFacebookuser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}
