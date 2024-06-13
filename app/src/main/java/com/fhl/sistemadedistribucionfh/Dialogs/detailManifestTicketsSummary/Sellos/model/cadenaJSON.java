package com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.model;

import com.google.gson.annotations.SerializedName;

public class cadenaJSON {

    @SerializedName("Id")
    private Integer id;
    @SerializedName("CurrentData")
    private CurrentData currentData;

    public cadenaJSON(Integer id, CurrentData currentData) {
        super();
        this.id = id;
        this.currentData = currentData;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CurrentData getCurrentData() {
        return currentData;
    }

    public void setCurrentData(CurrentData currentData) {
        this.currentData = currentData;
    }

}
