package com.deswaef.wowscrappie.importingservice.battlenet.wow.client;

import com.google.gson.Gson;

public final class BattlenetConverter {
    private BattlenetConverter() {
    }

    public static <T> T convert(String input, Class<T> convertInto) {
        Gson gson = new Gson();
        return gson.fromJson(input, convertInto);
    }

}