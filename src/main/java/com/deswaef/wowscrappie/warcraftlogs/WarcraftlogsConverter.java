package com.deswaef.wowscrappie.warcraftlogs;

import com.google.gson.Gson;

public class WarcraftlogsConverter {
    private WarcraftlogsConverter() {}
    public static <T> T  convert(String input, Class<T> convertInto){
        Gson gson = new Gson();
        return gson.fromJson(input, convertInto);
    }
}
