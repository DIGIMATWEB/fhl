package com.companyname.mauitest.checkList.model;

import com.google.gson.annotations.SerializedName;

public class dataChecklist {


    @SerializedName("nombreCheckList")
    private String nombreCheckList;
    @SerializedName("vehicleType")
    private String vehicleType;
    @SerializedName("manifestAsignment")
    private String manifestAsignment;
    @SerializedName("status")
    private String status;

    public dataChecklist(String nombreCheckList,String vehicleType, String manifestAsignment, String status) {
        super();
        this.nombreCheckList=nombreCheckList;
        this.vehicleType = vehicleType;
        this.manifestAsignment = manifestAsignment;
        this.status = status;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getManifestAsignment() {
        return manifestAsignment;
    }

    public void setManifestAsignment(String manifestAsignment) {
        this.manifestAsignment = manifestAsignment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getNombreCheckList() {
        return nombreCheckList;
    }

    public void setNombreCheckList(String nombreCheckList) {
        this.nombreCheckList = nombreCheckList;
    }
}
