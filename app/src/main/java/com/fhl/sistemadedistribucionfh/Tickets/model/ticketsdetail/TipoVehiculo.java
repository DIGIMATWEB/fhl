
package com.fhl.sistemadedistribucionfh.Tickets.model.ticketsdetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TipoVehiculo implements Serializable {

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
    public TipoVehiculo() {
    }

    /**
     * 
     * @param id
     * @param nombre
     */
    public TipoVehiculo(Integer id, String nombre) {
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
