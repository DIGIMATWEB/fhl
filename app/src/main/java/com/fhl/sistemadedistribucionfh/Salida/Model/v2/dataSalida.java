
package com.fhl.sistemadedistribucionfh.Salida.Model.v2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dataSalida {
    @SerializedName("despacho")
    @Expose
    private Despacho despacho;
    @SerializedName("qrCodigo")
    @Expose
    private String qrCodigo;
    @SerializedName("numeroSello")
    @Expose
    private String numeroSello;
    @SerializedName("despachoId")
    @Expose
    private Integer despachoId;
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
    public dataSalida(Despacho despacho, String qrCodigo, String numeroSello, Integer despachoId, Object usuario, Integer id, Object trail, String fechaCreacion, Boolean eliminado, Boolean esNuevo) {
        super();
        this.despacho = despacho;
        this.qrCodigo = qrCodigo;
        this.numeroSello = numeroSello;
        this.despachoId = despachoId;
        this.usuario = usuario;
        this.id = id;
        this.trail = trail;
        this.fechaCreacion = fechaCreacion;
        this.eliminado = eliminado;
        this.esNuevo = esNuevo;
    }

    public Despacho getDespacho() {
        return despacho;
    }

    public void setDespacho(Despacho despacho) {
        this.despacho = despacho;
    }

    public String getQrCodigo() {
        return qrCodigo;
    }

    public void setQrCodigo(String qrCodigo) {
        this.qrCodigo = qrCodigo;
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
