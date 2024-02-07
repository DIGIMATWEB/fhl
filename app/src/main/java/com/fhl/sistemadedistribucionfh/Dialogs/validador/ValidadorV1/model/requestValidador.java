package com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV1.model;

import com.google.gson.annotations.SerializedName;

public class requestValidador {

    @SerializedName("token")
    private String token;
    @SerializedName("barcode")
    private String barcode;

    public requestValidador(String token, String barcode) {
        super();
        this.token = token;
        this.barcode = barcode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
