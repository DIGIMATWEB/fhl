
package com.fhl.sistemadedistribucionfh.evidence.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TipoEntrega {

    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("Id")
    @Expose
    private Integer id;

    public TipoEntrega(String nombre, Integer id) {
        super();
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
