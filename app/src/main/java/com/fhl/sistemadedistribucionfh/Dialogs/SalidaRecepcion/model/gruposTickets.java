package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model;

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.model.ticketsScanned;

import java.util.List;

public class gruposTickets {
    private List<ticketsScanned> tickets;

    public gruposTickets(List<ticketsScanned> tickets){
        this.tickets=tickets;
    }

    public List<ticketsScanned> getTickets() {
        return tickets;
    }

    public void setTickets(List<ticketsScanned> tickets) {
        this.tickets = tickets;
    }
}
