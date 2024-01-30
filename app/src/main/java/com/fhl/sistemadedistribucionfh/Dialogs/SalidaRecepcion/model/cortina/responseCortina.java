package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.cortina;

import com.google.gson.annotations.SerializedName;

public class responseCortina {
    @SerializedName("timeMeasure")
    private TimeMeasurecortina timeMeasure;
    @SerializedName("TotalRows")
    private Integer totalRows;
    @SerializedName("Status")
    private Integer status;
    @SerializedName("Message")
    private String message;
    @SerializedName("Data")
    private dataCortina data;
    @SerializedName("Function")
    private String function;
    @SerializedName("sTiempos")
    private String sTiempos;


    public responseCortina(TimeMeasurecortina timeMeasure, Integer totalRows, Integer status, String message, dataCortina data, String function, String sTiempos) {
        super();
        this.timeMeasure = timeMeasure;
        this.totalRows = totalRows;
        this.status = status;
        this.message = message;
        this.data = data;
        this.function = function;
        this.sTiempos = sTiempos;
    }

    public TimeMeasurecortina getTimeMeasure() {
        return timeMeasure;
    }

    public void setTimeMeasure(TimeMeasurecortina timeMeasure) {
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

    public dataCortina getData() {
        return data;
    }

    public void setData(dataCortina data) {
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
