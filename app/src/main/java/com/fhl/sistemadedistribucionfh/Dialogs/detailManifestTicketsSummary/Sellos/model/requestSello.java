package com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.model;

import com.google.gson.annotations.SerializedName;

public class requestSello {


    @SerializedName("cadenaJSON")
    private String json;
    public requestSello(String json){
        this.json=json;
    }
    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
