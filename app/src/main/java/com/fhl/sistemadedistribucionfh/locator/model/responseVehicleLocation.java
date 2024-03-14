package com.fhl.sistemadedistribucionfh.locator.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseVehicleLocation {
    @SerializedName("totalRows")
    private Object totalRows;
    @SerializedName("pageIndex")
    private Object pageIndex;
    @SerializedName("pageSize")
    private Object pageSize;
    @SerializedName("status")
    private Integer status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<dataVehicleLocation> data;

    public responseVehicleLocation(Object totalRows, Object pageIndex, Object pageSize, Integer status, String message, List<dataVehicleLocation> data) {
        super();
        this.totalRows = totalRows;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Object getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Object totalRows) {
        this.totalRows = totalRows;
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

    public List<dataVehicleLocation> getData() {
        return data;
    }

    public void setData(List<dataVehicleLocation> data) {
        this.data = data;
    }
}
