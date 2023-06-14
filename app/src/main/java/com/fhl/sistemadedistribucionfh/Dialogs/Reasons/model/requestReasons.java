package com.fhl.sistemadedistribucionfh.Dialogs.Reasons.model;

import com.google.gson.annotations.SerializedName;

public class requestReasons {
    @SerializedName("token")
    private String token;
    public requestReasons(String token) {
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
