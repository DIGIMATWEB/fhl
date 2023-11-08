package com.fhl.sistemadedistribucionfh.nmanifest.modelV2;

import com.google.gson.annotations.SerializedName;

public class serviciosAdicionalesDataV2 {
    @SerializedName("id")
    private Integer id;
    @SerializedName("descripcion")
    private String descripcion;
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
    private String descripcionErrorTexto;
    @SerializedName("cantidadError")
    private Boolean cantidadError;
    @SerializedName("cantidadErrorTexto")
    private String cantidadErrorTexto;
    @SerializedName("costoUnitarioError")
    private Boolean costoUnitarioError;
    @SerializedName("costoUnitarioErrorTexto")
    private String costoUnitarioErrorTexto;

    public serviciosAdicionalesDataV2(Integer id, String descripcion, String cantidad, String costoUnitario, Integer divisa, Integer servicioAdicionalId, Boolean editDetalle, Boolean descripcionError, String descripcionErrorTexto, Boolean cantidadError, String cantidadErrorTexto, Boolean costoUnitarioError, String costoUnitarioErrorTexto) {
        super();
        this.id = id;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.costoUnitario = costoUnitario;
        this.divisa = divisa;
        this.servicioAdicionalId = servicioAdicionalId;
        this.editDetalle = editDetalle;
        this.descripcionError = descripcionError;
        this.descripcionErrorTexto = descripcionErrorTexto;
        this.cantidadError = cantidadError;
        this.cantidadErrorTexto = cantidadErrorTexto;
        this.costoUnitarioError = costoUnitarioError;
        this.costoUnitarioErrorTexto = costoUnitarioErrorTexto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(String costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public Integer getDivisa() {
        return divisa;
    }

    public void setDivisa(Integer divisa) {
        this.divisa = divisa;
    }

    public Integer getServicioAdicionalId() {
        return servicioAdicionalId;
    }

    public void setServicioAdicionalId(Integer servicioAdicionalId) {
        this.servicioAdicionalId = servicioAdicionalId;
    }

    public Boolean getEditDetalle() {
        return editDetalle;
    }

    public void setEditDetalle(Boolean editDetalle) {
        this.editDetalle = editDetalle;
    }

    public Boolean getDescripcionError() {
        return descripcionError;
    }

    public void setDescripcionError(Boolean descripcionError) {
        this.descripcionError = descripcionError;
    }

    public String getDescripcionErrorTexto() {
        return descripcionErrorTexto;
    }

    public void setDescripcionErrorTexto(String descripcionErrorTexto) {
        this.descripcionErrorTexto = descripcionErrorTexto;
    }

    public Boolean getCantidadError() {
        return cantidadError;
    }

    public void setCantidadError(Boolean cantidadError) {
        this.cantidadError = cantidadError;
    }

    public String getCantidadErrorTexto() {
        return cantidadErrorTexto;
    }

    public void setCantidadErrorTexto(String cantidadErrorTexto) {
        this.cantidadErrorTexto = cantidadErrorTexto;
    }

    public Boolean getCostoUnitarioError() {
        return costoUnitarioError;
    }

    public void setCostoUnitarioError(Boolean costoUnitarioError) {
        this.costoUnitarioError = costoUnitarioError;
    }

    public String getCostoUnitarioErrorTexto() {
        return costoUnitarioErrorTexto;
    }

    public void setCostoUnitarioErrorTexto(String costoUnitarioErrorTexto) {
        this.costoUnitarioErrorTexto = costoUnitarioErrorTexto;
    }
}
