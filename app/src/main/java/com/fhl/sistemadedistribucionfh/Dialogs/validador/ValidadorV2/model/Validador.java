
package com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Validador {

    @SerializedName("estatus")
    @Expose
    private String estatus;

    public Validador(String estatus) {
        super();
        this.estatus = estatus;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

}
