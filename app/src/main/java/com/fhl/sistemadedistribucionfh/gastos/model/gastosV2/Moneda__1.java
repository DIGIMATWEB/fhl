
package com.fhl.sistemadedistribucionfh.gastos.model.gastosV2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Moneda__1 {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Nombre")
    @Expose
    private String nombre;

    public Moneda__1(Integer id, String nombre) {
        super();
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
