package com.fhl.sistemadedistribucionfh.Dialogs.habilities.model.vehicle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class responseVehicle {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private DataresponseVehicle data;

    public responseVehicle(Integer status, String message, DataresponseVehicle data) {
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

    public DataresponseVehicle getData() {
        return data;
    }

    public void setData(DataresponseVehicle data) {
        this.data = data;
    }

}
