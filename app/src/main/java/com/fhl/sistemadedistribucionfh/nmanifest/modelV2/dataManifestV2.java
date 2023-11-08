package com.fhl.sistemadedistribucionfh.nmanifest.modelV2;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class dataManifestV2 {
    @SerializedName("auxiliares")
    private List<auxiliaresDataV2> auxiliares;

    @SerializedName("serviciosAdicionales")
    private List<serviciosAdicionalesDataV2> serviciosAdicionales;
}
