package com.fhl.sistemadedistribucionfh.nmanifest.modelV2;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class dataManifestV2 {
    @SerializedName("auxiliares")
    private List<auxiliaresDataV2> auxiliares;
    @SerializedName("serviciosAdicionales")
    private List<serviciosAdicionalesDataV2> serviciosAdicionales;
    @SerializedName("folioDespacho")
    private String folioDespacho;
    @SerializedName("borrador")
    private String borrador;
    @SerializedName("origen")
    private String origen;
    @SerializedName("destino")
    private String destino;
    @SerializedName("vehiculoId")
    private Integer vehiculoId;
    @SerializedName("vehiculoTercero")
    private String vehiculoTercero;
    @SerializedName("remolqueId")
    private Integer remolqueId;
    @SerializedName("operadorId")
    private Integer operadorId;
    @SerializedName("custodia")
    private Boolean custodia;
    @SerializedName("peligroso")
    private Boolean peligroso;
    @SerializedName("rutaId")
    private Integer rutaId;
    @SerializedName("andenId")
    private Integer andenId;
    @SerializedName("estatusId")
    private Integer estatusId;
    @SerializedName("usuario")
    private String usuario;
    @SerializedName("ocupacionEfectiva")
    private String ocupacionEfectiva;
    @SerializedName("tiempoEntrega")
    private String tiempoEntrega;
    @SerializedName("vehiculo")
    private VehiculoDataV2 vehiculo;
    @SerializedName("remolque")
    private String remolque;
    @SerializedName("operador")
    private String operador;
    @SerializedName("ruta")
    private String ruta;
    @SerializedName("anden")
    private String anden;
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

    public dataManifestV2(List<auxiliaresDataV2> auxiliares, List<serviciosAdicionalesDataV2> serviciosAdicionales, String folioDespacho, String borrador, String origen, String destino, Integer vehiculoId, String vehiculoTercero, Integer remolqueId, Integer operadorId, Boolean custodia, Boolean peligroso, Integer rutaId, Integer andenId, Integer estatusId, String usuario, String ocupacionEfectiva, String tiempoEntrega, VehiculoDataV2 vehiculo, String remolque, String operador, String ruta, String anden, Integer id, String trail, String fechaCreacion, Boolean eliminado, Boolean esNuevo) {
        super();
        this.auxiliares = auxiliares;
        this.serviciosAdicionales = serviciosAdicionales;
        this.folioDespacho = folioDespacho;
        this.borrador = borrador;
        this.origen = origen;
        this.destino = destino;
        this.vehiculoId = vehiculoId;
        this.vehiculoTercero = vehiculoTercero;
        this.remolqueId = remolqueId;
        this.operadorId = operadorId;
        this.custodia = custodia;
        this.peligroso = peligroso;
        this.rutaId = rutaId;
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

    public List<auxiliaresDataV2> getAuxiliares() {
        return auxiliares;
    }

    public void setAuxiliares(List<auxiliaresDataV2> auxiliares) {
        this.auxiliares = auxiliares;
    }

    public List<serviciosAdicionalesDataV2> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setServiciosAdicionales(List<serviciosAdicionalesDataV2> serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales;
    }

    public String getFolioDespacho() {
        return folioDespacho;
    }

    public void setFolioDespacho(String folioDespacho) {
        this.folioDespacho = folioDespacho;
    }

    public String getBorrador() {
        return borrador;
    }

    public void setBorrador(String borrador) {
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

    public Integer getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Integer vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public String getVehiculoTercero() {
        return vehiculoTercero;
    }

    public void setVehiculoTercero(String vehiculoTercero) {
        this.vehiculoTercero = vehiculoTercero;
    }

    public Integer getRemolqueId() {
        return remolqueId;
    }

    public void setRemolqueId(Integer remolqueId) {
        this.remolqueId = remolqueId;
    }

    public Integer getOperadorId() {
        return operadorId;
    }

    public void setOperadorId(Integer operadorId) {
        this.operadorId = operadorId;
    }

    public Boolean getCustodia() {
        return custodia;
    }

    public void setCustodia(Boolean custodia) {
        this.custodia = custodia;
    }

    public Boolean getPeligroso() {
        return peligroso;
    }

    public void setPeligroso(Boolean peligroso) {
        this.peligroso = peligroso;
    }

    public Integer getRutaId() {
        return rutaId;
    }

    public void setRutaId(Integer rutaId) {
        this.rutaId = rutaId;
    }

    public Integer getAndenId() {
        return andenId;
    }

    public void setAndenId(Integer andenId) {
        this.andenId = andenId;
    }

    public Integer getEstatusId() {
        return estatusId;
    }

    public void setEstatusId(Integer estatusId) {
        this.estatusId = estatusId;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getOcupacionEfectiva() {
        return ocupacionEfectiva;
    }

    public void setOcupacionEfectiva(String ocupacionEfectiva) {
        this.ocupacionEfectiva = ocupacionEfectiva;
    }

    public String getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(String tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    public VehiculoDataV2 getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoDataV2 vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getRemolque() {
        return remolque;
    }

    public void setRemolque(String remolque) {
        this.remolque = remolque;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getAnden() {
        return anden;
    }

    public void setAnden(String anden) {
        this.anden = anden;
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
