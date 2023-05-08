package com.fhl.sistemadedistribucionfh.mainContainer.model;

import com.google.gson.annotations.SerializedName;

public class requestMenuItems {
    @SerializedName("token")
    private String token;


    public requestMenuItems(String token) {
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
