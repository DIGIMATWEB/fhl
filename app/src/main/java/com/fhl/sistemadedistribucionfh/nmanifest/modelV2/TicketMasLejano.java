package com.fhl.sistemadedistribucionfh.nmanifest.modelV2;

import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.Cliente;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.Estatus;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.EvidenciaLlegada;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.EvidenciaSalida;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.TipoEntrega;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.TipoVehiculo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TicketMasLejano {
    @SerializedName("EvidenciaSalida")
    @Expose
    private List<EvidenciaSalida> evidenciaSalida;
    @SerializedName("EvidenciaLlegada")
    @Expose
    private List<EvidenciaLlegada> evidenciaLlegada;
    @SerializedName("Cliente")
    @Expose
    private Cliente cliente;
    @SerializedName("VehiculoId")
    @Expose
    private Integer vehiculoId;
    @SerializedName("Vehiculo")
    @Expose
    private Vehiculo vehiculo;
    @SerializedName("TipoVehiculo")
    @Expose
    private TipoVehiculo tipoVehiculo;
    @SerializedName("TipoEntrega")
    @Expose
    private TipoEntrega tipoEntrega;
    @SerializedName("Estatus")
    @Expose
    private Estatus estatus;
    @SerializedName("SendtripPlus")
    @Expose
    private SendtripPlus sendtripPlus;
    @SerializedName("FolioTicket")
    @Expose
    private String folioTicket;
    @SerializedName("Origen")
    @Expose
    private String origen;
    @SerializedName("ClienteId")
    @Expose
    private Integer clienteId;
    @SerializedName("Referencia")
    @Expose
    private String referencia;
    @SerializedName("TipoSolicitudId")
    @Expose
    private Integer tipoSolicitudId;
    @SerializedName("TipoEntregaId")
    @Expose
    private Integer tipoEntregaId;
    @SerializedName("EstatusId")
    @Expose
    private Integer estatusId;
    @SerializedName("Comentarios")
    @Expose
    private String comentarios;
    @SerializedName("TiempoParadaDestino")
    @Expose
    private String tiempoParadaDestino;
    @SerializedName("TipoVehiculoId")
    @Expose
    private Integer tipoVehiculoId;
    @SerializedName("Id")
    @Expose
    private Integer id;

    public TicketMasLejano(List<EvidenciaSalida> evidenciaSalida, List<EvidenciaLlegada> evidenciaLlegada, Cliente cliente, Integer vehiculoId, Vehiculo vehiculo, TipoVehiculo tipoVehiculo, TipoEntrega tipoEntrega, Estatus estatus, SendtripPlus sendtripPlus, String folioTicket, String origen, Integer clienteId, String referencia, Integer tipoSolicitudId, Integer tipoEntregaId, Integer estatusId, String comentarios, String tiempoParadaDestino, Integer tipoVehiculoId, Integer id) {
        super();
        this.evidenciaSalida = evidenciaSalida;
        this.evidenciaLlegada = evidenciaLlegada;
        this.cliente = cliente;
        this.vehiculoId = vehiculoId;
        this.vehiculo = vehiculo;
        this.tipoVehiculo = tipoVehiculo;
        this.tipoEntrega = tipoEntrega;
        this.estatus = estatus;
        this.sendtripPlus = sendtripPlus;
        this.folioTicket = folioTicket;
        this.origen = origen;
        this.clienteId = clienteId;
        this.referencia = referencia;
        this.tipoSolicitudId = tipoSolicitudId;
        this.tipoEntregaId = tipoEntregaId;
        this.estatusId = estatusId;
        this.comentarios = comentarios;
        this.tiempoParadaDestino = tiempoParadaDestino;
        this.tipoVehiculoId = tipoVehiculoId;
        this.id = id;
    }

    public List<EvidenciaSalida> getEvidenciaSalida() {
        return evidenciaSalida;
    }

    public void setEvidenciaSalida(List<EvidenciaSalida> evidenciaSalida) {
        this.evidenciaSalida = evidenciaSalida;
    }

    public List<EvidenciaLlegada> getEvidenciaLlegada() {
        return evidenciaLlegada;
    }

    public void setEvidenciaLlegada(List<EvidenciaLlegada> evidenciaLlegada) {
        this.evidenciaLlegada = evidenciaLlegada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public TipoEntrega getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(TipoEntrega tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public SendtripPlus getSendtripPlus() {
        return sendtripPlus;
    }

    public void setSendtripPlus(SendtripPlus sendtripPlus) {
        this.sendtripPlus = sendtripPlus;
    }

    public String getFolioTicket() {
        return folioTicket;
    }

    public void setFolioTicket(String folioTicket) {
        this.folioTicket = folioTicket;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Integer getTipoSolicitudId() {
        return tipoSolicitudId;
    }

    public void setTipoSolicitudId(Integer tipoSolicitudId) {
        this.tipoSolicitudId = tipoSolicitudId;
    }

    public Integer getTipoEntregaId() {
        return tipoEntregaId;
    }

    public void setTipoEntregaId(Integer tipoEntregaId) {
        this.tipoEntregaId = tipoEntregaId;
    }

    public Integer getEstatusId() {
        return estatusId;
    }

    public void setEstatusId(Integer estatusId) {
        this.estatusId = estatusId;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getTiempoParadaDestino() {
        return tiempoParadaDestino;
    }

    public void setTiempoParadaDestino(String tiempoParadaDestino) {
        this.tiempoParadaDestino = tiempoParadaDestino;
    }

    public Integer getTipoVehiculoId() {
        return tipoVehiculoId;
    }

    public void setTipoVehiculoId(Integer tipoVehiculoId) {
        this.tipoVehiculoId = tipoVehiculoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
