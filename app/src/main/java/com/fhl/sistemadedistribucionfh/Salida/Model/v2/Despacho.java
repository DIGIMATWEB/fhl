
package com.fhl.sistemadedistribucionfh.Salida.Model.v2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Despacho {
    @SerializedName("folioDespacho")
    @Expose
    private String folioDespacho;
    @SerializedName("borrador")
    @Expose
    private Object borrador;
    @SerializedName("origen")
    @Expose
    private String origen;
    @SerializedName("destino")
    @Expose
    private String destino;
    @SerializedName("vehiculoId")
    @Expose
    private Object vehiculoId;
    @SerializedName("vehiculoTercero")
    @Expose
    private Object vehiculoTercero;
    @SerializedName("remolqueId")
    @Expose
    private Object remolqueId;
    @SerializedName("operadorId")
    @Expose
    private Object operadorId;
    @SerializedName("custodia")
    @Expose
    private Object custodia;
    @SerializedName("auxiliares")
    @Expose
    private Object auxiliares;
    @SerializedName("peligroso")
    @Expose
    private Object peligroso;
    @SerializedName("rutaId")
    @Expose
    private Object rutaId;
    @SerializedName("serviciosAdicionales")
    @Expose
    private Object serviciosAdicionales;
    @SerializedName("andenId")
    @Expose
    private Object andenId;
    @SerializedName("estatusId")
    @Expose
    private Object estatusId;
    @SerializedName("usuario")
    @Expose
    private Object usuario;
    @SerializedName("ocupacionEfectiva")
    @Expose
    private Object ocupacionEfectiva;
    @SerializedName("tiempoEntrega")
    @Expose
    private Object tiempoEntrega;
    @SerializedName("vehiculo")
    @Expose
    private Object vehiculo;
    @SerializedName("remolque")
    @Expose
    private Object remolque;
    @SerializedName("operador")
    @Expose
    private Object operador;
    @SerializedName("ruta")
    @Expose
    private Object ruta;
    @SerializedName("anden")
    @Expose
    private Object anden;
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

    public Despacho(String folioDespacho, Object borrador, String origen, String destino, Object vehiculoId, Object vehiculoTercero, Object remolqueId, Object operadorId, Object custodia, Object auxiliares, Object peligroso, Object rutaId, Object serviciosAdicionales, Object andenId, Object estatusId, Object usuario, Object ocupacionEfectiva, Object tiempoEntrega, Object vehiculo, Object remolque, Object operador, Object ruta, Object anden, Integer id, Object trail, String fechaCreacion, Boolean eliminado, Boolean esNuevo) {
        super();
        this.folioDespacho = folioDespacho;
        this.borrador = borrador;
        this.origen = origen;
        this.destino = destino;
        this.vehiculoId = vehiculoId;
        this.vehiculoTercero = vehiculoTercero;
        this.remolqueId = remolqueId;
        this.operadorId = operadorId;
        this.custodia = custodia;
        this.auxiliares = auxiliares;
        this.peligroso = peligroso;
        this.rutaId = rutaId;
        this.serviciosAdicionales = serviciosAdicionales;
        this.andenId = andenId;
        this.estatusId = estatusId;
        this.usuario = usuario;
        this.ocupacionEfectiva = ocupacionEfectiva;
        this.tiempoEntrega = tiempoEntrega;
        this.vehiculo = vehiculo;
        this.remolque = remolque;
        this.operador = operador;
        this.ruta = ruta;
        this.anden = anden;
        this.id = id;
        this.trail = trail;
        this.fechaCreacion = fechaCreacion;
        this.eliminado = eliminado;
        this.esNuevo = esNuevo;
    }

    public String getFolioDespacho() {
        return folioDespacho;
    }

    public void setFolioDespacho(String folioDespacho) {
        this.folioDespacho = folioDespacho;
    }

    public Object getBorrador() {
        return borrador;
    }

    public void setBorrador(Object borrador) {
        this.borrador = borrador;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Object getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Object vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public Object getVehiculoTercero() {
        return vehiculoTercero;
    }

    public void setVehiculoTercero(Object vehiculoTercero) {
        this.vehiculoTercero = vehiculoTercero;
    }

    public Object getRemolqueId() {
        return remolqueId;
    }

    public void setRemolqueId(Object remolqueId) {
        this.remolqueId = remolqueId;
    }

    public Object getOperadorId() {
        return operadorId;
    }

    public void setOperadorId(Object operadorId) {
        this.operadorId = operadorId;
    }

    public Object getCustodia() {
        return custodia;
    }

    public void setCustodia(Object custodia) {
        this.custodia = custodia;
    }

    public Object getAuxiliares() {
        return auxiliares;
    }

    public void setAuxiliares(Object auxiliares) {
        this.auxiliares = auxiliares;
    }

    public Object getPeligroso() {
        return peligroso;
    }

    public void setPeligroso(Object peligroso) {
        this.peligroso = peligroso;
    }

    public Object getRutaId() {
        return rutaId;
    }

    public void setRutaId(Object rutaId) {
        this.rutaId = rutaId;
    }

    public Object getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setServiciosAdicionales(Object serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales;
    }

    public Object getAndenId() {
        return andenId;
    }

    public void setAndenId(Object andenId) {
        this.andenId = andenId;
    }

    public Object getEstatusId() {
        return estatusId;
    }

    public void setEstatusId(Object estatusId) {
        this.estatusId = estatusId;
    }

    public Object getUsuario() {
        return usuario;
    }

    public void setUsuario(Object usuario) {
        this.usuario = usuario;
    }

    public Object getOcupacionEfectiva() {
        return ocupacionEfectiva;
    }

    public void setOcupacionEfectiva(Object ocupacionEfectiva) {
        this.ocupacionEfectiva = ocupacionEfectiva;
    }

    public Object getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(Object tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    public Object getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Object vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Object getRemolque() {
        return remolque;
    }

    public void setRemolque(Object remolque) {
        this.remolque = remolque;
    }

    public Object getOperador() {
        return operador;
    }

    public void setOperador(Object operador) {
        this.operador = operador;
    }

    public Object getRuta() {
        return ruta;
    }

    public void setRuta(Object ruta) {
        this.ruta = ruta;
    }

    public Object getAnden() {
        return anden;
    }

    public void setAnden(Object anden) {
        this.anden = anden;
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
