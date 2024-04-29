
package com.fhl.sistemadedistribucionfh.Tickets.model.ticketsdetail;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.Pregunta;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Check implements Serializable {
    @SerializedName("Preguntas")
    @Expose
    private List<Pregunta> preguntas;
    @SerializedName("Llave")
    @Expose
    private Integer llave;
    @SerializedName("Valor")
    @Expose
    private String valor;

    public Check(List<Pregunta> preguntas, Integer llave, String valor) {
        super();
        this.preguntas = preguntas;
        this.llave = llave;
        this.valor = valor;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
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
