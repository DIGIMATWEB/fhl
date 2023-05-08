package com.fhl.sistemadedistribucionfh.nmanifestDetail.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseTicketsManifest {

    @SerializedName("resconseCode")
    private Integer resconseCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<dataTicketsManifest> data;

    public responseTicketsManifest(Integer resconseCode, String message, List<dataTicketsManifest> data) {
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

    public List<dataTicketsManifest> getData() {
        return data;
    }

    public void setData(List<dataTicketsManifest> data) {
        this.data = data;
    }

}
