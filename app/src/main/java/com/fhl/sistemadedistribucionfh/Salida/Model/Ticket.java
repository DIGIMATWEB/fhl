package com.fhl.sistemadedistribucionfh.Salida.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Ticket implements Serializable {


    @SerializedName("folio")
    private String folio;
    @SerializedName("numerodeTicket")
    private String numerodeTicket;
    @SerializedName("Sellos")
    private List<Sello> sellos;

    public Ticket(String folio, String numerodeTicket, List<Sello> sellos) {
        super();
        this.folio = folio;
        this.numerodeTicket = numerodeTicket;
        this.sellos = sellos;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getNumerodeTicket() {
        return numerodeTicket;
    }

    public void setNumerodeTicket(String numerodeTicket) {
        this.numerodeTicket = numerodeTicket;
    }

    public List<Sello> getSellos() {
        return sellos;
    }

    public void setSellos(List<Sello> sellos) {
        this.sellos = sellos;
    }
}
