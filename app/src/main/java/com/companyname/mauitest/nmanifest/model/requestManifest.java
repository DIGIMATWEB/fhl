package com.companyname.mauitest.nmanifest.model;

import com.google.gson.annotations.SerializedName;

public class requestManifest {

    @SerializedName("token")
    private String token;

    public requestManifest(String token) {
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
