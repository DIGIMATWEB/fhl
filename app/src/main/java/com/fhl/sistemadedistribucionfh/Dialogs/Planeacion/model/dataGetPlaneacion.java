package com.fhl.sistemadedistribucionfh.Dialogs.Planeacion.model;

import com.google.gson.annotations.SerializedName;

public class dataGetPlaneacion {
    @SerializedName("FolioTicket")
    private String folioTicket;
    @SerializedName("TipoSolicitudId")
    private Integer tipoSolicitudId;
    @SerializedName("Empaque")
    private String empaque;

    public dataGetPlaneacion(String folioTicket, Integer tipoSolicitudId, String empaque) {
        super();
        this.folioTicket = folioTicket;
        this.tipoSolicitudId = tipoSolicitudId;
        this.empaque = empaque;
    }

    public String getFolioTicket() {
        return folioTicket;
    }

    public void setFolioTicket(String folioTicket) {
        this.folioTicket = folioTicket;
    }

    public Integer getTipoSolicitudId() {
        return tipoSolicitudId;
    }

    public void setTipoSolicitudId(Integer tipoSolicitudId) {
        this.tipoSolicitudId = tipoSolicitudId;
    }

    public String getEmpaque() {
        return empaque;
    }

    public void setEmpaque(String empaque) {
        this.empaque = empaque;
    }

}
