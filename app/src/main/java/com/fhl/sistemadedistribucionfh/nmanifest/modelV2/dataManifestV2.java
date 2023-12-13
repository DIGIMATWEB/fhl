
package com.fhl.sistemadedistribucionfh.nmanifest.modelV2;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dataManifestV2 {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("FolioDespacho")
    @Expose
    private String folioDespacho;
    @SerializedName("Origen")
    @Expose
    private String origen;
    @SerializedName("Destino")
    @Expose
    private String destino;
    @SerializedName("VehiculoTercero")
    @Expose
    private String vehiculoTercero;
    @SerializedName("OperadorId")
    @Expose
    private Integer operadorId;
    @SerializedName("Operadores")
    @Expose
    private Operadores operadores;
    @SerializedName("Custodia")
    @Expose
    private Boolean custodia;
    @SerializedName("Peligroso")
    @Expose
    private Boolean peligroso;
    @SerializedName("RutaId")
    @Expose
    private Integer rutaId;
    @SerializedName("AndenId")
    @Expose
    private Integer andenId;
    @SerializedName("EstatusId")
    @Expose
    private Integer estatusId;
    @SerializedName("Estatus")
    @Expose
    private Estatus estatus;
    @SerializedName("Usuario")
    @Expose
    private String usuario;
    @SerializedName("FechaCreacion")
    @Expose
    private String fechaCreacion;
    @SerializedName("OcupacionEfectiva")
    @Expose
    private String ocupacionEfectiva;
    @SerializedName("TiempoEntrega")
    @Expose
    private String tiempoEntrega;
    @SerializedName("ServiciosAdicionales")
    @Expose
    private List<ServiciosAdicionale> serviciosAdicionales;
    @SerializedName("Auxiliares")
    @Expose
    private List<Auxiliare> auxiliares;
    @SerializedName("VehiculoId")
    @Expose
    private Integer vehiculoId;
    @SerializedName("Vehiculo")
    @Expose
    private Vehiculo vehiculo;

    /**
     * No args constructor for use in serialization
     * 
     */
    public dataManifestV2() {
    }

    /**
     * 
     * @param custodia
     * @param ocupacionEfectiva
     * @param vehiculoTercero
     * @param operadores
     * @param rutaId
     * @param serviciosAdicionales
     * @param origen
     * @param estatusId
     * @param vehiculo
     * @param folioDespacho
     * @param operadorId
     * @param estatus
     * @param tiempoEntrega
     * @param vehiculoId
     * @param auxiliares
     * @param peligroso
     * @param andenId
     * @param fechaCreacion
     * @param usuario
     * @param id
     * @param destino
     */
    public dataManifestV2(Integer id, String folioDespacho, String origen, String destino, String vehiculoTercero, Integer operadorId, Operadores operadores, Boolean custodia, Boolean peligroso, Integer rutaId, Integer andenId, Integer estatusId, Estatus estatus, String usuario, String fechaCreacion, String ocupacionEfectiva, String tiempoEntrega, List<ServiciosAdicionale> serviciosAdicionales, List<Auxiliare> auxiliares, Integer vehiculoId, Vehiculo vehiculo) {
        super();
        this.id = id;
        this.folioDespacho = folioDespacho;
        this.origen = origen;
        this.destino = destino;
        this.vehiculoTercero = vehiculoTercero;
        this.operadorId = operadorId;
        this.operadores = operadores;
        this.custodia = custodia;
        this.peligroso = peligroso;
        this.rutaId = rutaId;
        this.andenId = andenId;
        this.estatusId = estatusId;
        this.estatus = estatus;
        this.usuario = usuario;
        this.fechaCreacion = fechaCreacion;
        this.ocupacionEfectiva = ocupacionEfectiva;
        this.tiempoEntrega = tiempoEntrega;
        this.serviciosAdicionales = serviciosAdicionales;
        this.auxiliares = auxiliares;
        this.vehiculoId = vehiculoId;
        this.vehiculo = vehiculo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFolioDespacho() {
        return folioDespacho;
    }

    public void setFolioDespacho(String folioDespacho) {
        this.folioDespacho = folioDespacho;
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

    public String getVehiculoTercero() {
        return vehiculoTercero;
    }

    public void setVehiculoTercero(String vehiculoTercero) {
        this.vehiculoTercero = vehiculoTercero;
    }

    public Integer getOperadorId() {
        return operadorId;
    }

    public void setOperadorId(Integer operadorId) {
        this.operadorId = operadorId;
    }

    public Operadores getOperadores() {
        return operadores;
    }

    public void setOperadores(Operadores operadores) {
        this.operadores = operadores;
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

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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

    public List<ServiciosAdicionale> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setServiciosAdicionales(List<ServiciosAdicionale> serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales;
    }

    public List<Auxiliare> getAuxiliares() {
        return auxiliares;
    }

    public void setAuxiliares(List<Auxiliare> auxiliares) {
        this.auxiliares = auxiliares;
    }

    public Integer getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Integer vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

}
