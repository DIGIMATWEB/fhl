package com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2;

import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.dataManifestV2;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseTicketsManifestV2 {
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
    private List<dataTicketsManifestV2> data;
    @SerializedName("function")
    private String function;
    @SerializedName("parameters")
    private String parameters;
    @SerializedName("sTiempos")
    private String sTiempos;

    public responseTicketsManifestV2(Integer totalRows, Integer pageIndex, Integer pageSize, Integer status, String message, List<dataTicketsManifestV2> data, String function, String parameters, String sTiempos) {
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

    public List<dataTicketsManifestV2> getData() {
        return data;
    }

    public void setData(List<dataTicketsManifestV2> data) {
        this.data = data;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getsTiempos() {
        return sTiempos;
    }

    public void setsTiempos(String sTiempos) {
        this.sTiempos = sTiempos;
    }
}