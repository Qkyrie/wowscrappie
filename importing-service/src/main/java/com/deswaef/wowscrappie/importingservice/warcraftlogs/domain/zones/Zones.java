package com.deswaef.wowscrappie.importingservice.warcraftlogs.domain.zones;

import com.deswaef.wowscrappie.importingservice.warcraftlogs.WarcraftlogsConversionException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;

public class Zones {

    public static Collection<Zone> fromJson(String json) throws WarcraftlogsConversionException {
        try {
            Type type = new TypeToken<Collection<Zone>>() {
            }.getType();
            Gson gson = new Gson();
            return gson.fromJson(json, type);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new WarcraftlogsConversionException();
        }
    }
}
