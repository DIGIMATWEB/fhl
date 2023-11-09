
package com.fhl.sistemadedistribucionfh.Tickets.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TipoVehiculo {

    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("usuario")
    @Expose
    private Object usuario;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("trail")
    @Expose
    private Object trail;
    @SerializedName("fechaCreacion")
    @Expose
    private String fechaCreacion;
    @SerializedName("eliminado")
    @Expose
    private Boolean eliminado;
    @SerializedName("esNuevo")
    @Expose
    private Boolean esNuevo;

    public TipoVehiculo(String nombre, Object usuario, Integer id, Object trail, String fechaCreacion, Boolean eliminado, Boolean esNuevo) {
        super();
        this.nombre = nombre;
        this.usuario = usuario;
        this.id = id;
        this.trail = trail;
        this.fechaCreacion = fechaCreacion;
        this.eliminado = eliminado;
        this.esNuevo = esNuevo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Object getUsuario() {
        return usuario;
    }

    public void setUsuario(Object usuario) {
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getTrail() {
        return trail;
    }

    public void setTrail(Object trail) {
        this.trail = trail;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Boolean getEsNuevo() {
        return esNuevo;
    }

    public void setEsNuevo(Boolean esNuevo) {
        this.esNuevo = esNuevo;
    }

}
