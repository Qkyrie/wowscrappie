package com.deswaef.wowscrappie.warcraftlogs.domain.rankings;

import com.deswaef.wowscrappie.warcraftlogs.WarcraftlogsConversionException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;

public class CharacterRankings {

    public static Collection<CharacterRanking> fromJson(String json) throws WarcraftlogsConversionException {
        try {
            Type type = new TypeToken<Collection<CharacterRanking>>() {
            }.getType();
            Gson gson = new Gson();
            return gson.fromJson(json, type);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new WarcraftlogsConversionException();
        }
    }
}
