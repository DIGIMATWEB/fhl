package com.fhl.sistemadedistribucionfh.resguardo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseResguardo {
    @SerializedName("resconseCode")
    private Integer resconseCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<dataResguardo> data;

    public responseResguardo(Integer resconseCode, String message, List<dataResguardo> data) {
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

    public List<dataResguardo> getData() {
        return data;
    }

    public void setData(List<dataResguardo> data) {
        this.data = data;
    }
}
