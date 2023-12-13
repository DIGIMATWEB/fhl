
package com.fhl.sistemadedistribucionfh.nmanifest.modelV2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Marca {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Nombre")
    @Expose
    private String nombre;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Marca() {
    }

    /**
     * 
     * @param id
     * @param nombre
     */
    public Marca(Integer id, String nombre) {
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
