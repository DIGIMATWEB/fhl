
package com.fhl.sistemadedistribucionfh.Tickets.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class ResoponseTicketsDetail {

    @SerializedName("totalRows")
    @Expose
    private Integer totalRows;
    @SerializedName("pageIndex")
    @Expose
    private Object pageIndex;
    @SerializedName("pageSize")
    @Expose
    private Object pageSize;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<dataDetailTickets> data;
    @SerializedName("function")
    @Expose
    private String function;
    @SerializedName("parameters")
    @Expose
    private Object parameters;
    @SerializedName("sTiempos")
    @Expose
    private String sTiempos;

    public ResoponseTicketsDetail(Integer totalRows, Object pageIndex, Object pageSize, Integer status, String message, List<dataDetailTickets> data, String function, Object parameters, String sTiempos) {
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

    public List<dataDetailTickets> getData() {
        return data;
    }

    public void setData(List<dataDetailTickets> data) {
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
