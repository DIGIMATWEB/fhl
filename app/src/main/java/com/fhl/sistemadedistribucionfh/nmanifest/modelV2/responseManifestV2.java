package com.fhl.sistemadedistribucionfh.nmanifest.modelV2;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseManifestV2 {
    @SerializedName("totalRows")
    private Integer totalRows;

    @SerializedName("pageIndex")
    private Integer pageIndex;

    @SerializedName("pageSize")
    private Integer pageSize;

    @SerializedName("status")
    private Integer status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<dataManifestV2> data;
}
