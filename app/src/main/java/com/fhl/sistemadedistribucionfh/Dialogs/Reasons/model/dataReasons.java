package com.fhl.sistemadedistribucionfh.Dialogs.Reasons.model;

import com.google.gson.annotations.SerializedName;

public class dataReasons {

    @SerializedName("descripcionCausa")
    private String descripcionCausa;
    @SerializedName("usuario")
    private String usuario;
    @SerializedName("id")
    private Integer id;
    @SerializedName("trail")
    private String trail;
    @SerializedName("fechaCreacion")
    private String fechaCreacion;
    @SerializedName("eliminado")
    private Boolean eliminado;
    @SerializedName("esNuevo")
    private Boolean esNuevo;

    public dataReasons(String descripcionCausa, String usuario, Integer id, String trail, String fechaCreacion, Boolean eliminado, Boolean esNuevo) {
        super();
        this.descripcionCausa = descripcionCausa;
        this.usuario = usuario;
        this.id = id;
        this.trail = trail;
        this.fechaCreacion = fechaCreacion;
        this.eliminado = eliminado;
        this.esNuevo = esNuevo;
    }

    public String getDescripcionCausa() {
        return descripcionCausa;
    }

    public void setDescripcionCausa(String descripcionCausa) {
        this.descripcionCausa = descripcionCausa;
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

    public String getTrail() {
        return trail;
    }

    public void setTrail(String trail) {
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

