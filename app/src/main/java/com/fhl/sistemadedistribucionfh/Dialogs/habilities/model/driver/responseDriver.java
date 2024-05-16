package com.fhl.sistemadedistribucionfh.Dialogs.habilities.model.driver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class responseDriver {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private DataresponseDriver data;

    public responseDriver(Integer status, String message, DataresponseDriver data) {
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

    public DataresponseDriver getData() {
        return data;
    }

    public void setData(DataresponseDriver data) {
        this.data = data;
    }

}
