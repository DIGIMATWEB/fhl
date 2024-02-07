
package com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Operador {

    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("Id")
    @Expose
    private Integer id;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Operador() {
    }

    /**
     * 
     * @param id
     * @param nombre
     */
    public Operador(String nombre, Integer id) {
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
