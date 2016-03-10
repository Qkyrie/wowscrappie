package com.deswaef.wowscrappie.importingservice.wowhead.client.resource;


import com.deswaef.wowscrappie.importingservice.infrastructure.exception.HeurekaException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WowheadResource {

    private Log logger = LogFactory.getLog(this.getClass());

    @Value("${wowhead.api.baseurl}")
    private String wowheadBaseUrl;

    public String getFromWowheadAPI(String apiUrl) {
        RestTemplate template = new RestTemplate();
        String result;
        try {
            result = template.getForObject(String.format("%s%s", wowheadBaseUrl, apiUrl), String.class);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new HeurekaException(String.format("Error during the fetch of %s%s", wowheadBaseUrl, apiUrl), ex);
        }
        return result;
    }

}