
package com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class responseTicketsManifestV2 {

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
    private String message;
    @SerializedName("Data")
    @Expose
    private List<dataTicketsManifestV2> data;
    @SerializedName("Function")
    @Expose
    private String function;
    @SerializedName("Parameters")
    @Expose
    private Object parameters;
    @SerializedName("sTiempos")
    @Expose
    private String sTiempos;

    public responseTicketsManifestV2(TimeMeasure timeMeasure, Integer totalRows, Object pageIndex, Object pageSize, Integer status, String message, List<dataTicketsManifestV2> data, String function, Object parameters, String sTiempos) {
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
