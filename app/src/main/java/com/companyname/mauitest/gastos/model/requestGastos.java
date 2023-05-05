package com.companyname.mauitest.gastos.model;

import com.google.gson.annotations.SerializedName;

public class requestGastos {
    @SerializedName("token")
    private String token;

    public requestGastos(String token) {
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
