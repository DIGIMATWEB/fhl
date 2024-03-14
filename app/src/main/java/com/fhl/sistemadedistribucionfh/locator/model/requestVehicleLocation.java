package com.fhl.sistemadedistribucionfh.locator.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class requestVehicleLocation {

    @SerializedName("vehicleList")
    private List<Integer> vehicleList;

    public requestVehicleLocation(List<Integer> vehicleList) {
        super();
        this.vehicleList = vehicleList;
    }

    public List<Integer> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Integer> vehicleList) {
        this.vehicleList = vehicleList;
    }
}
