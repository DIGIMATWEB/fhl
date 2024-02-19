
package com.fhl.sistemadedistribucionfh.evidence.rateDriver.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class responseRateData {

    @SerializedName("encuestaOperadorPickup")
    @Expose
    private String encuestaOperadorPickup;

    public responseRateData(String encuestaOperadorPickup) {
        super();
        this.encuestaOperadorPickup = encuestaOperadorPickup;
    }

    public String getEncuestaOperadorPickup() {
        return encuestaOperadorPickup;
    }

    public void setEncuestaOperadorPickup(String encuestaOperadorPickup) {
        this.encuestaOperadorPickup = encuestaOperadorPickup;
    }

}
