package com.fhl.sistemadedistribucionfh.nmanifestDetail.model;

import com.google.gson.annotations.SerializedName;

public class dataTicketsManifest {

    @SerializedName("idTicket")
    private String idTicket;
    @SerializedName("folioTicket")
    private String folioTicket;
    @SerializedName("cliente")
    private String cliente;
    @SerializedName("documentacion")
    private String documentacion;
    @SerializedName("contacto")
    private String contacto;
    @SerializedName("adjunto")
    private String adjunto;
    @SerializedName("productos")
    private String productos;
    @SerializedName("checklist")
    private String checklist;
    @SerializedName("cierre")
    private String cierre;
    @SerializedName("estado")
    private String estado;
    @SerializedName("destino")
    private String destino;
    @SerializedName("salida")
    private String salida;
    @SerializedName("regreso")
    private String regreso;
    @SerializedName("georeferencia")
    private String georeferencia;
    @SerializedName("latituds")
    private String latituds;
    @SerializedName("longituds")
    private String longituds;
    @SerializedName("evidence")
    private String evidence;
    @SerializedName("fecha")
    private String fecha;

    public dataTicketsManifest(String idTicket, String folioTicket, String cliente, String documentacion, String contacto, String adjunto, String productos, String checklist, String cierre, String estado, String destino, String salida, String regreso, String georeferencia, String latituds, String longituds, String evidence, String fecha) {
        super();
        this.idTicket = idTicket;
        this.folioTicket = folioTicket;
        this.cliente = cliente;
        this.documentacion = documentacion;
        this.contacto = contacto;
        this.adjunto = adjunto;
        this.productos = productos;
        this.checklist = checklist;
        this.cierre = cierre;
        this.estado = estado;
        this.destino = destino;
        this.salida = salida;
        this.regreso = regreso;
        this.georeferencia = georeferencia;
        this.latituds = latituds;
        this.longituds = longituds;
        this.evidence = evidence;
        this.fecha = fecha;
    }

    public String getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(String idTicket) {
        this.idTicket = idTicket;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDocumentacion() {
        return documentacion;
    }

    public void setDocumentacion(String documentacion) {
        this.documentacion = documentacion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(String adjunto) {
        this.adjunto = adjunto;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public String getChecklist() {
        return checklist;
    }

    public void setChecklist(String checklist) {
        this.checklist = checklist;
    }

    public String getCierre() {
        return cierre;
    }

    public String getFolioTicket() {
        return folioTicket;
    }

    public void setFolioTicket(String folioTicket) {
        this.folioTicket = folioTicket;
    }
    public void setCierre(String cierre) {
        this.cierre = cierre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public String getRegreso() {
        return regreso;
    }

    public void setRegreso(String regreso) {
        this.regreso = regreso;
    }

    public String getGeoreferencia() {
        return georeferencia;
    }

    public void setGeoreferencia(String georeferencia) {
        this.georeferencia = georeferencia;
    }

    public String getLatituds() {
        return latituds;
    }

    public void setLatituds(String latituds) {
        this.latituds = latituds;
    }

    public String getLongituds() {
        return longituds;
    }

    public void setLongituds(String longituds) {
        this.longituds = longituds;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
