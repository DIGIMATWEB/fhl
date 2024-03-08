package com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model;

import com.google.gson.annotations.SerializedName;

public class responseSetValidacion {
    @SerializedName("TotalRows")
    private Integer totalRows;
    @SerializedName("Status")
    private Integer status;
    @SerializedName("Message")
    private String message;
    @SerializedName("Function")
    private String function;
    @SerializedName("sTiempos")
    private String sTiempos;

    public responseSetValidacion(Integer totalRows, Integer status, String message, String function, String sTiempos) {
        super();
        this.totalRows = totalRows;
        this.status = status;
        this.message = message;
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
