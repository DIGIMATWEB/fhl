package com.fhl.sistemadedistribucionfh.evidence.model.changeStatusmanifestticket;

import com.google.gson.annotations.SerializedName;

public class EstatusDespacho {
    @SerializedName("folioDespacho")
    private String folioDespacho;
    @SerializedName("estatusId")
    private String estatusId;
    @SerializedName("estatus")
    private String estatus;

    public EstatusDespacho(String folioDespacho, String estatusId, String estatus) {
        super();
        this.folioDespacho = folioDespacho;
        this.estatusId = estatusId;
        this.estatus = estatus;
    }

    public String getFolioDespacho() {
        return folioDespacho;
    }

    public void setFolioDespacho(String folioDespacho) {
        this.folioDespacho = folioDespacho;
    }

    public String getEstatusId() {
        return estatusId;
    }

    public void setEstatusId(String estatusId) {
        this.estatusId = estatusId;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}
