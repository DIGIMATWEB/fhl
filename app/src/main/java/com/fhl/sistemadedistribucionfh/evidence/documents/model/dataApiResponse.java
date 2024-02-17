
package com.fhl.sistemadedistribucionfh.evidence.documents.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dataApiResponse {

    @SerializedName("TotalRows")
    @Expose
    private Integer totalRows;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private InnerData data;

    /**
     * No args constructor for use in serialization
     * 
     */
    public dataApiResponse() {
    }

    /**
     * 
     * @param data
     * @param totalRows
     * @param message
     */
    public dataApiResponse(Integer totalRows, String message, InnerData data) {
        super();
        this.totalRows = totalRows;
        this.message = message;
        this.data = data;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public InnerData getData() {
        return data;
    }

    public void setData(InnerData data) {
        this.data = data;
    }

}
