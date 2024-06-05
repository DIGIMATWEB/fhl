package com.fhl.sistemadedistribucionfh.Dialogs.Planeacion.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseGetPlaneacion {
    @SerializedName("Status")
    private Integer status;
    @SerializedName("Message")
    private String message;
    @SerializedName("Data")
    private List<dataGetPlaneacion> data;


    public responseGetPlaneacion(Integer status, String message, List<dataGetPlaneacion> data) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
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

    public List<dataGetPlaneacion> getData() {
        return data;
    }

    public void setData(List<dataGetPlaneacion> data) {
        this.data = data;
    }

}

