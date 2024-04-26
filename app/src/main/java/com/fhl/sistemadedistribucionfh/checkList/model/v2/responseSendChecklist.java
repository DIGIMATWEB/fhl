package com.fhl.sistemadedistribucionfh.checkList.model.v2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class responseSendChecklist {
    @SerializedName("TotalRows")
    @Expose
    private Integer totalRows;
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Function")
    @Expose
    private String function;

    public responseSendChecklist(Integer totalRows, Integer status, String message, String function) {
        super();
        this.totalRows = totalRows;
        this.status = status;
        this.message = message;
        this.function = function;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
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

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
