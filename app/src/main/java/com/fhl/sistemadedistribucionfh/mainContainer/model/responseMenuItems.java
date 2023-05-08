package com.fhl.sistemadedistribucionfh.mainContainer.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseMenuItems {

    @SerializedName("resconseCode")
    private Integer resconseCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<dataMenuItems> data;

    public responseMenuItems(Integer resconseCode, String message, List<dataMenuItems> data) {
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

    public List<dataMenuItems> getData() {
        return data;
    }

    public void setData(List<dataMenuItems> data) {
        this.data = data;
    }
}
