package com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV1.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseValidador {

    @SerializedName("resconseCode")
    private Integer resconseCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<dataValidador> data;

    public responseValidador(Integer resconseCode, String message, List<dataValidador> data) {
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

    public List<dataValidador> getData() {
        return data;
    }

    public void setData(List<dataValidador> data) {
        this.data = data;
    }
}
