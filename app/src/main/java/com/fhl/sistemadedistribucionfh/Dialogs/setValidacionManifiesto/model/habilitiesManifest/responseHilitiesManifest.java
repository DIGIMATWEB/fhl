package com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.habilitiesManifest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseHilitiesManifest {

    @SerializedName("TotalRows")
  
    private Integer totalRows;
    @SerializedName("Status")
  
    private Integer status;
    @SerializedName("Message")
  
    private String message;
    @SerializedName("Data")
  
    private List<dataHabilitiesManifest> data;

    public responseHilitiesManifest(Integer totalRows, Integer status, String message, List<dataHabilitiesManifest> data) {
        this.totalRows = totalRows;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<dataHabilitiesManifest> getData() {
        return data;
    }

    public void setData(List<dataHabilitiesManifest> data) {
        this.data = data;
    }
}
