package com.companyname.mauitest.nmanifest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseManifest {


    @SerializedName("resconseCode")
    private Integer resconseCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<dataManifest> data;

    public responseManifest(Integer resconseCode, String message, List<dataManifest> data) {
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

    public List<dataManifest> getData() {
        return data;
    }

    public void setData(List<dataManifest> data) {
        this.data = data;
    }
}
