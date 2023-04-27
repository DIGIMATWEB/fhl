package com.companyname.mauitest.Salida.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sello implements Serializable {
    @SerializedName("nombreSello")
    private String nombreSello;
    @SerializedName("folio")
    private String folio;

    public Sello(String nombreSello, String folio) {
        super();
        this.nombreSello = nombreSello;
        this.folio = folio;
    }

    public String getNombreSello() {
        return nombreSello;
    }

    public void setNombreSello(String nombreSello) {
        this.nombreSello = nombreSello;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }
}
