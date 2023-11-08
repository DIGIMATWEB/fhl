package com.fhl.sistemadedistribucionfh.nmanifest.modelV2;

import com.google.gson.annotations.SerializedName;

public class MarcaDataV2 {
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("usuario")
    private String usuario;
    @SerializedName("id")
    private Integer id;
    @SerializedName("trail")
    private Object trail;
    @SerializedName("fechaCreacion")
    private String fechaCreacion;
    @SerializedName("eliminado")
    private Boolean eliminado;
    @SerializedName("esNuevo")
    private Boolean esNuevo;

    public MarcaDataV2(String nombre, String usuario, Integer id, Object trail, String fechaCreacion, Boolean eliminado, Boolean esNuevo) {
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
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
