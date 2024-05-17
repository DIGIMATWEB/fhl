package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.model;

import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.SendtripPlus;

public class ticketsScanned {
    private String folio;
    private Boolean flag;
    public ticketsScanned(String folio, Boolean flag, SendtripPlus sendtripPlus){
        this.folio=folio;
        this.flag=flag;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }
}
