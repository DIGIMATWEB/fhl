package com.fhl.sistemadedistribucionfh.evidence.rateDriver.model;

import com.google.gson.annotations.SerializedName;

public class requestRate {

    @SerializedName("folioDespacho")
    private String folioDespacho;
    @SerializedName("operadorId")
    private Integer operadorId;
    @SerializedName("encuestaOperadorPickUp")
    private Integer encuestaOperadorPickUp;
    public requestRate(String folioDespacho, Integer operadorId, Integer encuestaOperadorPickUp) {
        super();
        this.folioDespacho = folioDespacho;
        this.operadorId = operadorId;
        this.encuestaOperadorPickUp = encuestaOperadorPickUp;
    }

    public String getFolioDespacho() {
        return folioDespacho;
    }

    public void setFolioDespacho(String folioDespacho) {
        this.folioDespacho = folioDespacho;
    }

    public Integer getOperadorId() {
        return operadorId;
    }

    public void setOperadorId(Integer operadorId) {
        this.operadorId = operadorId;
    }

    public Integer getEncuestaOperadorPickUp() {
        return encuestaOperadorPickUp;
    }

    public void setEncuestaOperadorPickUp(Integer encuestaOperadorPickUp) {
        this.encuestaOperadorPickUp = encuestaOperadorPickUp;
    }
}
