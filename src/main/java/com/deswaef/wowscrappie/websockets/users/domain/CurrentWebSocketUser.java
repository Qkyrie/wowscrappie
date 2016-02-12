package com.deswaef.wowscrappie.websockets.users.domain;

import java.io.Serializable;
import java.util.Calendar;

public class CurrentWebSocketUser implements Serializable{
    private String id;

    private String username;

    private Calendar connectionTime;

    public CurrentWebSocketUser() {
    }

    public CurrentWebSocketUser(String id, String username, Calendar connectionTime) {
        super();
        this.id = id;
        this.username = username;
        this.connectionTime = connectionTime;
    }

    public String getUsername() {
        return username;
    }

    public CurrentWebSocketUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public Calendar getConnectionTime() {
        return connectionTime;
    }

    public CurrentWebSocketUser setConnectionTime(Calendar connectionTime) {
        this.connectionTime = connectionTime;
        return this;
    }


}

