package com.fhl.sistemadedistribucionfh.Dialogs.Reasons.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseReasons {


    @SerializedName("totalRows")
    private Integer totalRows;
    @SerializedName("pageIndex")
    private Integer pageIndex;
    @SerializedName("pageSize")
    private Integer pageSize;
    @SerializedName("status")
    private Integer status;
    @SerializedName("message")
    private Object message;
    @SerializedName("data")
    private List<dataReasons> data;
    @SerializedName("function")
    private String function;
    @SerializedName("parameters")
    private Object parameters;
    @SerializedName("sTiempos")
    private String sTiempos;

    public responseReasons(Integer totalRows, Integer pageIndex, Integer pageSize, Integer status, Object message, List<dataReasons> data, String function, Object parameters, String sTiempos) {
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

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public List<dataReasons> getData() {
        return data;
    }

    public void setData(List<dataReasons> data) {
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
