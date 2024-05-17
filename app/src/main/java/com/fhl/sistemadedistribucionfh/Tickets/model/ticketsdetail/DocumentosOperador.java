
package com.fhl.sistemadedistribucionfh.Tickets.model.ticketsdetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DocumentosOperador implements Serializable {

    @SerializedName("Llave")
    @Expose
    private Integer llave;
    @SerializedName("Valor")
    @Expose
    private String valor;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DocumentosOperador() {
    }

    /**
     * 
     * @param llave
     * @param valor
     */
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
