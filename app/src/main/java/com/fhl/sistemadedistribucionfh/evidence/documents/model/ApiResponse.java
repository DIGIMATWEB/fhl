
package com.fhl.sistemadedistribucionfh.evidence.documents.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    @SerializedName("timeMeasure")
    @Expose
    private TimeMeasure timeMeasure;
    @SerializedName("TotalRows")
    @Expose
    private Integer totalRows;
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
    private Object message;
    @SerializedName("Data")
    @Expose
    private List<dataApiResponse> data;
    @SerializedName("Function")
    @Expose
    private String function;
    @SerializedName("Parameters")
    @Expose
    private Object parameters;
    @SerializedName("sTiempos")
    @Expose
    private String sTiempos;

    public ApiResponse(TimeMeasure timeMeasure, Integer totalRows, Object pageIndex, Object pageSize, Integer status, Object message, List<dataApiResponse> data, String function, Object parameters, String sTiempos) {
        super();
        this.timeMeasure = timeMeasure;
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

    public TimeMeasure getTimeMeasure() {
        return timeMeasure;
    }

    public void setTimeMeasure(TimeMeasure timeMeasure) {
        this.timeMeasure = timeMeasure;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
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

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public List<dataApiResponse> getData() {
        return data;
    }

    public void setData(List<dataApiResponse> data) {
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
