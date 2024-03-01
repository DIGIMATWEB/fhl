package com.fhl.sistemadedistribucionfh.checkList.Questions.model.questions;

import com.google.gson.annotations.SerializedName;

public class responseQuestions {
    @SerializedName("timeMeasure")
    private TimeMeasure timeMeasure;
    @SerializedName("TotalRows")
    private Integer totalRows;
    @SerializedName("Status")
    private Integer status;
    @SerializedName("Message")
    private String message;
    @SerializedName("Data")
    private dataQuestions data;
    @SerializedName("Function")
    private String function;
    @SerializedName("sTiempos")
    private String sTiempos;

    public responseQuestions(TimeMeasure timeMeasure, Integer totalRows, Integer status, String message, dataQuestions data, String function, String sTiempos) {
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

    public dataQuestions getData() {
        return data;
    }

    public void setData(dataQuestions data) {
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
