package com.fhl.sistemadedistribucionfh.nmanifestDetail.model;

import com.google.gson.annotations.SerializedName;

public class requestTicketsManifest {

    @SerializedName("idmanifest")
    private String idmanifest;


    public requestTicketsManifest(String idmanifest) {
        super();
        this.idmanifest = idmanifest;
    }

    public String getIdmanifest() {
        return idmanifest;
    }

    public void setIdmanifest(String idmanifest) {
        this.idmanifest = idmanifest;
    }
}
