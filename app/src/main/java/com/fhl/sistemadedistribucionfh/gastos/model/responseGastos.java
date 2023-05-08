package com.fhl.sistemadedistribucionfh.gastos.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseGastos {
    @SerializedName("resconseCode")
    private Integer resconseCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<dataGastos> data;

    public responseGastos(Integer resconseCode, String message, List<dataGastos> data) {
        super();
        this.resconseCode = resconseCode;
        this.message = message;
        this.data = data;
    }

    public Integer getResconseCode() {
        return resconseCode;
    }

    public void setResconseCode(Integer resconseCode) {
        this.resconseCode = resconseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<dataGastos> getData() {
        return data;
    }

    public void setData(List<dataGastos> data) {
        this.data = data;
    }
}
