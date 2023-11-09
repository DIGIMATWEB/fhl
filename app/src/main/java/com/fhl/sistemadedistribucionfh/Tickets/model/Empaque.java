
package com.fhl.sistemadedistribucionfh.Tickets.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Empaque {

    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("items")
    @Expose
    private List<Item> items;
    @SerializedName("destinatarios")
    @Expose
    private List<Destinatario> destinatarios;
    @SerializedName("remitente")
    @Expose
    private List<Remitente> remitente;

    public Empaque(String nombre, List<Item> items, List<Destinatario> destinatarios, List<Remitente> remitente) {
        super();
        this.nombre = nombre;
        this.items = items;
        this.destinatarios = destinatarios;
        this.remitente = remitente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Destinatario> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(List<Destinatario> destinatarios) {
        this.destinatarios = destinatarios;
    }

    public List<Remitente> getRemitente() {
        return remitente;
    }

    public void setRemitente(List<Remitente> remitente) {
        this.remitente = remitente;
    }

}
