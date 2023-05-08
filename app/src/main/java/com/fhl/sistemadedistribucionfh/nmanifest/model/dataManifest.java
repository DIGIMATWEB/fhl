package com.fhl.sistemadedistribucionfh.nmanifest.model;

import com.google.gson.annotations.SerializedName;

public class dataManifest {
    @SerializedName("manifestRef")
    private String idmanifest;
    @SerializedName("cedis")
    private String cedis;
    @SerializedName("referencia")
    private String referencia;
    @SerializedName("supervisor")
    private String supervisor;
    @SerializedName("vehiculo")
    private String vehiculo;
    @SerializedName("salida")
    private String salida;
    @SerializedName("placa")
    private String placa;
    @SerializedName("regreso")
    private String regreso;
    @SerializedName("validacion")
    private String validacion;

    public dataManifest(String idmanifest, String cedis, String referencia, String supervisor, String vehiculo, String salida, String placa, String regreso, String validacion) {
        super();
        this.idmanifest = idmanifest;
        this.cedis = cedis;
        this.referencia = referencia;
        this.supervisor = supervisor;
        this.vehiculo = vehiculo;
        this.salida = salida;
        this.placa = placa;
        this.regreso = regreso;
        this.validacion = validacion;
    }

    public String getIdmanifest() {
        return idmanifest;
    }

    public void setIdmanifest(String idmanifest) {
        this.idmanifest = idmanifest;
    }

    public String getCedis() {
        return cedis;
    }

    public void setCedis(String cedis) {
        this.cedis = cedis;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getRegreso() {
        return regreso;
    }

    public void setRegreso(String regreso) {
        this.regreso = regreso;
    }

    public String getValidacion() {
        return validacion;
    }

    public void setValidacion(String validacion) {
        this.validacion = validacion;
    }

}
