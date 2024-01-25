
package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Modelo {

    @SerializedName("MarcaId")
    @Expose
    private Integer marcaId;
    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("Id")
    @Expose
    private Integer id;
    public Modelo(Integer marcaId, String nombre, Integer id) {
        super();
        this.marcaId = marcaId;
        this.nombre = nombre;
        this.id = id;
    }

    public Integer getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Integer marcaId) {
        this.marcaId = marcaId;
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
