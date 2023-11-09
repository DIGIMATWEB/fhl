
package com.fhl.sistemadedistribucionfh.Tickets.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dataDetailTickets {

    @SerializedName("habilidadesVehiculo")
    @Expose
    private List<HabilidadesVehiculo> habilidadesVehiculo;
    @SerializedName("empaque")
    @Expose
    private List<Empaque> empaque;
    @SerializedName("documentosVehiculo")
    @Expose
    private List<DocumentosVehiculo> documentosVehiculo;
    @SerializedName("habilidadesOperador")
    @Expose
    private Object habilidadesOperador;
    @SerializedName("documentosOperador")
    @Expose
    private List<DocumentosOperador> documentosOperador;
    @SerializedName("habilidadesAuxiliar")
    @Expose
    private List<HabilidadesAuxiliar> habilidadesAuxiliar;
    @SerializedName("documentosAuxiliar")
    @Expose
    private List<DocumentosAuxiliar> documentosAuxiliar;
    @SerializedName("evidenciaSalida")
    @Expose
    private List<EvidenciaSalida> evidenciaSalida;
    @SerializedName("evidenciaLlegada")
    @Expose
    private List<EvidenciaLlegada> evidenciaLlegada;
    @SerializedName("checkList")
    @Expose
    private List<Check> checkList;
    @SerializedName("peligroso")
    @Expose
    private List<Object> peligroso;
    @SerializedName("destinatarios")
    @Expose
    private Destinatarios destinatarios;
    @SerializedName("folioTicket")
    @Expose
    private String folioTicket;
    @SerializedName("folioTicketWMS")
    @Expose
    private String folioTicketWMS;
    @SerializedName("tipoFolio")
    @Expose
    private String tipoFolio;
    @SerializedName("origen")
    @Expose
    private String origen;
    @SerializedName("clienteId")
    @Expose
    private Integer clienteId;
    @SerializedName("destinatariosId")
    @Expose
    private Integer destinatariosId;
    @SerializedName("referencia")
    @Expose
    private String referencia;
    @SerializedName("solicitaServicio")
    @Expose
    private String solicitaServicio;
    @SerializedName("fechaSolicitud")
    @Expose
    private String fechaSolicitud;
    @SerializedName("tipoSolicitudId")
    @Expose
    private Integer tipoSolicitudId;
    @SerializedName("tipoEntregaId")
    @Expose
    private Integer tipoEntregaId;
    @SerializedName("estatusId")
    @Expose
    private Integer estatusId;
    @SerializedName("comentarios")
    @Expose
    private String comentarios;
    @SerializedName("tipoRecepcion")
    @Expose
    private Object tipoRecepcion;
    @SerializedName("secuencia")
    @Expose
    private String secuencia;
    @SerializedName("fechaPromesaLlegadaOrigen")
    @Expose
    private String fechaPromesaLlegadaOrigen;
    @SerializedName("fechaSalidaEstimada")
    @Expose
    private Object fechaSalidaEstimada;
    @SerializedName("fechaPromesaCarga")
    @Expose
    private String fechaPromesaCarga;
    @SerializedName("fechaPromesaEntrega")
    @Expose
    private String fechaPromesaEntrega;
    @SerializedName("fechaPromesaRetorno")
    @Expose
    private String fechaPromesaRetorno;
    @SerializedName("tiempoCarga")
    @Expose
    private String tiempoCarga;
    @SerializedName("tiempoParadaDestino")
    @Expose
    private String tiempoParadaDestino;
    @SerializedName("fechaVentanaInicio")
    @Expose
    private String fechaVentanaInicio;
    @SerializedName("fechaVentanaFin")
    @Expose
    private String fechaVentanaFin;
    @SerializedName("fechaRestriccionCirculacionInicio")
    @Expose
    private String fechaRestriccionCirculacionInicio;
    @SerializedName("fechaRestriccionCirculacionFin")
    @Expose
    private String fechaRestriccionCirculacionFin;
    @SerializedName("rutaId")
    @Expose
    private Integer rutaId;
    @SerializedName("tipoVehiculoId")
    @Expose
    private Integer tipoVehiculoId;
    @SerializedName("maniobras")
    @Expose
    private Integer maniobras;
    @SerializedName("custodia")
    @Expose
    private String custodia;
    @SerializedName("custodiaArmada")
    @Expose
    private String custodiaArmada;
    @SerializedName("tipoCustodiaId")
    @Expose
    private Integer tipoCustodiaId;
    @SerializedName("requiereEvidenciaSeguroSocial")
    @Expose
    private String requiereEvidenciaSeguroSocial;
    @SerializedName("seguro")
    @Expose
    private Boolean seguro;
    @SerializedName("servicioCobro")
    @Expose
    private Boolean servicioCobro;
    @SerializedName("servicioAdicional")
    @Expose
    private Object servicioAdicional;
    @SerializedName("recepcionTicket")
    @Expose
    private Object recepcionTicket;
    @SerializedName("asignacionManifiesto")
    @Expose
    private Object asignacionManifiesto;
    @SerializedName("inicioEscaneoRecepcionProducto")
    @Expose
    private Object inicioEscaneoRecepcionProducto;
    @SerializedName("finEscaneoRecepcionProducto")
    @Expose
    private Object finEscaneoRecepcionProducto;
    @SerializedName("inicioEntregaProducto")
    @Expose
    private Object inicioEntregaProducto;
    @SerializedName("finEntregaProducto")
    @Expose
    private Object finEntregaProducto;
    @SerializedName("usuario")
    @Expose
    private Object usuario;
    @SerializedName("cliente")
    @Expose
    private Cliente cliente;
    @SerializedName("tipoVehiculo")
    @Expose
    private TipoVehiculo tipoVehiculo;
    @SerializedName("ruta")
    @Expose
    private Object ruta;
    @SerializedName("tipoCustodia")
    @Expose
    private TipoCustodia tipoCustodia;
    @SerializedName("destinatariosClienteId")
    @Expose
    private Integer destinatariosClienteId;
    @SerializedName("cantidad")
    @Expose
    private Integer cantidad;
    @SerializedName("sumaAsegurada")
    @Expose
    private Integer sumaAsegurada;
    @SerializedName("seguroId")
    @Expose
    private Object seguroId;
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

    public dataDetailTickets(List<HabilidadesVehiculo> habilidadesVehiculo, List<Empaque> empaque, List<DocumentosVehiculo> documentosVehiculo, Object habilidadesOperador, List<DocumentosOperador> documentosOperador, List<HabilidadesAuxiliar> habilidadesAuxiliar, List<DocumentosAuxiliar> documentosAuxiliar, List<EvidenciaSalida> evidenciaSalida, List<EvidenciaLlegada> evidenciaLlegada, List<Check> checkList, List<Object> peligroso, Destinatarios destinatarios, String folioTicket, String folioTicketWMS, String tipoFolio, String origen, Integer clienteId, Integer destinatariosId, String referencia, String solicitaServicio, String fechaSolicitud, Integer tipoSolicitudId, Integer tipoEntregaId, Integer estatusId, String comentarios, Object tipoRecepcion, String secuencia, String fechaPromesaLlegadaOrigen, Object fechaSalidaEstimada, String fechaPromesaCarga, String fechaPromesaEntrega, String fechaPromesaRetorno, String tiempoCarga, String tiempoParadaDestino, String fechaVentanaInicio, String fechaVentanaFin, String fechaRestriccionCirculacionInicio, String fechaRestriccionCirculacionFin, Integer rutaId, Integer tipoVehiculoId, Integer maniobras, String custodia, String custodiaArmada, Integer tipoCustodiaId, String requiereEvidenciaSeguroSocial, Boolean seguro, Boolean servicioCobro, Object servicioAdicional, Object recepcionTicket, Object asignacionManifiesto, Object inicioEscaneoRecepcionProducto, Object finEscaneoRecepcionProducto, Object inicioEntregaProducto, Object finEntregaProducto, Object usuario, Cliente cliente, TipoVehiculo tipoVehiculo, Object ruta, TipoCustodia tipoCustodia, Integer destinatariosClienteId, Integer cantidad, Integer sumaAsegurada, Object seguroId, Integer id, Object trail, String fechaCreacion, Boolean eliminado, Boolean esNuevo) {
        super();
        this.habilidadesVehiculo = habilidadesVehiculo;
        this.empaque = empaque;
        this.documentosVehiculo = documentosVehiculo;
        this.habilidadesOperador = habilidadesOperador;
        this.documentosOperador = documentosOperador;
        this.habilidadesAuxiliar = habilidadesAuxiliar;
        this.documentosAuxiliar = documentosAuxiliar;
        this.evidenciaSalida = evidenciaSalida;
        this.evidenciaLlegada = evidenciaLlegada;
        this.checkList = checkList;
        this.peligroso = peligroso;
        this.destinatarios = destinatarios;
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
        this.estatusId = estatusId;
        this.comentarios = comentarios;
        this.tipoRecepcion = tipoRecepcion;
        this.secuencia = secuencia;
        this.fechaPromesaLlegadaOrigen = fechaPromesaLlegadaOrigen;
        this.fechaSalidaEstimada = fechaSalidaEstimada;
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
        this.tipoVehiculoId = tipoVehiculoId;
        this.maniobras = maniobras;
        this.custodia = custodia;
        this.custodiaArmada = custodiaArmada;
        this.tipoCustodiaId = tipoCustodiaId;
        this.requiereEvidenciaSeguroSocial = requiereEvidenciaSeguroSocial;
        this.seguro = seguro;
        this.servicioCobro = servicioCobro;
        this.servicioAdicional = servicioAdicional;
        this.recepcionTicket = recepcionTicket;
        this.asignacionManifiesto = asignacionManifiesto;
        this.inicioEscaneoRecepcionProducto = inicioEscaneoRecepcionProducto;
        this.finEscaneoRecepcionProducto = finEscaneoRecepcionProducto;
        this.inicioEntregaProducto = inicioEntregaProducto;
        this.finEntregaProducto = finEntregaProducto;
        this.usuario = usuario;
        this.cliente = cliente;
        this.tipoVehiculo = tipoVehiculo;
        this.ruta = ruta;
        this.tipoCustodia = tipoCustodia;
        this.destinatariosClienteId = destinatariosClienteId;
        this.cantidad = cantidad;
        this.sumaAsegurada = sumaAsegurada;
        this.seguroId = seguroId;
        this.id = id;
        this.trail = trail;
        this.fechaCreacion = fechaCreacion;
        this.eliminado = eliminado;
        this.esNuevo = esNuevo;
    }

    public List<HabilidadesVehiculo> getHabilidadesVehiculo() {
        return habilidadesVehiculo;
    }

    public void setHabilidadesVehiculo(List<HabilidadesVehiculo> habilidadesVehiculo) {
        this.habilidadesVehiculo = habilidadesVehiculo;
    }

    public List<Empaque> getEmpaque() {
        return empaque;
    }

    public void setEmpaque(List<Empaque> empaque) {
        this.empaque = empaque;
    }

    public List<DocumentosVehiculo> getDocumentosVehiculo() {
        return documentosVehiculo;
    }

    public void setDocumentosVehiculo(List<DocumentosVehiculo> documentosVehiculo) {
        this.documentosVehiculo = documentosVehiculo;
    }

    public Object getHabilidadesOperador() {
        return habilidadesOperador;
    }

    public void setHabilidadesOperador(Object habilidadesOperador) {
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

    public List<Object> getPeligroso() {
        return peligroso;
    }

    public void setPeligroso(List<Object> peligroso) {
        this.peligroso = peligroso;
    }

    public Destinatarios getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(Destinatarios destinatarios) {
        this.destinatarios = destinatarios;
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

    public Object getTipoRecepcion() {
        return tipoRecepcion;
    }

    public void setTipoRecepcion(Object tipoRecepcion) {
        this.tipoRecepcion = tipoRecepcion;
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

    public Object getFechaSalidaEstimada() {
        return fechaSalidaEstimada;
    }

    public void setFechaSalidaEstimada(Object fechaSalidaEstimada) {
        this.fechaSalidaEstimada = fechaSalidaEstimada;
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

    public Object getServicioAdicional() {
        return servicioAdicional;
    }

    public void setServicioAdicional(Object servicioAdicional) {
        this.servicioAdicional = servicioAdicional;
    }

    public Object getRecepcionTicket() {
        return recepcionTicket;
    }

    public void setRecepcionTicket(Object recepcionTicket) {
        this.recepcionTicket = recepcionTicket;
    }

    public Object getAsignacionManifiesto() {
        return asignacionManifiesto;
    }

    public void setAsignacionManifiesto(Object asignacionManifiesto) {
        this.asignacionManifiesto = asignacionManifiesto;
    }

    public Object getInicioEscaneoRecepcionProducto() {
        return inicioEscaneoRecepcionProducto;
    }

    public void setInicioEscaneoRecepcionProducto(Object inicioEscaneoRecepcionProducto) {
        this.inicioEscaneoRecepcionProducto = inicioEscaneoRecepcionProducto;
    }

    public Object getFinEscaneoRecepcionProducto() {
        return finEscaneoRecepcionProducto;
    }

    public void setFinEscaneoRecepcionProducto(Object finEscaneoRecepcionProducto) {
        this.finEscaneoRecepcionProducto = finEscaneoRecepcionProducto;
    }

    public Object getInicioEntregaProducto() {
        return inicioEntregaProducto;
    }

    public void setInicioEntregaProducto(Object inicioEntregaProducto) {
        this.inicioEntregaProducto = inicioEntregaProducto;
    }

    public Object getFinEntregaProducto() {
        return finEntregaProducto;
    }

    public void setFinEntregaProducto(Object finEntregaProducto) {
        this.finEntregaProducto = finEntregaProducto;
    }

    public Object getUsuario() {
        return usuario;
    }

    public void setUsuario(Object usuario) {
        this.usuario = usuario;
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

    public Object getRuta() {
        return ruta;
    }

    public void setRuta(Object ruta) {
        this.ruta = ruta;
    }

    public TipoCustodia getTipoCustodia() {
        return tipoCustodia;
    }

    public void setTipoCustodia(TipoCustodia tipoCustodia) {
        this.tipoCustodia = tipoCustodia;
    }

    public Integer getDestinatariosClienteId() {
        return destinatariosClienteId;
    }

    public void setDestinatariosClienteId(Integer destinatariosClienteId) {
        this.destinatariosClienteId = destinatariosClienteId;
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

    public Object getSeguroId() {
        return seguroId;
    }

    public void setSeguroId(Object seguroId) {
        this.seguroId = seguroId;
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
