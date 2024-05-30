
package com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DocumentosAuxiliar implements Serializable {

    @SerializedName("Llave")
    @Expose
    private Integer llave;
    @SerializedName("Valor")
    @Expose
    private String valor;

    public DocumentosAuxiliar(Integer llave, String valor) {
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
