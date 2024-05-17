
package com.fhl.sistemadedistribucionfh.Tickets.model.ticketsdetail;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dataDetailTickets implements Serializable {

    @SerializedName("TicketId")
    @Expose
    private Integer ticketId;
    @SerializedName("FolioTicket")
    @Expose
    private String folioTicket;
    @SerializedName("Origen")
    @Expose
    private String origen;
    @SerializedName("ClienteId")
    @Expose
    private Integer clienteId;
    @SerializedName("Cliente")
    @Expose
    private Cliente cliente;
    @SerializedName("DestinatariosId")
    @Expose
    private Integer destinatariosId;
//    @SerializedName("Destinatarios")
//    @Expose
//    private Destinatarios destinatarios;
    @SerializedName("Referencia")
    @Expose
    private String referencia;
    @SerializedName("TipoSolicitudId")
    @Expose
    private Integer tipoSolicitudId;
    @SerializedName("TipoEntregaId")
    @Expose
    private Integer tipoEntregaId;
    @SerializedName("Comentarios")
    @Expose
    private String comentarios;
    @SerializedName("EstatusId")
    @Expose
    private Integer estatusId;
    @SerializedName("Secuencia")
    @Expose
    private String secuencia;
    @SerializedName("FechaPromesaLlegadaOrigen")
    @Expose
    private String fechaPromesaLlegadaOrigen;
    @SerializedName("FechaPromesaCarga")
    @Expose
    private String fechaPromesaCarga;
    @SerializedName("FechaPromesaEntrega")
    @Expose
    private String fechaPromesaEntrega;
    @SerializedName("FechaPromesaRetorno")
    @Expose
    private String fechaPromesaRetorno;
    @SerializedName("TiempoCarga")
    @Expose
    private String tiempoCarga;
    @SerializedName("TiempoParadaDestino")
    @Expose
    private String tiempoParadaDestino;
    @SerializedName("FechaVentanaInicio")
    @Expose
    private String fechaVentanaInicio;
    @SerializedName("FechaVentanaFin")
    @Expose
    private String fechaVentanaFin;
    @SerializedName("FechaRestriccionCirculacionInicio")
    @Expose
    private String fechaRestriccionCirculacionInicio;
    @SerializedName("FechaRestriccionCirculacionFin")
    @Expose
    private String fechaRestriccionCirculacionFin;
    @SerializedName("RutaId")
    @Expose
    private Integer rutaId;
    @SerializedName("Rutas")
    @Expose
    private Rutas rutas;
    @SerializedName("TipoVehiculoId")
    @Expose
    private Integer tipoVehiculoId;
    @SerializedName("TipoVehiculo")
    @Expose
    private TipoVehiculo tipoVehiculo;
    @SerializedName("DocumentosVehiculo")
    @Expose
    private List<DocumentosVehiculo> documentosVehiculo;
    @SerializedName("HabilidadesOperadoro")
    @Expose
    private List<HabilidadesOperadoro> habilidadesOperadoro;
    @SerializedName("DocumentosOperador")
    @Expose
    private List<DocumentosOperador> documentosOperador;
    @SerializedName("HabilidadesAuxiliar")
    @Expose
    private List<HabilidadesAuxiliar> habilidadesAuxiliar;
    @SerializedName("DocumentosAuxiliar")
    @Expose
    private List<DocumentosAuxiliar> documentosAuxiliar;
    @SerializedName("EvidenciaSalida")
    @Expose
    private List<EvidenciaSalida> evidenciaSalida;
    @SerializedName("EvidenciaLlegada")
    @Expose
    private List<EvidenciaLlegada> evidenciaLlegada;
    @SerializedName("CheckList")
    @Expose
    private List<Check> checkList;
    @SerializedName("DestinatariosClienteId")
    @Expose
    private Integer destinatariosClienteId;

    public dataDetailTickets(Integer ticketId, String folioTicket, String origen, Integer clienteId, Cliente cliente, Integer destinatariosId,  String referencia, Integer tipoSolicitudId, Integer tipoEntregaId, String comentarios, Integer estatusId, String secuencia, String fechaPromesaLlegadaOrigen, String fechaPromesaCarga, String fechaPromesaEntrega, String fechaPromesaRetorno, String tiempoCarga, String tiempoParadaDestino, String fechaVentanaInicio, String fechaVentanaFin, String fechaRestriccionCirculacionInicio, String fechaRestriccionCirculacionFin, Integer rutaId, Rutas rutas, Integer tipoVehiculoId, TipoVehiculo tipoVehiculo, List<DocumentosVehiculo> documentosVehiculo, List<HabilidadesOperadoro> habilidadesOperadoro, List<DocumentosOperador> documentosOperador, List<HabilidadesAuxiliar> habilidadesAuxiliar, List<DocumentosAuxiliar> documentosAuxiliar, List<EvidenciaSalida> evidenciaSalida, List<EvidenciaLlegada> evidenciaLlegada, List<Check> checkList, Integer destinatariosClienteId) {
        super();
        this.ticketId = ticketId;
        this.folioTicket = folioTicket;
        this.origen = origen;
        this.clienteId = clienteId;
        this.cliente = cliente;
        this.destinatariosId = destinatariosId;
       // this.destinatarios = destinatarios;
        this.referencia = referencia;
        this.tipoSolicitudId = tipoSolicitudId;
        this.tipoEntregaId = tipoEntregaId;
        this.comentarios = comentarios;
        this.estatusId = estatusId;
        this.secuencia = secuencia;
        this.fechaPromesaLlegadaOrigen = fechaPromesaLlegadaOrigen;
        this.fechaPromesaCarga = fechaPromesaCarga;
        this.fechaPromesaEntrega = fechaPromesaEntrega;
        this.fechaPromesaRetorno = fechaPromesaRetorno;
        this.tiempoCarga = tiempoCarga;
        this.tiempoParadaDestino = tiempoParadaDestino;
        this.fechaVentanaInicio = fechaVentanaInicio;
        this.fechaVentanaFin = fechaVentanaFin;
        this.fechaRestriccionCirculacionInicio = fechaRestriccionCirculacionInicio;
        this.fechaRestriccionCirculacionFin = fechaRestriccionCirculacionFin;
        this.rutaId = rutaId;
        this.rutas = rutas;
        this.tipoVehiculoId = tipoVehiculoId;
        this.tipoVehiculo = tipoVehiculo;
        this.documentosVehiculo = documentosVehiculo;
        this.habilidadesOperadoro = habilidadesOperadoro;
        this.documentosOperador = documentosOperador;
        this.habilidadesAuxiliar = habilidadesAuxiliar;
        this.documentosAuxiliar = documentosAuxiliar;
        this.evidenciaSalida = evidenciaSalida;
        this.evidenciaLlegada = evidenciaLlegada;
        this.checkList = checkList;
        this.destinatariosClienteId = destinatariosClienteId;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getDestinatariosId() {
        return destinatariosId;
    }

    public void setDestinatariosId(Integer destinatariosId) {
        this.destinatariosId = destinatariosId;
    }

//    public Destinatarios getDestinatarios() {
//        return destinatarios;
//    }
//
//    public void setDestinatarios(Destinatarios destinatarios) {
//        this.destinatarios = destinatarios;
//    }

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

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Integer getEstatusId() {
        return estatusId;
    }

    public void setEstatusId(Integer estatusId) {
        this.estatusId = estatusId;
    }

    public String getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    public String getFechaPromesaLlegadaOrigen() {
        return fechaPromesaLlegadaOrigen;
    }

    public void setFechaPromesaLlegadaOrigen(String fechaPromesaLlegadaOrigen) {
        this.fechaPromesaLlegadaOrigen = fechaPromesaLlegadaOrigen;
    }

    public String getFechaPromesaCarga() {
        return fechaPromesaCarga;
    }

    public void setFechaPromesaCarga(String fechaPromesaCarga) {
        this.fechaPromesaCarga = fechaPromesaCarga;
    }

    public String getFechaPromesaEntrega() {
        return fechaPromesaEntrega;
    }

    public void setFechaPromesaEntrega(String fechaPromesaEntrega) {
        this.fechaPromesaEntrega = fechaPromesaEntrega;
    }

    public String getFechaPromesaRetorno() {
        return fechaPromesaRetorno;
    }

    public void setFechaPromesaRetorno(String fechaPromesaRetorno) {
        this.fechaPromesaRetorno = fechaPromesaRetorno;
    }

    public String getTiempoCarga() {
        return tiempoCarga;
    }

    public void setTiempoCarga(String tiempoCarga) {
        this.tiempoCarga = tiempoCarga;
    }

    public String getTiempoParadaDestino() {
        return tiempoParadaDestino;
    }

    public void setTiempoParadaDestino(String tiempoParadaDestino) {
        this.tiempoParadaDestino = tiempoParadaDestino;
    }

    public String getFechaVentanaInicio() {
        return fechaVentanaInicio;
    }

    public void setFechaVentanaInicio(String fechaVentanaInicio) {
        this.fechaVentanaInicio = fechaVentanaInicio;
    }

    public String getFechaVentanaFin() {
        return fechaVentanaFin;
    }

    public void setFechaVentanaFin(String fechaVentanaFin) {
        this.fechaVentanaFin = fechaVentanaFin;
    }

    public String getFechaRestriccionCirculacionInicio() {
        return fechaRestriccionCirculacionInicio;
    }

    public void setFechaRestriccionCirculacionInicio(String fechaRestriccionCirculacionInicio) {
        this.fechaRestriccionCirculacionInicio = fechaRestriccionCirculacionInicio;
    }

    public String getFechaRestriccionCirculacionFin() {
        return fechaRestriccionCirculacionFin;
    }

    public void setFechaRestriccionCirculacionFin(String fechaRestriccionCirculacionFin) {
        this.fechaRestriccionCirculacionFin = fechaRestriccionCirculacionFin;
    }

    public Integer getRutaId() {
        return rutaId;
    }

    public void setRutaId(Integer rutaId) {
        this.rutaId = rutaId;
    }

    public Rutas getRutas() {
        return rutas;
    }

    public void setRutas(Rutas rutas) {
        this.rutas = rutas;
    }

    public Integer getTipoVehiculoId() {
        return tipoVehiculoId;
    }

    public void setTipoVehiculoId(Integer tipoVehiculoId) {
        this.tipoVehiculoId = tipoVehiculoId;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public List<DocumentosVehiculo> getDocumentosVehiculo() {
        return documentosVehiculo;
    }

    public void setDocumentosVehiculo(List<DocumentosVehiculo> documentosVehiculo) {
        this.documentosVehiculo = documentosVehiculo;
    }

    public List<HabilidadesOperadoro> getHabilidadesOperadoro() {
        return habilidadesOperadoro;
    }

    public void setHabilidadesOperadoro(List<HabilidadesOperadoro> habilidadesOperadoro) {
        this.habilidadesOperadoro = habilidadesOperadoro;
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

    public Integer getDestinatariosClienteId() {
        return destinatariosClienteId;
    }

    public void setDestinatariosClienteId(Integer destinatariosClienteId) {
        this.destinatariosClienteId = destinatariosClienteId;
    }

}
