
package com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class dataTicketsManifestV2 implements Serializable {

    @SerializedName("DespachoId")
    @Expose
    private Integer despachoId;
    @SerializedName("TicketId")
    @Expose
    private Integer ticketId;
    @SerializedName("FolioTicket")
    @Expose
    private String folioTicket;
    @SerializedName("FolioTicketWMS")
    @Expose
    private String folioTicketWMS;
    @SerializedName("TipoFolio")
    @Expose
    private String tipoFolio;
    @SerializedName("Origen")
    @Expose
    private String origen;
    @SerializedName("ClienteId")
    @Expose
    private Integer clienteId;
    @SerializedName("DestinatariosId")
    @Expose
    private Integer destinatariosId;
    @SerializedName("Referencia")
    @Expose
    private String referencia;
    @SerializedName("SolicitaServicio")
    @Expose
    private String solicitaServicio;
    @SerializedName("FechaSolicitud")
    @Expose
    private String fechaSolicitud;
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
    @SerializedName("Cantidad")
    @Expose
    private Integer cantidad;
    @SerializedName("SumaAsegurada")
    @Expose
    private Integer sumaAsegurada;
    @SerializedName("RutaId")
    @Expose
    private Integer rutaId;
    @SerializedName("TipoVehiculoId")
    @Expose
    private Integer tipoVehiculoId;
    @SerializedName("Maniobras")
    @Expose
    private Integer maniobras;
    @SerializedName("Custodia")
    @Expose
    private String custodia;
    @SerializedName("CustodiaArmada")
    @Expose
    private String custodiaArmada;
    @SerializedName("TipoCustodiaId")
    @Expose
    private Integer tipoCustodiaId;
    @SerializedName("RequiereEvidenciaSeguroSocial")
    @Expose
    private String requiereEvidenciaSeguroSocial;
    @SerializedName("Seguro")
    @Expose
    private Boolean seguro;
    @SerializedName("ServicioCobro")
    @Expose
    private Boolean servicioCobro;
    @SerializedName("DestinatariosClienteId")
    @Expose
    private Integer destinatariosClienteId;



    /**
     * 
     * @param seguro
     * @param fechaPromesaRetorno
     * @param rutaId
     * @param tipoFolio
     * @param fechaVentanaFin
     * @param custodiaArmada
     * @param folioTicket
     * @param origen
     * @param estatusId
     * @param tipoSolicitudId
     * @param fechaRestriccionCirculacionInicio
     * @param destinatariosClienteId
     * @param tiempoCarga
     * @param fechaRestriccionCirculacionFin
     * @param fechaPromesaLlegadaOrigen
     * @param maniobras
     * @param fechaVentanaInicio
     * @param comentarios
     * @param destinatariosId
     * @param solicitaServicio
     * @param custodia
     * @param fechaSolicitud
     * @param secuencia
     * @param fechaPromesaCarga
     * @param tipoEntregaId
     * @param folioTicketWMS
     * @param sumaAsegurada
     * @param tipoVehiculoId
     * @param clienteId
     * @param tiempoParadaDestino
     * @param fechaPromesaEntrega
     * @param despachoId
     * @param cantidad
     * @param tipoCustodiaId
     * @param servicioCobro
     * @param ticketId
     * @param referencia
     * @param requiereEvidenciaSeguroSocial
     */
    public dataTicketsManifestV2(Integer despachoId, Integer ticketId, String folioTicket, String folioTicketWMS, String tipoFolio, String origen, Integer clienteId, Integer destinatariosId, String referencia, String solicitaServicio, String fechaSolicitud, Integer tipoSolicitudId, Integer tipoEntregaId, String comentarios, Integer estatusId, String secuencia, String fechaPromesaLlegadaOrigen, String fechaPromesaCarga, String fechaPromesaEntrega, String fechaPromesaRetorno, String tiempoCarga, String tiempoParadaDestino, String fechaVentanaInicio, String fechaVentanaFin, String fechaRestriccionCirculacionInicio, String fechaRestriccionCirculacionFin, Integer cantidad, Integer sumaAsegurada, Integer rutaId, Integer tipoVehiculoId, Integer maniobras, String custodia, String custodiaArmada, Integer tipoCustodiaId, String requiereEvidenciaSeguroSocial, Boolean seguro, Boolean servicioCobro, Integer destinatariosClienteId) {
        super();
        this.despachoId = despachoId;
        this.ticketId = ticketId;
        this.folioTicket = folioTicket;
        this.folioTicketWMS = folioTicketWMS;
        this.tipoFolio = tipoFolio;
        this.origen = origen;
        this.clienteId = clienteId;
        this.destinatariosId = destinatariosId;
        this.referencia = referencia;
        this.solicitaServicio = solicitaServicio;
        this.fechaSolicitud = fechaSolicitud;
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
        this.cantidad = cantidad;
        this.sumaAsegurada = sumaAsegurada;
        this.rutaId = rutaId;
        this.tipoVehiculoId = tipoVehiculoId;
        this.maniobras = maniobras;
        this.custodia = custodia;
        this.custodiaArmada = custodiaArmada;
        this.tipoCustodiaId = tipoCustodiaId;
        this.requiereEvidenciaSeguroSocial = requiereEvidenciaSeguroSocial;
        this.seguro = seguro;
        this.servicioCobro = servicioCobro;
        this.destinatariosClienteId = destinatariosClienteId;
    }

    public Integer getDespachoId() {
        return despachoId;
    }

    public void setDespachoId(Integer despachoId) {
        this.despachoId = despachoId;
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

    public String getFolioTicketWMS() {
        return folioTicketWMS;
    }

    public void setFolioTicketWMS(String folioTicketWMS) {
        this.folioTicketWMS = folioTicketWMS;
    }

    public String getTipoFolio() {
        return tipoFolio;
    }

    public void setTipoFolio(String tipoFolio) {
        this.tipoFolio = tipoFolio;
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

    public Integer getDestinatariosId() {
        return destinatariosId;
    }

    public void setDestinatariosId(Integer destinatariosId) {
        this.destinatariosId = destinatariosId;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getSolicitaServicio() {
        return solicitaServicio;
    }

    public void setSolicitaServicio(String solicitaServicio) {
        this.solicitaServicio = solicitaServicio;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
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

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getSumaAsegurada() {
        return sumaAsegurada;
    }

    public void setSumaAsegurada(Integer sumaAsegurada) {
        this.sumaAsegurada = sumaAsegurada;
    }

    public Integer getRutaId() {
        return rutaId;
    }

    public void setRutaId(Integer rutaId) {
        this.rutaId = rutaId;
    }

    public Integer getTipoVehiculoId() {
        return tipoVehiculoId;
    }

    public void setTipoVehiculoId(Integer tipoVehiculoId) {
        this.tipoVehiculoId = tipoVehiculoId;
    }

    public Integer getManiobras() {
        return maniobras;
    }

    public void setManiobras(Integer maniobras) {
        this.maniobras = maniobras;
    }

    public String getCustodia() {
        return custodia;
    }

    public void setCustodia(String custodia) {
        this.custodia = custodia;
    }

    public String getCustodiaArmada() {
        return custodiaArmada;
    }

    public void setCustodiaArmada(String custodiaArmada) {
        this.custodiaArmada = custodiaArmada;
    }

    public Integer getTipoCustodiaId() {
        return tipoCustodiaId;
    }

    public void setTipoCustodiaId(Integer tipoCustodiaId) {
        this.tipoCustodiaId = tipoCustodiaId;
    }

    public String getRequiereEvidenciaSeguroSocial() {
        return requiereEvidenciaSeguroSocial;
    }

    public void setRequiereEvidenciaSeguroSocial(String requiereEvidenciaSeguroSocial) {
        this.requiereEvidenciaSeguroSocial = requiereEvidenciaSeguroSocial;
    }

    public Boolean getSeguro() {
        return seguro;
    }

    public void setSeguro(Boolean seguro) {
        this.seguro = seguro;
    }

    public Boolean getServicioCobro() {
        return servicioCobro;
    }

    public void setServicioCobro(Boolean servicioCobro) {
        this.servicioCobro = servicioCobro;
    }

    public Integer getDestinatariosClienteId() {
        return destinatariosClienteId;
    }

    public void setDestinatariosClienteId(Integer destinatariosClienteId) {
        this.destinatariosClienteId = destinatariosClienteId;
    }

}
