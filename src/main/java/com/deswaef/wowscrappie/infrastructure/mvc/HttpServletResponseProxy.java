package com.deswaef.wowscrappie.infrastructure.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
public class HttpServletResponseProxy {
    @Autowired(required = false)
    private HttpServletResponse response;

    public HttpServletResponse getResponse() {
        return response;
    }
}
