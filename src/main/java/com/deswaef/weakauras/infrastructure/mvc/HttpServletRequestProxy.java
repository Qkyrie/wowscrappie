package com.deswaef.weakauras.infrastructure.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * User: Quinten
 * Date: 5-8-2014
 * Time: 22:36
 *
 * @author Quinten De Swaef
 */
@Component
public class HttpServletRequestProxy {

    @Autowired(required = false)
    private HttpServletRequest httpServletRequest;

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }
}
