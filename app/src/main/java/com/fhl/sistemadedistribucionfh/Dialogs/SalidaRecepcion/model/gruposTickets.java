package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model;

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.model.ticketsScanned;

import java.util.List;

public class gruposTickets {
    private List<ticketsScanned> tickets;
    private Boolean checkEvidence =false;

    public gruposTickets(List<ticketsScanned> tickets,Boolean checkEvidence){
        this.tickets=tickets;
        this.checkEvidence=checkEvidence;
    }

    public List<ticketsScanned> getTickets() {
        return tickets;
    }

    public void setTickets(List<ticketsScanned> tickets) {
        this.tickets = tickets;
    }

    public Boolean getCheckEvidence() {
        return checkEvidence;
    }

    public void setCheckEvidence(Boolean checkEvidence) {
        this.checkEvidence = checkEvidence;
    }
}
