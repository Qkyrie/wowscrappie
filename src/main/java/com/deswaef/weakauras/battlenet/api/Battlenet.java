package com.deswaef.weakauras.battlenet.api;

import org.springframework.social.ApiBinding;
import org.springframework.web.client.RestOperations;

public interface Battlenet extends ApiBinding {
    UserOperations userOperations();
    RestOperations restOperations();
}
