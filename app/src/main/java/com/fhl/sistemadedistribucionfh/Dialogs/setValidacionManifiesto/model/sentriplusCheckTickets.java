package com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model;

public class sentriplusCheckTickets {
    private String Folio;
    private Boolean isSended=false;
    public sentriplusCheckTickets(String Folio,Boolean isSended){
        this.Folio=Folio;
        this.isSended=isSended;
    }

    public String getFolio() {
        return Folio;
    }

    public void setFolio(String folio) {
        Folio = folio;
    }

    public Boolean getSended() {
        return isSended;
    }

    public void setSended(Boolean sended) {
        isSended = sended;
    }
}
