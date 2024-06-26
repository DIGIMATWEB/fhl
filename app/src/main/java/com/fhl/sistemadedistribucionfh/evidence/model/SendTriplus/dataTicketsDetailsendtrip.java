
package com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus;

import java.io.Serializable;
import java.util.List;

import com.fhl.sistemadedistribucionfh.Tickets.model.ticketsdetail.Check;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dataTicketsDetailsendtrip implements Serializable {

    @SerializedName("DocumentosVehiculo")
   
    private List<DocumentosVehiculo> documentosVehiculo;
    @SerializedName("HabilidadesOperador")
   
    private List<HabilidadesOperador> habilidadesOperador;
    @SerializedName("DocumentosOperador")
   
    private List<DocumentosOperador> documentosOperador;
    @SerializedName("HabilidadesAuxiliar")
   
    private List<HabilidadesAuxiliar> habilidadesAuxiliar;
    @SerializedName("DocumentosAuxiliar")
   
    private List<DocumentosAuxiliar> documentosAuxiliar;
    @SerializedName("EvidenciaSalida")
   
    private List<EvidenciaSalida> evidenciaSalida;
    @SerializedName("EvidenciaLlegada")
   
    private List<EvidenciaLlegada> evidenciaLlegada;
    @SerializedName("CheckList")
   
    private List<Check> checkList;
    @SerializedName("Cliente")
    private Cliente cliente;
    @SerializedName("TipoVehiculo")
    private TipoVehiculo tipoVehiculo;
    @SerializedName("TipoEntrega")
   
    private TipoEntrega tipoEntrega;
    @SerializedName("Estatus")
   
    private Estatus estatus;
    @SerializedName("SendtripPlus")
   
    private SendtripPlus sendtripPlus;
    @SerializedName("FolioTicket")
   
    private String folioTicket;
    @SerializedName("Origen")
   
    private String origen;
    @SerializedName("ClienteId")
   
    private Integer clienteId;
    @SerializedName("Referencia")
   
    private String referencia;
    @SerializedName("TipoSolicitudId")
   
    private Integer tipoSolicitudId;
    @SerializedName("TipoEntregaId")
   
    private Integer tipoEntregaId;
    @SerializedName("EstatusId")
   
    private Integer estatusId;
    @SerializedName("Comentarios")
   
    private String comentarios;
    @SerializedName("Secuencia")
   
    private String secuencia;
    @SerializedName("TiempoParadaDestino")
   
    private String tiempoParadaDestino;
    @SerializedName("TipoVehiculoId")
   
    private Integer tipoVehiculoId;
    @SerializedName("Id")
   
    private Integer id;
    @SerializedName("VehiculoId")
   
    private Integer vehiculoId;
    @SerializedName("Vehiculo")
   
    private VehiculoSendtrip vehiculo;
    public dataTicketsDetailsendtrip(List<DocumentosVehiculo> documentosVehiculo, List<HabilidadesOperador> habilidadesOperador,
                                     List<DocumentosOperador> documentosOperador, List<HabilidadesAuxiliar> habilidadesAuxiliar,
                                     List<DocumentosAuxiliar> documentosAuxiliar, List<EvidenciaSalida> evidenciaSalida,
                                     List<EvidenciaLlegada> evidenciaLlegada, List<Check> checkList, Cliente cliente, TipoVehiculo tipoVehiculo,
                                     TipoEntrega tipoEntrega, Estatus estatus, SendtripPlus sendtripPlus, String folioTicket, String origen,
                                     Integer clienteId, String referencia, Integer tipoSolicitudId, Integer tipoEntregaId, Integer estatusId,
                                     String comentarios, String secuencia, String tiempoParadaDestino, Integer tipoVehiculoId, Integer id,Integer vehiculoId,VehiculoSendtrip vehiculo
    ) {
        super();
        this.documentosVehiculo = documentosVehiculo;
        this.habilidadesOperador = habilidadesOperador;
        this.documentosOperador = documentosOperador;
        this.habilidadesAuxiliar = habilidadesAuxiliar;
        this.documentosAuxiliar = documentosAuxiliar;
        this.evidenciaSalida = evidenciaSalida;
        this.evidenciaLlegada = evidenciaLlegada;
        this.checkList = checkList;
        this.cliente = cliente;
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
        this.secuencia = secuencia;
        this.tiempoParadaDestino = tiempoParadaDestino;
        this.tipoVehiculoId = tipoVehiculoId;
        this.id = id;
        this.vehiculoId = vehiculoId;
        this.vehiculo = vehiculo;
    }
    public Integer getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Integer vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public VehiculoSendtrip getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoSendtrip vehiculo) {
        this.vehiculo = vehiculo;
    }
    public List<DocumentosVehiculo> getDocumentosVehiculo() {
        return documentosVehiculo;
    }

    public void setDocumentosVehiculo(List<DocumentosVehiculo> documentosVehiculo) {
        this.documentosVehiculo = documentosVehiculo;
    }

    public List<HabilidadesOperador> getHabilidadesOperador() {
        return habilidadesOperador;
    }

    public void setHabilidadesOperador(List<HabilidadesOperador> habilidadesOperador) {
        this.habilidadesOperador = habilidadesOperador;
    }

    public List<DocumentosOperador> getDocumentosOperador() {
        return documentosOperador;
    }

    public void setDocumentosOperador(List<DocumentosOperador> documentosOperador) {
        this.documentosOperador = documentosOperador;
    }

    public List<HabilidadesAuxiliar> getHabilidadesAuxiliar() {
        return habilidadesAuxiliar;
    }

    public void setHabilidadesAuxiliar(List<HabilidadesAuxiliar> habilidadesAuxiliar) {
        this.habilidadesAuxiliar = habilidadesAuxiliar;
    }

    public List<DocumentosAuxiliar> getDocumentosAuxiliar() {
        return documentosAuxiliar;
    }

    public void setDocumentosAuxiliar(List<DocumentosAuxiliar> documentosAuxiliar) {
        this.documentosAuxiliar = documentosAuxiliar;
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

    public List<Check> getCheckList() {
        return checkList;
    }

    public void setCheckList(List<Check> checkList) {
        this.checkList = checkList;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public String getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
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
