package com.fhl.sistemadedistribucionfh.Dialogs.habilities.model.driver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class responseDriverHabilities {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private DataDriverHailities data;

    public responseDriverHabilities(Integer status, String message, DataDriverHailities data) {
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

    public DataDriverHailities getData() {
        return data;
    }

    public void setData(DataDriverHailities data) {
        this.data = data;
    }
}
