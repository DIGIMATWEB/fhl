package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.model;

import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.SendtripPlus;

import java.io.Serializable;

public class ticketsScanned implements Serializable {
    private String folio;
    private Boolean flag;
    private Boolean hasTekenevidence=false;
    private SendtripPlus sendtripPlus;
    public ticketsScanned(String folio, Boolean flag, SendtripPlus sendtripPlus,Boolean hasTekenevidence){
        this.folio=folio;
        this.flag=flag;
        this.sendtripPlus=sendtripPlus;
        this.hasTekenevidence=hasTekenevidence;
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

    public SendtripPlus getSendtripPlus() {
        return sendtripPlus;
    }

    public void setSendtripPlus(SendtripPlus sendtripPlus) {
        this.sendtripPlus = sendtripPlus;
    }

    public Boolean getHasTekenevidence() {
        return hasTekenevidence;
    }

    public void setHasTekenevidence(Boolean hasTekenevidence) {
        this.hasTekenevidence = hasTekenevidence;
    }
}
