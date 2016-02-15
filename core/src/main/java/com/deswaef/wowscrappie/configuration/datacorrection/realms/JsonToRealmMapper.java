package com.deswaef.wowscrappie.configuration.datacorrection.realms;

import com.deswaef.wowscrappie.configuration.datacorrection.realms.json.RealmsJsonObject;
import com.deswaef.wowscrappie.infrastructure.exception.WowscrappieException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class JsonToRealmMapper {

    public RealmsJsonObject map(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, RealmsJsonObject.class);
        } catch (IOException e) {
            throw new WowscrappieException("Unable to map realm-json to object", e);
        }
    }

    public RealmsJsonObject map(File file) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(file, RealmsJsonObject.class);
        } catch (IOException e) {
            throw new WowscrappieException("Unable to load file", e);
        }
    }

}
