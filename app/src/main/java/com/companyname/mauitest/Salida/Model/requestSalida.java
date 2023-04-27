package com.companyname.mauitest.Salida.Model;

import com.google.gson.annotations.SerializedName;

public class requestSalida {

    @SerializedName("token")
    private String token;
    @SerializedName("salida")
    private String salida;

    public requestSalida(String token, String salida) {
        super();
        this.token = token;
        this.salida = salida;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }
}
