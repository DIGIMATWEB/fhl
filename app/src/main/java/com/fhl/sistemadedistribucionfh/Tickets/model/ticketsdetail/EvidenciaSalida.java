
package com.fhl.sistemadedistribucionfh.Tickets.model.ticketsdetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EvidenciaSalida {

    @SerializedName("Llave")
    @Expose
    private Integer llave;
    @SerializedName("TipoEvidencia")
    private Integer TipoEvidencia;
    @SerializedName("Valor")
    @Expose
    private String valor;
    public EvidenciaSalida(Integer TipoEvidencia,Integer llave, String valor) {
        super();
        this.llave = llave;
        this.valor = valor;
        this.TipoEvidencia=TipoEvidencia;
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

    public Integer getTipoEvidencia() {
        return TipoEvidencia;
    }

    public void setTipoEvidencia(Integer tipoEvidencia) {
        TipoEvidencia = tipoEvidencia;
    }
}
