package com.fhl.sistemadedistribucionfh.login.model;

import com.google.gson.annotations.SerializedName;

public class responseLogin {
    @SerializedName("token")
    private String token;
    @SerializedName("expiration")
    private String expiration;

    public responseLogin(String token, String expiration) {
        super();
        this.token = token;
        this.expiration = expiration;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
}
