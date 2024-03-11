package com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model;

import com.google.gson.annotations.SerializedName;

public class Validadorset {

    @SerializedName("Usuario")
    private String usuario;
    @SerializedName("Estatus")
    private String estatus;

    public Validadorset(String usuario, String estatus) {
        super();
        this.usuario = usuario;
        this.estatus = estatus;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}
