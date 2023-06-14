package com.fhl.sistemadedistribucionfh.Dialogs.Reasons.model;

import com.google.gson.annotations.SerializedName;

public class dataReasons {
    @SerializedName("idReason")
    private String idReason;
    @SerializedName("reason")
    private String reason;

    public dataReasons(String idReason, String reason) {
        super();
        this.idReason = idReason;
        this.reason = reason;
    }

    public String getIdReason() {
        return idReason;
    }

    public void setIdReason(String idReason) {
        this.idReason = idReason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}

