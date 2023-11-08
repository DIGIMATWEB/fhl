package com.fhl.sistemadedistribucionfh.nmanifest.modelV2;

import com.google.gson.annotations.SerializedName;

public class auxiliaresDataV2 {
    @SerializedName("llave")
    private Integer llave;

    @SerializedName("valor")
    private String valor;

    public auxiliaresDataV2(Integer llave, String valor) {
        super();
        this.llave = llave;
        this.valor = valor;
    }

    public Integer getLlave() {
        return llave;
    }

    public void setLlave(Integer llave) {
        this.llave = llave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
