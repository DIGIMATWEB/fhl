package com.fhl.sistemadedistribucionfh.nmanifest.modelV2;

import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.Paquete;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.Remitente;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SendtripPlus {
    @SerializedName("FechaPromesaEntrega")
    @Expose
    private String fechaPromesaEntrega;
    @SerializedName("FechaVentanaInicio")
    @Expose
    private String fechaVentanaInicio;
    @SerializedName("FechaVentanaFin")
    @Expose
    private String fechaVentanaFin;
    @SerializedName("FechaPromesaCarga")
    @Expose
    private String fechaPromesaCarga;
    @SerializedName("TiempoCarga")
    @Expose
    private String tiempoCarga;
    @SerializedName("TipoOrigen")
    @Expose
    private Integer tipoOrigen;
    @SerializedName("Remitente")
    @Expose
    private Remitente remitente;
    @SerializedName("TipoDestino")
    @Expose
    private Integer tipoDestino;
    @SerializedName("Destinatario")
    @Expose
    private Destinatario destinatario;
    @SerializedName("Paquetes")
    @Expose
    private List<Paquete> paquetes;

    public SendtripPlus(String fechaPromesaEntrega, String fechaVentanaInicio, String fechaVentanaFin, String fechaPromesaCarga, String tiempoCarga, Integer tipoOrigen, Remitente remitente, Integer tipoDestino, Destinatario destinatario, List<Paquete> paquetes) {
        super();
        this.fechaPromesaEntrega = fechaPromesaEntrega;
        this.fechaVentanaInicio = fechaVentanaInicio;
        this.fechaVentanaFin = fechaVentanaFin;
        this.fechaPromesaCarga = fechaPromesaCarga;
        this.tiempoCarga = tiempoCarga;
        this.tipoOrigen = tipoOrigen;
        this.remitente = remitente;
        this.tipoDestino = tipoDestino;
        this.destinatario = destinatario;
        this.paquetes = paquetes;
    }

    public String getFechaPromesaEntrega() {
        return fechaPromesaEntrega;
    }

    public void setFechaPromesaEntrega(String fechaPromesaEntrega) {
        this.fechaPromesaEntrega = fechaPromesaEntrega;
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

    public String getFechaPromesaCarga() {
        return fechaPromesaCarga;
    }

    public void setFechaPromesaCarga(String fechaPromesaCarga) {
        this.fechaPromesaCarga = fechaPromesaCarga;
    }

    public String getTiempoCarga() {
        return tiempoCarga;
    }

    public void setTiempoCarga(String tiempoCarga) {
        this.tiempoCarga = tiempoCarga;
    }

    public Integer getTipoOrigen() {
        return tipoOrigen;
    }

    public void setTipoOrigen(Integer tipoOrigen) {
        this.tipoOrigen = tipoOrigen;
    }

    public Remitente getRemitente() {
        return remitente;
    }

    public void setRemitente(Remitente remitente) {
        this.remitente = remitente;
    }

    public Integer getTipoDestino() {
        return tipoDestino;
    }

    public void setTipoDestino(Integer tipoDestino) {
        this.tipoDestino = tipoDestino;
    }

    public Destinatario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Destinatario destinatario) {
        this.destinatario = destinatario;
    }

    public List<Paquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(List<Paquete> paquetes) {
        this.paquetes = paquetes;
    }
}
