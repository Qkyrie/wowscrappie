package com.deswaef.weakauras.websockets.users.data;

import java.util.Calendar;

public class ActiveWebSocketUser {
    private String id;

    private String username;

    private Calendar connectionTime;

    public ActiveWebSocketUser() {
    }

    public ActiveWebSocketUser(String id, String username, Calendar connectionTime) {
        super();
        this.id = id;
        this.username = username;
        this.connectionTime = connectionTime;
    }

    public String getUsername() {
        return username;
    }

    public ActiveWebSocketUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public Calendar getConnectionTime() {
        return connectionTime;
    }

    public ActiveWebSocketUser setConnectionTime(Calendar connectionTime) {
        this.connectionTime = connectionTime;
        return this;
    }


}

