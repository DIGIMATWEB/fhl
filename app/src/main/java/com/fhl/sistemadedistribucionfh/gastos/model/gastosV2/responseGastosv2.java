
package com.fhl.sistemadedistribucionfh.gastos.model.gastosV2;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseGastosv2 {

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
    private List<dataGastosOperativos> data;
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
    public ResponseGastosv2() {
    }

    /**
     * 
     * @param data
     * @param sTiempos
     * @param function
     * @param totalRows
     * @param message
     * @param status
     */
    public ResponseGastosv2(Integer totalRows, Integer status, String message, List<dataGastosOperativos> data, String function, String sTiempos) {
        super();
        this.totalRows = totalRows;
        this.status = status;
        this.message = message;
        this.data = data;
        this.function = function;
        this.sTiempos = sTiempos;
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

    public List<dataGastosOperativos> getData() {
        return data;
    }

    public void setData(List<dataGastosOperativos> data) {
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
