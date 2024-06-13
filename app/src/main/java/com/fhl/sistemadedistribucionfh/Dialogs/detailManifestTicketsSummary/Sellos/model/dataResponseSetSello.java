package com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.model;

import com.google.gson.annotations.SerializedName;

public class dataResponseSetSello {
    @SerializedName("numeroSello")
    private Object numeroSello;
    @SerializedName("despachoId")
    private Integer despachoId;
    @SerializedName("usuario")
    private Object usuario;
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

    public dataResponseSetSello(Object numeroSello, Integer despachoId, Object usuario, Integer id, Object trail, String fechaCreacion, Boolean eliminado, Boolean esNuevo) {
        super();
        this.numeroSello = numeroSello;
        this.despachoId = despachoId;
        this.usuario = usuario;
        this.id = id;
        this.trail = trail;
        this.fechaCreacion = fechaCreacion;
        this.eliminado = eliminado;
        this.esNuevo = esNuevo;
    }

    public Object getNumeroSello() {
        return numeroSello;
    }

    public void setNumeroSello(Object numeroSello) {
        this.numeroSello = numeroSello;
    }

    public Integer getDespachoId() {
        return despachoId;
    }

    public void setDespachoId(Integer despachoId) {
        this.despachoId = despachoId;
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
