package com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.habilitiesManifest;

import com.google.gson.annotations.SerializedName;

public class HabilidadesAuxiliare {
    @SerializedName("Llave")
    private Integer llave;
    @SerializedName("Valor")
    private String valor;
    public HabilidadesAuxiliare(Integer llave,String valor) {
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
