
package com.fhl.sistemadedistribucionfh.nmanifest.modelV2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiciosAdicionale {

    @SerializedName("Id")
    @Expose
    private Object id;
    @SerializedName("Descripcion")
    @Expose
    private Object descripcion;
    @SerializedName("Cantidad")
    @Expose
    private String cantidad;
    @SerializedName("CostoUnitario")
    @Expose
    private String costoUnitario;
    @SerializedName("Divisa")
    @Expose
    private Integer divisa;
    @SerializedName("ServicioAdicionalId")
    @Expose
    private Integer servicioAdicionalId;
    @SerializedName("EditDetalle")
    @Expose
    private Boolean editDetalle;
    @SerializedName("DescripcionError")
    @Expose
    private Boolean descripcionError;
    @SerializedName("DescripcionErrorTexto")
    @Expose
    private Object descripcionErrorTexto;
    @SerializedName("CantidadError")
    @Expose
    private Boolean cantidadError;
    @SerializedName("CantidadErrorTexto")
    @Expose
    private Object cantidadErrorTexto;
    @SerializedName("CostoUnitarioError")
    @Expose
    private Boolean costoUnitarioError;
    @SerializedName("CostoUnitarioErrorTexto")
    @Expose
    private Object costoUnitarioErrorTexto;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ServiciosAdicionale() {
    }

    /**
     * 
     * @param descripcion
     * @param divisa
     * @param servicioAdicionalId
     * @param costoUnitarioError
     * @param costoUnitario
     * @param descripcionErrorTexto
     * @param costoUnitarioErrorTexto
     * @param cantidadError
     * @param id
     * @param cantidad
     * @param editDetalle
     * @param descripcionError
     * @param cantidadErrorTexto
     */
    public ServiciosAdicionale(Object id, Object descripcion, String cantidad, String costoUnitario, Integer divisa, Integer servicioAdicionalId, Boolean editDetalle, Boolean descripcionError, Object descripcionErrorTexto, Boolean cantidadError, Object cantidadErrorTexto, Boolean costoUnitarioError, Object costoUnitarioErrorTexto) {
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

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(Object descripcion) {
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

    public Object getDescripcionErrorTexto() {
        return descripcionErrorTexto;
    }

    public void setDescripcionErrorTexto(Object descripcionErrorTexto) {
        this.descripcionErrorTexto = descripcionErrorTexto;
    }

    public Boolean getCantidadError() {
        return cantidadError;
    }

    public void setCantidadError(Boolean cantidadError) {
        this.cantidadError = cantidadError;
    }

    public Object getCantidadErrorTexto() {
        return cantidadErrorTexto;
    }

    public void setCantidadErrorTexto(Object cantidadErrorTexto) {
        this.cantidadErrorTexto = cantidadErrorTexto;
    }

    public Boolean getCostoUnitarioError() {
        return costoUnitarioError;
    }

    public void setCostoUnitarioError(Boolean costoUnitarioError) {
        this.costoUnitarioError = costoUnitarioError;
    }

    public Object getCostoUnitarioErrorTexto() {
        return costoUnitarioErrorTexto;
    }

    public void setCostoUnitarioErrorTexto(Object costoUnitarioErrorTexto) {
        this.costoUnitarioErrorTexto = costoUnitarioErrorTexto;
    }

}
