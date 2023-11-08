package com.fhl.sistemadedistribucionfh.nmanifest.modelV2;

import com.google.gson.annotations.SerializedName;

public class serviciosAdicionalesDataV2 {
    @SerializedName("id")
    private Integer id;

    @SerializedName("descripcion")
    private Object descripcion;

    @SerializedName("cantidad")
    private String cantidad;

    @SerializedName("costoUnitario")
    private String costoUnitario;

    @SerializedName("divisa")
    private Integer divisa;

    @SerializedName("servicioAdicionalId")
    private Integer servicioAdicionalId;

    @SerializedName("editDetalle")
    private Boolean editDetalle;

    @SerializedName("descripcionError")
    private Boolean descripcionError;

    @SerializedName("descripcionErrorTexto")
    private Object descripcionErrorTexto;

    @SerializedName("cantidadError")
    private Boolean cantidadError;

    @SerializedName("cantidadErrorTexto")
    private Object cantidadErrorTexto;

    @SerializedName("costoUnitarioError")
    private Boolean costoUnitarioError;

    @SerializedName("costoUnitarioErrorTexto")
    private Object costoUnitarioErrorTexto;
}
