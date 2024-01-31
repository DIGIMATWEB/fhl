
package com.fhl.sistemadedistribucionfh.Sellos.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class responseSellos {

    @SerializedName("timeMeasure")
    @Expose
    private TimeMeasure timeMeasure;
    @SerializedName("TotalRows")
    @Expose
    private Integer totalRows;
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private Data data;
    @SerializedName("Function")
    @Expose
    private String function;
    @SerializedName("sTiempos")
    @Expose
    private String sTiempos;

    public responseSellos(TimeMeasure timeMeasure, Integer totalRows, Integer status, String message, Data data, String function, String sTiempos) {
        super();
        this.timeMeasure = timeMeasure;
        this.totalRows = totalRows;
        this.status = status;
        this.message = message;
        this.data = data;
        this.function = function;
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getsTiempos() {
        return sTiempos;
    }

    public void setsTiempos(String sTiempos) {
        this.sTiempos = sTiempos;
    }

}
