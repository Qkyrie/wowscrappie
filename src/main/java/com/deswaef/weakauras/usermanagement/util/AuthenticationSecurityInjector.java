package com.deswaef.weakauras.usermanagement.util;

import org.springframework.transaction.annotation.Transactional;

public interface AuthenticationSecurityInjector {
    void injectFacebookId(FacebookUser facebookUser);
}
