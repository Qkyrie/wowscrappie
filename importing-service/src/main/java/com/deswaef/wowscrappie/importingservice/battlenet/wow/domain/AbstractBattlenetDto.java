package com.deswaef.wowscrappie.importingservice.battlenet.wow.domain;

import com.deswaef.wowscrappie.importingservice.infrastructure.exception.HeurekaException;

public abstract class AbstractBattlenetDto {
    private static final String ERROR_MATCHER = "\"status\":\"nok\"";

    public static boolean isErrorMessage(String json) {
        return json.contains(ERROR_MATCHER);
    }

    public static void throwException(String json) {
        throw new HeurekaException(String.format("unable to convert json: %s", json));
    }
}