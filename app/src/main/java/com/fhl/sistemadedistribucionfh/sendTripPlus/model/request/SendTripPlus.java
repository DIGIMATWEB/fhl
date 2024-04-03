
package com.fhl.sistemadedistribucionfh.sendTripPlus.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendTripPlus {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("trip")
    @Expose
    private Trip trip;
    public SendTripPlus(String token, Trip trip) {
        super();
        this.token = token;
        this.trip = trip;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

}
