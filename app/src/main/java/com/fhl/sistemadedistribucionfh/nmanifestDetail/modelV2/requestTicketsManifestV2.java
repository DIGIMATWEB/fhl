package com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2;

import com.google.gson.annotations.SerializedName;

public class requestTicketsManifestV2 {
    @SerializedName("folioDespacho")
    private String folioDespacho;

    public requestTicketsManifestV2(String folioDespacho) {
        super();
        this.folioDespacho = folioDespacho;
    }

    public String getFolioDespacho() {
        return folioDespacho;
    }

    public void setFolioDespacho(String folioDespacho) {
        this.folioDespacho = folioDespacho;
    }
}
