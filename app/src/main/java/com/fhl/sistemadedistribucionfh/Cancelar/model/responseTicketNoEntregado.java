package com.fhl.sistemadedistribucionfh.Cancelar.model;

import com.google.gson.annotations.SerializedName;

public class responseTicketNoEntregado {
    @SerializedName("Status")
    private Integer status;
    @SerializedName("Message")
    private String message;
    @SerializedName("Data")
    private dataTicketNoEntregado data;
    public responseTicketNoEntregado(Integer status, String message, dataTicketNoEntregado data) {
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

    public dataTicketNoEntregado getData() {
        return data;
    }

    public void setData(dataTicketNoEntregado data) {
        this.data = data;
    }
}
