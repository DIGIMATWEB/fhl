package com.fhl.sistemadedistribucionfh.Dialogs.validador.model;

import com.google.gson.annotations.SerializedName;

public class dataValidador {
    @SerializedName("idDespacho")
    private String idDespacho;
    @SerializedName("driverId")
    private String driverId;
    @SerializedName("manifestId")
    private String manifestId;
    @SerializedName("unitId")
    private String unitId;
    @SerializedName("vStatus")
    private String vStatus;
    @SerializedName("mStatus")
    private String mStatus;
    @SerializedName("dStatus")
    private String dStatus;

    public dataValidador(String idDespacho, String driverId, String manifestId, String unitId, String vStatus, String mStatus, String dStatus) {
        super();
        this.idDespacho = idDespacho;
        this.driverId = driverId;
        this.manifestId = manifestId;
        this.unitId = unitId;
        this.vStatus = vStatus;
        this.mStatus = mStatus;
        this.dStatus = dStatus;
    }

    public String getIdDespacho() {
        return idDespacho;
    }

    public void setIdDespacho(String idDespacho) {
        this.idDespacho = idDespacho;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getManifestId() {
        return manifestId;
    }

    public void setManifestId(String manifestId) {
        this.manifestId = manifestId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getvStatus() {
        return vStatus;
    }

    public void setvStatus(String vStatus) {
        this.vStatus = vStatus;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getdStatus() {
        return dStatus;
    }

    public void setdStatus(String dStatus) {
        this.dStatus = dStatus;
    }
}
