
package com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


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
    @SerializedName("CantidadPaquetes")
    @Expose
    private Integer cantidadPaquetes;
    @SerializedName("Paquetes")
    @Expose
    private List<Paquete> paquetes;

    public SendtripPlus(String fechaPromesaEntrega, String fechaVentanaInicio, String fechaVentanaFin, String fechaPromesaCarga, String tiempoCarga, Integer tipoOrigen, Remitente remitente, Integer tipoDestino, Destinatario destinatario, Integer cantidadPaquetes, List<Paquete> paquetes) {
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
        this.cantidadPaquetes = cantidadPaquetes;
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

    public Integer getCantidadPaquetes() {
        return cantidadPaquetes;
    }

    public void setCantidadPaquetes(Integer cantidadPaquetes) {
        this.cantidadPaquetes = cantidadPaquetes;
    }

    public List<Paquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(List<Paquete> paquetes) {
        this.paquetes = paquetes;
    }

}
