package com.deswaef.weakauras.usermanagement.util;

public class FacebookUser {
    private String facebookId;
    private String username;
    private String email;

    public String getFacebookId() {
        return facebookId;
    }

    public FacebookUser setFacebookId(String facebookId) {
        this.facebookId = facebookId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public FacebookUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public FacebookUser setEmail(String email) {
        this.email = email;
        return this;
    }
}
