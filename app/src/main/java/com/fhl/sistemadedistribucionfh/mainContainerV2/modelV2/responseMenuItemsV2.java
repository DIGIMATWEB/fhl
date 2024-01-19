package com.fhl.sistemadedistribucionfh.mainContainerV2.modelV2;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseMenuItemsV2 {

    @SerializedName("totalRows")
    private Integer totalRows;
    @SerializedName("pageIndex")
    private Integer pageIndex;
    @SerializedName("pageSize")
    private Integer pageSize;
    @SerializedName("status")
    private Integer status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    //private List<dataMenuItemsV2> data;
    private String data;
    @SerializedName("function")
    private String function;
    @SerializedName("parameters")
    private Object parameters;
    @SerializedName("sTiempos")
    private String sTiempos;

    public responseMenuItemsV2(Integer totalRows, Integer pageIndex, Integer pageSize, Integer status, String message, String data, String function, Object parameters, String sTiempos) {
        super();
        this.totalRows = totalRows;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.status = status;
        this.message = message;
        this.data = data;
        this.function = function;
        this.parameters = parameters;
        this.sTiempos = sTiempos;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public Object getParameters() {
        return parameters;
    }

    public void setParameters(Object parameters) {
        this.parameters = parameters;
    }

    public String getsTiempos() {
        return sTiempos;
    }

    public void setsTiempos(String sTiempos) {
        this.sTiempos = sTiempos;
    }
}
