
package com.fhl.sistemadedistribucionfh.Tickets.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cliente {

    @SerializedName("razonSocial")
    @Expose
    private String razonSocial;
    @SerializedName("rfc")
    @Expose
    private String rfc;
    @SerializedName("axaptaId")
    @Expose
    private Object axaptaId;
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

    public Cliente(String razonSocial, String rfc, Object axaptaId, Object usuario, Integer id, Object trail, String fechaCreacion, Boolean eliminado, Boolean esNuevo) {
        super();
        this.razonSocial = razonSocial;
        this.rfc = rfc;
        this.axaptaId = axaptaId;
        this.usuario = usuario;
        this.id = id;
        this.trail = trail;
        this.fechaCreacion = fechaCreacion;
        this.eliminado = eliminado;
        this.esNuevo = esNuevo;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Object getAxaptaId() {
        return axaptaId;
    }

    public void setAxaptaId(Object axaptaId) {
        this.axaptaId = axaptaId;
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
