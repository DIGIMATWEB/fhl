
package com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TicketsDetailSentriplus {

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
    private List<dataTicketsDetailsendtrip> data;
    @SerializedName("Function")
    @Expose
    private String function;
    @SerializedName("sTiempos")
    @Expose
    private String sTiempos;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TicketsDetailSentriplus() {
    }

    /**
     * 
     * @param timeMeasure
     * @param data
     * @param sTiempos
     * @param function
     * @param totalRows
     * @param message
     * @param status
     */
    public TicketsDetailSentriplus(TimeMeasure timeMeasure, Integer totalRows, Integer status, String message, List<dataTicketsDetailsendtrip> data, String function, String sTiempos) {
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

    public List<dataTicketsDetailsendtrip> getData() {
        return data;
    }

    public void setData(List<dataTicketsDetailsendtrip> data) {
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
