
package com.fhl.sistemadedistribucionfh.Tickets.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DocumentosOperador {

    @SerializedName("llave")
    @Expose
    private Integer llave;
    @SerializedName("valor")
    @Expose
    private String valor;

    public DocumentosOperador(Integer llave, String valor) {
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
