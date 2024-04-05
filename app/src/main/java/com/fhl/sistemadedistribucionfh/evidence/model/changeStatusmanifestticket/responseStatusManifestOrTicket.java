package com.fhl.sistemadedistribucionfh.evidence.model.changeStatusmanifestticket;

import com.google.gson.annotations.SerializedName;

public class responseStatusManifestOrTicket {
    @SerializedName("TotalRows")
    private Integer totalRows;
    @SerializedName("Status")
    private Integer status;
    @SerializedName("Message")
    private String message;
    @SerializedName("Data")
    private dataStatusManifestTicket data;
    @SerializedName("Function")
    private String function;
    @SerializedName("sTiempos")
    private String sTiempos;

    public responseStatusManifestOrTicket(Integer totalRows, Integer status, String message, dataStatusManifestTicket data, String function, String sTiempos) {
        super();
        this.totalRows = totalRows;
        this.status = status;
        this.message = message;
        this.data = data;
        this.function = function;
        this.sTiempos = sTiempos;
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

    public dataStatusManifestTicket getData() {
        return data;
    }

    public void setData(dataStatusManifestTicket data) {
        this.data = data;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getsTiempos() {
        return sTiempos;
    }

    public void setsTiempos(String sTiempos) {
        this.sTiempos = sTiempos;
    }

}
