package com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.model;

import com.google.gson.annotations.SerializedName;

public class responseSetSello {

    @SerializedName("status")
    private Integer status;

    @SerializedName("data")
    private dataResponseSetSello data;
    @SerializedName("function")
    private String function;
    @SerializedName("parameters")
    private Object parameters;
    @SerializedName("sTiempos")
    private String sTiempos;
    @SerializedName("message")
    private String message;
    public responseSetSello( Integer status, dataResponseSetSello data, String function, Object parameters, String sTiempos,String message) {
        super();
        this.status = status;
        this.data = data;
        this.function = function;
        this.parameters = parameters;
        this.sTiempos = sTiempos;
        this.message=message;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }



    public dataResponseSetSello getData() {
        return data;
    }

    public void setData(dataResponseSetSello data) {
        this.data = data;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public Object getParameters() {
        return parameters;
    }

    public void setParameters(Object parameters) {
        this.parameters = parameters;
    }

    public String getsTiempos() {
        return sTiempos;
    }

    public void setsTiempos(String sTiempos) {
        this.sTiempos = sTiempos;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
