package com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model;

import com.google.gson.annotations.SerializedName;

public class requestSetValidacion {

    @SerializedName("folioDespacho")
    private String folioDespacho;
    @SerializedName("validador")
    private Validadorset validador;

    public requestSetValidacion(String folioDespacho, Validadorset validador) {
        super();
        this.folioDespacho = folioDespacho;
        this.validador = validador;
    }

    public String getFolioDespacho() {
        return folioDespacho;
    }

    public void setFolioDespacho(String folioDespacho) {
        this.folioDespacho = folioDespacho;
    }

    public Validadorset getValidador() {
        return validador;
    }

    public void setValidador(Validadorset validador) {
        this.validador = validador;
    }
}
