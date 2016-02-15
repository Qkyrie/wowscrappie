package com.deswaef.heureka.warcraftlogs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WarcraftlogsProperties {

    @Value("${com.deswaef.scrappie.warcraftlogs.baseurl}")
    private String baseUrl;
    @Value("${com.deswaef.scrappie.warcraftlogs.publickey}")
    private String publicKey;

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getPublicKey() {
        return publicKey;
    }
}
