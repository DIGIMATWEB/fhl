package com.fhl.sistemadedistribucionfh.Dialogs.Reasons.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseReasons {

    @SerializedName("resconseCode")
    private Integer resconseCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<dataReasons> data;

    public responseReasons(Integer resconseCode, String message, List<dataReasons> data) {
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

    public List<dataReasons> getData() {
        return data;
    }

    public void setData(List<dataReasons> data) {
        this.data = data;
    }

}
