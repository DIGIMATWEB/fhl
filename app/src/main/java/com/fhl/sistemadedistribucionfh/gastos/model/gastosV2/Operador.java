
package com.fhl.sistemadedistribucionfh.gastos.model.gastosV2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Operador {

    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("RFC")
    @Expose
    private String rfc;
    @SerializedName("Id")
    @Expose
    private Integer id;

    public Operador(String nombre, String rfc, Integer id) {
        super();
        this.nombre = nombre;
        this.rfc = rfc;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
