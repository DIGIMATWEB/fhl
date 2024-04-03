package com.fhl.sistemadedistribucionfh.sendTripPlus.model.avocado;

import com.google.gson.annotations.SerializedName;

public class responseLoginAvocado {
    @SerializedName("responseCode")
    private Integer responseCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private dataAvocado data;


    public responseLoginAvocado(Integer responseCode, String message, dataAvocado data) {
        super();
        this.responseCode = responseCode;
        this.message = message;
        this.data = data;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public dataAvocado getData() {
        return data;
    }

    public void setData(dataAvocado data) {
        this.data = data;
    }
}
