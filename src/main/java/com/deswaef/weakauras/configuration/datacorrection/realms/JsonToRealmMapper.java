package com.deswaef.weakauras.configuration.datacorrection.realms;

import com.deswaef.weakauras.configuration.datacorrection.realms.json.RealmsJsonObject;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class JsonToRealmMapper {

    public RealmsJsonObject map(String json) throws JsonMappingException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, RealmsJsonObject.class);
        } catch (IOException e) {
            throw new JsonMappingException("Unable to map realm-json to object");
        }
    }

    public RealmsJsonObject map(File file) throws JsonMappingException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(file, RealmsJsonObject.class);
        } catch (IOException e) {
            throw new JsonMappingException("Unable to load file");
        }
    }

}
