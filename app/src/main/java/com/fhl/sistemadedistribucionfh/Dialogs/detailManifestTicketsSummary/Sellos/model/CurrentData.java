package com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.model;

import com.google.gson.annotations.SerializedName;

public class CurrentData {

    @SerializedName("NumeroSello")
    private String numeroSello;
    @SerializedName("DespachoId")
    private Integer despachoId;
    @SerializedName("Usuario")
    private Object usuario;
    @SerializedName("Id")
    private Object id;
    @SerializedName("Trail")
    private Object trail;

    public CurrentData(String numeroSello, Integer despachoId, Object usuario, Object id, Object trail) {
        super();
        this.numeroSello = numeroSello;
        this.despachoId = despachoId;
        this.usuario = usuario;
        this.id = id;
        this.trail = trail;
    }

    public String getNumeroSello() {
        return numeroSello;
    }

    public void setNumeroSello(String numeroSello) {
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

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getTrail() {
        return trail;
    }

    public void setTrail(Object trail) {
        this.trail = trail;
    }
}
