package com.fhl.sistemadedistribucionfh.evidence.model.changeStatusmanifestticket;

import com.google.gson.annotations.SerializedName;

public class dataStatusManifestTicket {
    @SerializedName("estatusDespacho")
    private EstatusDespacho estatusDespacho;

    public dataStatusManifestTicket(EstatusDespacho estatusDespacho) {
        super();
        this.estatusDespacho = estatusDespacho;
    }

    public EstatusDespacho getEstatusDespacho() {
        return estatusDespacho;
    }

    public void setEstatusDespacho(EstatusDespacho estatusDespacho) {
        this.estatusDespacho = estatusDespacho;
    }

}
