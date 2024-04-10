
package com.fhl.sistemadedistribucionfh.gastos.model.gastosV2;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dataGastosOperativos {

    @SerializedName("OperadorId")
    @Expose
    private Integer operadorId;
    @SerializedName("Operador")
    @Expose
    private Operador operador;
    @SerializedName("EstatusId")
    @Expose
    private Integer estatusId;
    @SerializedName("Estatus")
    @Expose
    private Estatus estatus;
    @SerializedName("FechaCreacion")
    @Expose
    private String fechaCreacion;
    @SerializedName("OcupacionEfectiva")
    @Expose
    private String ocupacionEfectiva;
    @SerializedName("TiempoEntrega")
    @Expose
    private String tiempoEntrega;
    @SerializedName("VehiculoId")
    @Expose
    private Integer vehiculoId;
    @SerializedName("Vehiculo")
    @Expose
    private Vehiculo vehiculo;
    @SerializedName("GastosOperativos")
    @Expose
    private List<GastosOperativo> gastosOperativos;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("FolioDespacho")
    @Expose
    private String folioDespacho;


    public dataGastosOperativos(Integer operadorId, Operador operador, Integer estatusId, Estatus estatus, String fechaCreacion, String ocupacionEfectiva, String tiempoEntrega, Integer vehiculoId, Vehiculo vehiculo, List<GastosOperativo> gastosOperativos, Integer id, String folioDespacho) {
        super();
        this.operadorId = operadorId;
        this.operador = operador;
        this.estatusId = estatusId;
        this.estatus = estatus;
        this.fechaCreacion = fechaCreacion;
        this.ocupacionEfectiva = ocupacionEfectiva;
        this.tiempoEntrega = tiempoEntrega;
        this.vehiculoId = vehiculoId;
        this.vehiculo = vehiculo;
        this.gastosOperativos = gastosOperativos;
        this.id = id;
        this.folioDespacho = folioDespacho;
    }

    public Integer getOperadorId() {
        return operadorId;
    }

    public void setOperadorId(Integer operadorId) {
        this.operadorId = operadorId;
    }

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
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

    public List<GastosOperativo> getGastosOperativos() {
        return gastosOperativos;
    }

    public void setGastosOperativos(List<GastosOperativo> gastosOperativos) {
        this.gastosOperativos = gastosOperativos;
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

}
