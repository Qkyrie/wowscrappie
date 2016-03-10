package com.deswaef.wowscrappie.importingservice.warcraftlogs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WarcraftlogsAPIClient {

    private Log logger = LogFactory.getLog(WarcraftlogsAPIClient.class);

    @Autowired
    private WarcraftlogsProperties warcraftlogsProperties;

    public String getFromAPI(String apiUrl) throws WarcraftlogsAPIException {

        RestTemplate template = new RestTemplate();
        String formattedUrl = String.format("%s%s?api_key=%s", warcraftlogsProperties.getBaseUrl(), apiUrl, warcraftlogsProperties.getPublicKey());
        try {
            return template.getForObject(formattedUrl, String.class);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new WarcraftlogsAPIException(String.format("Error during the fetch of %s", formattedUrl), ex);
        }
    }

}
