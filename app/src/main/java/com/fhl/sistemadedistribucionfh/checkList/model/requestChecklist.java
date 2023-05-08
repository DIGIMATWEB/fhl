package com.fhl.sistemadedistribucionfh.checkList.model;

import com.google.gson.annotations.SerializedName;

public class requestChecklist {
    @SerializedName("token")
    private String token;

    public requestChecklist(String token) {
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
