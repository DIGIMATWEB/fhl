package com.fhl.sistemadedistribucionfh.Salida.Model.test;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseSalida {
    @SerializedName("Message")
    private String messge;
    @SerializedName("Status")
    private Integer code;
    @SerializedName("qr")
    private String qr;
    @SerializedName("envio")
    private String envio;
    @SerializedName("Tickets")
    private List<Ticket> tickets;

    public responseSalida(String messge, Integer code, String qr, String envio, List<Ticket> tickets) {
        super();
        this.messge = messge;
        this.code = code;
        this.qr = qr;
        this.envio = envio;
        this.tickets = tickets;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public String getEnvio() {
        return envio;
    }

    public void setEnvio(String envio) {
        this.envio = envio;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
