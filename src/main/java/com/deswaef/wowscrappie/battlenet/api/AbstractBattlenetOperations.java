package com.deswaef.wowscrappie.battlenet.api;

import org.springframework.social.MissingAuthorizationException;

/**
 * User: Quinten
 * Date: 13-5-2015
 * Time: 00:20
 *
 * @author Quinten De Swaef
 */
public class AbstractBattlenetOperations {
    private final boolean isAuthorized;

    public AbstractBattlenetOperations(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

    protected void requireAuthorization() {
        if (!isAuthorized) {
            throw new MissingAuthorizationException("battlenet");
        }
    }

    // Using String here instead of URI so I can include braces in the path. See, e.g., RepoTemplate. [WLW]
    protected String buildUri(String path) {
        return API_URL_BASE + path;
    }

    // GitHub API v3
    private static final String API_URL_BASE = "https://eu.api.battle.net";
}
