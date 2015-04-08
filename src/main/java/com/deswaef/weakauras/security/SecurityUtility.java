package com.deswaef.weakauras.security;

import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SecurityUtility {

    public Optional<ScrappieUser> currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null && authentication.getPrincipal() instanceof ScrappieUser) {
            return Optional.of((ScrappieUser)authentication.getPrincipal());
        } else {
            return Optional.empty();
        }
    }

}
