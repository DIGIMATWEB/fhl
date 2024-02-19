package com.fhl.sistemadedistribucionfh.evidence.rateDriver.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class responseRate {

    @SerializedName("PageIndex")
    @Expose
    private Object pageIndex;
    @SerializedName("PageSize")
    @Expose
    private Object pageSize;
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("responseRateData")
    @Expose
    private responseRateData data;

    public responseRate(Object pageIndex, Object pageSize, Integer status, String message, responseRateData data) {
        super();
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Object getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Object pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Object getPageSize() {
        return pageSize;
    }

    public void setPageSize(Object pageSize) {
        this.pageSize = pageSize;
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

    public responseRateData getData() {
        return data;
    }

    public void setData(responseRateData data) {
        this.data = data;
    }
}
