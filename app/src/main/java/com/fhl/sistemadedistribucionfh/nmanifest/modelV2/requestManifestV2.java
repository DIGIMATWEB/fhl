package com.fhl.sistemadedistribucionfh.nmanifest.modelV2;

import com.google.gson.annotations.SerializedName;

public class requestManifestV2 {
    @SerializedName("operadorId")
    private Integer operadorId;

    public requestManifestV2(Integer operadorId) {
        super();
        this.operadorId = operadorId;
    }

    public Integer getOperadorId() {
        return operadorId;
    }

    public void setOperadorId(Integer operadorId) {
        this.operadorId = operadorId;
    }
}
