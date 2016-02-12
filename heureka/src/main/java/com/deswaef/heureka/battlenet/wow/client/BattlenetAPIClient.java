package com.deswaef.heureka.battlenet.wow.client;

import com.deswaef.heureka.infrastructure.exception.HeurekaException;
import com.deswaef.wowscrappie.realm.domain.Locality;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

@Component
public class BattlenetAPIClient {

    @Value("${battlenet.api.client.baseurl.eu}")
    private String euRoot;
    @Value("${battlenet.api.client.baseurl.us}")
    private String usRoot;
    @Value("${battlenet.api.client.appId}")
    private String apikey;

    public String getFromUrl(String url) {
        RestTemplate template = new RestTemplate();
        String result;
        try {
            URL targetUrl = new URL(url);
            result = IOUtils.toString(targetUrl.toURI());
        } catch (Exception ex) {
            throw new HeurekaException(String.format("Error during the fetch of %s", url), ex);
        }
        return result;
    }

    public String getFromWowAPI(String apiUrl, Locality locality) {
        String baseUrl;
        if (locality.equals(Locality.EU)) {
            baseUrl = euRoot;
        } else {
            baseUrl = usRoot;
        }
        RestTemplate template = new RestTemplate();
        String result;
        try {
            result = template.getForObject(appendApiKey(baseUrl + apiUrl), String.class);
        } catch (Exception ex) {
            throw new HeurekaException(String.format("Error during the fetch of %s%s", baseUrl, apiUrl), ex);
        }
        return result;
    }

    private String appendApiKey(String url) {
        if (url.contains("?")) {
            return url + "&apikey=" + apikey;
        } else {
            return url + "?apikey=" + apikey;
        }
    }

}