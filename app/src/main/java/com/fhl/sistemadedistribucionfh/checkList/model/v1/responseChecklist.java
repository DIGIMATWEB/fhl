package com.fhl.sistemadedistribucionfh.checkList.model.v1;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseChecklist {
    @SerializedName("resconseCode")
    private Integer resconseCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<dataChecklist> data;

    public responseChecklist(Integer resconseCode, String message, List<dataChecklist> data) {
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

    public List<dataChecklist> getData() {
        return data;
    }

    public void setData(List<dataChecklist> data) {
        this.data = data;
    }

}
