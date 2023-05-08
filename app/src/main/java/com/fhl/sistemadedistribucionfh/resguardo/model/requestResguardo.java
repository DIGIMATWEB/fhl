package com.fhl.sistemadedistribucionfh.resguardo.model;

import com.google.gson.annotations.SerializedName;

public class requestResguardo {
    @SerializedName("token")
    private String token;

    public requestResguardo(String token) {
        super();
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
