
package com.fhl.sistemadedistribucionfh.Tickets.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

//public class Destinatarios implements Serializable {
//
//    @SerializedName("contactoId")
//    @Expose
//    private Integer contactoId;
//    @SerializedName("contacto")
//    @Expose
//    private Contacto contacto;
//    @SerializedName("clienteId")
//    @Expose
//    private Integer clienteId;
//    @SerializedName("razonSocial")
//    @Expose
//    private String razonSocial;
//    @SerializedName("rfc")
//    @Expose
//    private String rfc;
//    @SerializedName("axaptaId")
//    @Expose
//    private Object axaptaId;
//    @SerializedName("referencia")
//    @Expose
//    private Object referencia;
//    @SerializedName("calle")
//    @Expose
//    private Object calle;
//    @SerializedName("numeroExterior")
//    @Expose
//    private Object numeroExterior;
//    @SerializedName("numeroInterior")
//    @Expose
//    private Object numeroInterior;
//    @SerializedName("colonia")
//    @Expose
//    private Object colonia;
//    @SerializedName("localidad")
//    @Expose
//    private Object localidad;
//    @SerializedName("municipio")
//    @Expose
//    private Object municipio;
//    @SerializedName("estado")
//    @Expose
//    private Object estado;
//    @SerializedName("pais")
//    @Expose
//    private Object pais;
//    @SerializedName("codigoPostal")
//    @Expose
//    private Integer codigoPostal;
//    @SerializedName("coordenadas")
//    @Expose
//    private Object coordenadas;
//    @SerializedName("recepcionCita")
//    @Expose
//    private Object recepcionCita;
//    @SerializedName("ventanaAtencion")
//    @Expose
//    private Object ventanaAtencion;
//    @SerializedName("restriccionCirculacion")
//    @Expose
//    private Object restriccionCirculacion;
//    @SerializedName("habilidadVehiculo")
//    @Expose
//    private Object habilidadVehiculo;
//    @SerializedName("documentoVehiculo")
//    @Expose
//    private Object documentoVehiculo;
//    @SerializedName("habilidadOperador")
//    @Expose
//    private Object habilidadOperador;
//    @SerializedName("documentoOperador")
//    @Expose
//    private Object documentoOperador;
//    @SerializedName("habilidadAuxiliar")
//    @Expose
//    private Object habilidadAuxiliar;
//    @SerializedName("documentoAuxiliar")
//    @Expose
//    private Object documentoAuxiliar;
//    @SerializedName("evidenciaSalida")
//    @Expose
//    private Object evidenciaSalida;
//    @SerializedName("evidenciaLlegada")
//    @Expose
//    private Object evidenciaLlegada;
//    @SerializedName("sellos")
//    @Expose
//    private Object sellos;
//    @SerializedName("checklist")
//    @Expose
//    private Object checklist;
//    @SerializedName("geolocalizacion")
//    @Expose
//    private Object geolocalizacion;
//    @SerializedName("tiempoParado")
//    @Expose
//    private Object tiempoParado;
//    @SerializedName("usuario")
//    @Expose
//    private Object usuario;
//    @SerializedName("id")
//    @Expose
//    private Integer id;
//    @SerializedName("trail")
//    @Expose
//    private Object trail;
//    @SerializedName("fechaCreacion")
//    @Expose
//    private String fechaCreacion;
//    @SerializedName("eliminado")
//    @Expose
//    private Boolean eliminado;
//    @SerializedName("esNuevo")
//    @Expose
//    private Boolean esNuevo;
//
//    public Destinatarios(Integer contactoId, Contacto contacto, Integer clienteId, String razonSocial, String rfc, Object axaptaId, Object referencia, Object calle, Object numeroExterior, Object numeroInterior, Object colonia, Object localidad, Object municipio, Object estado, Object pais, Integer codigoPostal, Object coordenadas, Object recepcionCita, Object ventanaAtencion, Object restriccionCirculacion, Object habilidadVehiculo, Object documentoVehiculo, Object habilidadOperador, Object documentoOperador, Object habilidadAuxiliar, Object documentoAuxiliar, Object evidenciaSalida, Object evidenciaLlegada, Object sellos, Object checklist, Object geolocalizacion, Object tiempoParado, Object usuario, Integer id, Object trail, String fechaCreacion, Boolean eliminado, Boolean esNuevo) {
//        super();
//        this.contactoId = contactoId;
//        this.contacto = contacto;
//        this.clienteId = clienteId;
//        this.razonSocial = razonSocial;
//        this.rfc = rfc;
//        this.axaptaId = axaptaId;
//        this.referencia = referencia;
//        this.calle = calle;
//        this.numeroExterior = numeroExterior;
//        this.numeroInterior = numeroInterior;
//        this.colonia = colonia;
//        this.localidad = localidad;
//        this.municipio = municipio;
//        this.estado = estado;
//        this.pais = pais;
//        this.codigoPostal = codigoPostal;
//        this.coordenadas = coordenadas;
//        this.recepcionCita = recepcionCita;
//        this.ventanaAtencion = ventanaAtencion;
//        this.restriccionCirculacion = restriccionCirculacion;
//        this.habilidadVehiculo = habilidadVehiculo;
//        this.documentoVehiculo = documentoVehiculo;
//        this.habilidadOperador = habilidadOperador;
//        this.documentoOperador = documentoOperador;
//        this.habilidadAuxiliar = habilidadAuxiliar;
//        this.documentoAuxiliar = documentoAuxiliar;
//        this.evidenciaSalida = evidenciaSalida;
//        this.evidenciaLlegada = evidenciaLlegada;
//        this.sellos = sellos;
//        this.checklist = checklist;
//        this.geolocalizacion = geolocalizacion;
//        this.tiempoParado = tiempoParado;
//        this.usuario = usuario;
//        this.id = id;
//        this.trail = trail;
//        this.fechaCreacion = fechaCreacion;
//        this.eliminado = eliminado;
//        this.esNuevo = esNuevo;
//    }
//
//    public Integer getContactoId() {
//        return contactoId;
//    }
//
//    public void setContactoId(Integer contactoId) {
//        this.contactoId = contactoId;
//    }
//
//    public Contacto getContacto() {
//        return contacto;
//    }
//
//    public void setContacto(Contacto contacto) {
//        this.contacto = contacto;
//    }
//
//    public Integer getClienteId() {
//        return clienteId;
//    }
//
//    public void setClienteId(Integer clienteId) {
//        this.clienteId = clienteId;
//    }
//
//    public String getRazonSocial() {
//        return razonSocial;
//    }
//
//    public void setRazonSocial(String razonSocial) {
//        this.razonSocial = razonSocial;
//    }
//
//    public String getRfc() {
//        return rfc;
//    }
//
//    public void setRfc(String rfc) {
//        this.rfc = rfc;
//    }
//
//    public Object getAxaptaId() {
//        return axaptaId;
//    }
//
//    public void setAxaptaId(Object axaptaId) {
//        this.axaptaId = axaptaId;
//    }
//
//    public Object getReferencia() {
//        return referencia;
//    }
//
//    public void setReferencia(Object referencia) {
//        this.referencia = referencia;
//    }
//
//    public Object getCalle() {
//        return calle;
//    }
//
//    public void setCalle(Object calle) {
//        this.calle = calle;
//    }
//
//    public Object getNumeroExterior() {
//        return numeroExterior;
//    }
//
//    public void setNumeroExterior(Object numeroExterior) {
//        this.numeroExterior = numeroExterior;
//    }
//
//    public Object getNumeroInterior() {
//        return numeroInterior;
//    }
//
//    public void setNumeroInterior(Object numeroInterior) {
//        this.numeroInterior = numeroInterior;
//    }
//
//    public Object getColonia() {
//        return colonia;
//    }
//
//    public void setColonia(Object colonia) {
//        this.colonia = colonia;
//    }
//
//    public Object getLocalidad() {
//        return localidad;
//    }
//
//    public void setLocalidad(Object localidad) {
//        this.localidad = localidad;
//    }
//
//    public Object getMunicipio() {
//        return municipio;
//    }
//
//    public void setMunicipio(Object municipio) {
//        this.municipio = municipio;
//    }
//
//    public Object getEstado() {
//        return estado;
//    }
//
//    public void setEstado(Object estado) {
//        this.estado = estado;
//    }
//
//    public Object getPais() {
//        return pais;
//    }
//
//    public void setPais(Object pais) {
//        this.pais = pais;
//    }
//
//    public Integer getCodigoPostal() {
//        return codigoPostal;
//    }
//
//    public void setCodigoPostal(Integer codigoPostal) {
//        this.codigoPostal = codigoPostal;
//    }
//
//    public Object getCoordenadas() {
//        return coordenadas;
//    }
//
//    public void setCoordenadas(Object coordenadas) {
//        this.coordenadas = coordenadas;
//    }
//
//    public Object getRecepcionCita() {
//        return recepcionCita;
//    }
//
//    public void setRecepcionCita(Object recepcionCita) {
//        this.recepcionCita = recepcionCita;
//    }
//
//    public Object getVentanaAtencion() {
//        return ventanaAtencion;
//    }
//
//    public void setVentanaAtencion(Object ventanaAtencion) {
//        this.ventanaAtencion = ventanaAtencion;
//    }
//
//    public Object getRestriccionCirculacion() {
//        return restriccionCirculacion;
//    }
//
//    public void setRestriccionCirculacion(Object restriccionCirculacion) {
//        this.restriccionCirculacion = restriccionCirculacion;
//    }
//
//    public Object getHabilidadVehiculo() {
//        return habilidadVehiculo;
//    }
//
//    public void setHabilidadVehiculo(Object habilidadVehiculo) {
//        this.habilidadVehiculo = habilidadVehiculo;
//    }
//
//    public Object getDocumentoVehiculo() {
//        return documentoVehiculo;
//    }
//
//    public void setDocumentoVehiculo(Object documentoVehiculo) {
//        this.documentoVehiculo = documentoVehiculo;
//    }
//
//    public Object getHabilidadOperador() {
//        return habilidadOperador;
//    }
//
//    public void setHabilidadOperador(Object habilidadOperador) {
//        this.habilidadOperador = habilidadOperador;
//    }
//
//    public Object getDocumentoOperador() {
//        return documentoOperador;
//    }
//
//    public void setDocumentoOperador(Object documentoOperador) {
//        this.documentoOperador = documentoOperador;
//    }
//
//    public Object getHabilidadAuxiliar() {
//        return habilidadAuxiliar;
//    }
//
//    public void setHabilidadAuxiliar(Object habilidadAuxiliar) {
//        this.habilidadAuxiliar = habilidadAuxiliar;
//    }
//
//    public Object getDocumentoAuxiliar() {
//        return documentoAuxiliar;
//    }
//
//    public void setDocumentoAuxiliar(Object documentoAuxiliar) {
//        this.documentoAuxiliar = documentoAuxiliar;
//    }
//
//    public Object getEvidenciaSalida() {
//        return evidenciaSalida;
//    }
//
//    public void setEvidenciaSalida(Object evidenciaSalida) {
//        this.evidenciaSalida = evidenciaSalida;
//    }
//
//    public Object getEvidenciaLlegada() {
//        return evidenciaLlegada;
//    }
//
//    public void setEvidenciaLlegada(Object evidenciaLlegada) {
//        this.evidenciaLlegada = evidenciaLlegada;
//    }
//
//    public Object getSellos() {
//        return sellos;
//    }
//
//    public void setSellos(Object sellos) {
//        this.sellos = sellos;
//    }
//
//    public Object getChecklist() {
//        return checklist;
//    }
//
//    public void setChecklist(Object checklist) {
//        this.checklist = checklist;
//    }
//
//    public Object getGeolocalizacion() {
//        return geolocalizacion;
//    }
//
//    public void setGeolocalizacion(Object geolocalizacion) {
//        this.geolocalizacion = geolocalizacion;
//    }
//
//    public Object getTiempoParado() {
//        return tiempoParado;
//    }
//
//    public void setTiempoParado(Object tiempoParado) {
//        this.tiempoParado = tiempoParado;
//    }
//
//    public Object getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(Object usuario) {
//        this.usuario = usuario;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Object getTrail() {
//        return trail;
//    }
//
//    public void setTrail(Object trail) {
//        this.trail = trail;
//    }
//
//    public String getFechaCreacion() {
//        return fechaCreacion;
//    }
//
//    public void setFechaCreacion(String fechaCreacion) {
//        this.fechaCreacion = fechaCreacion;
//    }
//
//    public Boolean getEliminado() {
//        return eliminado;
//    }
//
//    public void setEliminado(Boolean eliminado) {
//        this.eliminado = eliminado;
//    }
//
//    public Boolean getEsNuevo() {
//        return esNuevo;
//    }
//
//    public void setEsNuevo(Boolean esNuevo) {
//        this.esNuevo = esNuevo;
//    }
//
//}
