package com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2;

import com.google.gson.annotations.SerializedName;

public class dataTicketsManifestV2 {
    @SerializedName("folioTicket")
    public String folioTicket;
    @SerializedName("folioTicketWMS")
    public String folioTicketWMS;
    @SerializedName("tipoFolio")
    public String tipoFolio;
    @SerializedName("origen")
    public String origen;
    @SerializedName("clienteId")
    public Integer clienteId;
    @SerializedName("destinatariosId")
    public Integer destinatariosId;
    @SerializedName("referencia")
    public String referencia;
    @SerializedName("solicitaServicio")
    public String solicitaServicio;
    @SerializedName("fechaSolicitud")
    public String fechaSolicitud;
    @SerializedName("tipoSolicitudId")
    public Integer tipoSolicitudId;
    @SerializedName("tipoEntregaId")
    public Integer tipoEntregaId;
    @SerializedName("estatusId")
    public Integer estatusId;
    @SerializedName("comentarios")
    public String comentarios;
    @SerializedName("tipoRecepcion")
    public Object tipoRecepcion;
    @SerializedName("secuencia")
    public String secuencia;
    @SerializedName("fechaPromesaLlegadaOrigen")
    public String fechaPromesaLlegadaOrigen;
    @SerializedName("fechaSalidaEstimada")
    public Object fechaSalidaEstimada;
    @SerializedName("fechaPromesaCarga")
    public String fechaPromesaCarga;
    @SerializedName("fechaPromesaEntrega")
    public String fechaPromesaEntrega;
    @SerializedName("fechaPromesaRetorno")
    public String fechaPromesaRetorno;
    @SerializedName("tiempoCarga")
    public String tiempoCarga;
    @SerializedName("tiempoParadaDestino")
    public String tiempoParadaDestino;
    @SerializedName("fechaVentanaInicio")
    public String fechaVentanaInicio;
    @SerializedName("fechaVentanaFin")
    public String fechaVentanaFin;
    @SerializedName("fechaRestriccionCirculacionInicio")
    public String fechaRestriccionCirculacionInicio;
    @SerializedName("fechaRestriccionCirculacionFin")
    public String fechaRestriccionCirculacionFin;
    @SerializedName("empaque")
    public Object empaque;
    @SerializedName("rutaId")
    public Integer rutaId;
    @SerializedName("tipoVehiculoId")
    public Integer tipoVehiculoId;
    @SerializedName("habilidadesVehiculo")
    public Object habilidadesVehiculo;
    @SerializedName("documentosVehiculo")
    public Object documentosVehiculo;
    @SerializedName("habilidadesOperador")
    public Object habilidadesOperador;
    @SerializedName("documentosOperador")
    public Object documentosOperador;
    @SerializedName("habilidadesAuxiliar")
    public Object habilidadesAuxiliar;
    @SerializedName("documentosAuxiliar")
    public Object documentosAuxiliar;
    @SerializedName("evidenciaSalida")
    public Object evidenciaSalida;
    @SerializedName("evidenciaLlegada")
    public Object evidenciaLlegada;
    @SerializedName("checkList")
    public Object checkList;
    @SerializedName("maniobras")
    public Integer maniobras;
    @SerializedName("peligroso")
    public Object peligroso;
    @SerializedName("custodia")
    public String custodia;
    @SerializedName("custodiaArmada")
    public String custodiaArmada;
    @SerializedName("tipoCustodiaId")
    public Integer tipoCustodiaId;
    @SerializedName("requiereEvidenciaSeguroSocial")
    public String requiereEvidenciaSeguroSocial;
    @SerializedName("seguro")
    public Boolean seguro;
    @SerializedName("servicioCobro")
    public Boolean servicioCobro;
    @SerializedName("servicioAdicional")
    public Object servicioAdicional;
    @SerializedName("recepcionTicket")
    public Object recepcionTicket;
    @SerializedName("asignacionManifiesto")
    public Object asignacionManifiesto;
    @SerializedName("inicioEscaneoRecepcionProducto")
    public Object inicioEscaneoRecepcionProducto;
    @SerializedName("finEscaneoRecepcionProducto")
    public Object finEscaneoRecepcionProducto;
    @SerializedName("inicioEntregaProducto")
    public Object inicioEntregaProducto;
    @SerializedName("finEntregaProducto")
    public Object finEntregaProducto;
    @SerializedName("usuario")
    public Object usuario;
    @SerializedName("cliente")
    public Object cliente;
    @SerializedName("destinatarios")
    public Object destinatarios;
    @SerializedName("tipoVehiculo")
    public Object tipoVehiculo;
    @SerializedName("ruta")
    public Object ruta;
    @SerializedName("tipoCustodia")
    public Object tipoCustodia;
    @SerializedName("destinatariosClienteId")
    public Integer destinatariosClienteId;
    @SerializedName("cantidad")
    public Integer cantidad;
    @SerializedName("sumaAsegurada")
    public Integer sumaAsegurada;
    @SerializedName("seguroId")
    public Object seguroId;
    @SerializedName("id")
    public Integer id;
    @SerializedName("trail")
    public Object trail;
    @SerializedName("fechaCreacion")
    public String fechaCreacion;
    @SerializedName("eliminado")
    public boolean eliminado;
    @SerializedName("esNuevo")
    public boolean esNuevo;

    public dataTicketsManifestV2(String folioTicket, String folioTicketWMS, String tipoFolio, String origen, Integer clienteId, Integer destinatariosId, String referencia, String solicitaServicio, String fechaSolicitud, Integer tipoSolicitudId, Integer tipoEntregaId, Integer estatusId, String comentarios, Object tipoRecepcion, String secuencia, String fechaPromesaLlegadaOrigen, Object fechaSalidaEstimada, String fechaPromesaCarga, String fechaPromesaEntrega, String fechaPromesaRetorno, String tiempoCarga, String tiempoParadaDestino, String fechaVentanaInicio, String fechaVentanaFin, String fechaRestriccionCirculacionInicio, String fechaRestriccionCirculacionFin, Object empaque, Integer rutaId, Integer tipoVehiculoId, Object habilidadesVehiculo, Object documentosVehiculo, Object habilidadesOperador, Object documentosOperador, Object habilidadesAuxiliar, Object documentosAuxiliar, Object evidenciaSalida, Object evidenciaLlegada, Object checkList, Integer maniobras, Object peligroso, String custodia, String custodiaArmada, Integer tipoCustodiaId, String requiereEvidenciaSeguroSocial, Boolean seguro, Boolean servicioCobro, Object servicioAdicional, Object recepcionTicket, Object asignacionManifiesto, Object inicioEscaneoRecepcionProducto, Object finEscaneoRecepcionProducto, Object inicioEntregaProducto, Object finEntregaProducto, Object usuario, Object cliente, Object destinatarios, Object tipoVehiculo, Object ruta, Object tipoCustodia, Integer destinatariosClienteId, Integer cantidad, Integer sumaAsegurada, Object seguroId, Integer id, Object trail, String fechaCreacion, boolean eliminado, boolean esNuevo) {
        super();
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
        this.empaque = empaque;
        this.rutaId = rutaId;
        this.tipoVehiculoId = tipoVehiculoId;
        this.habilidadesVehiculo = habilidadesVehiculo;
        this.documentosVehiculo = documentosVehiculo;
        this.habilidadesOperador = habilidadesOperador;
        this.documentosOperador = documentosOperador;
        this.habilidadesAuxiliar = habilidadesAuxiliar;
        this.documentosAuxiliar = documentosAuxiliar;
        this.evidenciaSalida = evidenciaSalida;
        this.evidenciaLlegada = evidenciaLlegada;
        this.checkList = checkList;
        this.maniobras = maniobras;
        this.peligroso = peligroso;
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
        this.destinatarios = destinatarios;
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

    public Object getEmpaque() {
        return empaque;
    }

    public void setEmpaque(Object empaque) {
        this.empaque = empaque;
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

    public Object getHabilidadesVehiculo() {
        return habilidadesVehiculo;
    }

    public void setHabilidadesVehiculo(Object habilidadesVehiculo) {
        this.habilidadesVehiculo = habilidadesVehiculo;
    }

    public Object getDocumentosVehiculo() {
        return documentosVehiculo;
    }

    public void setDocumentosVehiculo(Object documentosVehiculo) {
        this.documentosVehiculo = documentosVehiculo;
    }

    public Object getHabilidadesOperador() {
        return habilidadesOperador;
    }

    public void setHabilidadesOperador(Object habilidadesOperador) {
        this.habilidadesOperador = habilidadesOperador;
    }

    public Object getDocumentosOperador() {
        return documentosOperador;
    }

    public void setDocumentosOperador(Object documentosOperador) {
        this.documentosOperador = documentosOperador;
    }

    public Object getHabilidadesAuxiliar() {
        return habilidadesAuxiliar;
    }

    public void setHabilidadesAuxiliar(Object habilidadesAuxiliar) {
        this.habilidadesAuxiliar = habilidadesAuxiliar;
    }

    public Object getDocumentosAuxiliar() {
        return documentosAuxiliar;
    }

    public void setDocumentosAuxiliar(Object documentosAuxiliar) {
        this.documentosAuxiliar = documentosAuxiliar;
    }

    public Object getEvidenciaSalida() {
        return evidenciaSalida;
    }

    public void setEvidenciaSalida(Object evidenciaSalida) {
        this.evidenciaSalida = evidenciaSalida;
    }

    public Object getEvidenciaLlegada() {
        return evidenciaLlegada;
    }

    public void setEvidenciaLlegada(Object evidenciaLlegada) {
        this.evidenciaLlegada = evidenciaLlegada;
    }

    public Object getCheckList() {
        return checkList;
    }

    public void setCheckList(Object checkList) {
        this.checkList = checkList;
    }

    public Integer getManiobras() {
        return maniobras;
    }

    public void setManiobras(Integer maniobras) {
        this.maniobras = maniobras;
    }

    public Object getPeligroso() {
        return peligroso;
    }

    public void setPeligroso(Object peligroso) {
        this.peligroso = peligroso;
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

    public Object getCliente() {
        return cliente;
    }

    public void setCliente(Object cliente) {
        this.cliente = cliente;
    }

    public Object getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(Object destinatarios) {
        this.destinatarios = destinatarios;
    }

    public Object getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(Object tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public Object getRuta() {
        return ruta;
    }

    public void setRuta(Object ruta) {
        this.ruta = ruta;
    }

    public Object getTipoCustodia() {
        return tipoCustodia;
    }

    public void setTipoCustodia(Object tipoCustodia) {
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

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public boolean isEsNuevo() {
        return esNuevo;
    }

    public void setEsNuevo(boolean esNuevo) {
        this.esNuevo = esNuevo;
    }
}
