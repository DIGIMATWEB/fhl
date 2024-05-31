package com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model;

import com.google.gson.annotations.SerializedName;

public class requestSetDatosValidador {
    @SerializedName("folioDespacho")
    private String folioDespacho;
    @SerializedName("vehicleVIM")
    private String vehicleVIM;
    @SerializedName("rfc")
    private String rfc;
    @SerializedName("habilidadesOperador")
    private String habilidadesOperador;
    @SerializedName("habilidadesVehiculo")
    private String habilidadesVehiculo;
    @SerializedName("user")
    private String user;

    public requestSetDatosValidador(String folioDespacho, String vehicleVIM, String rfc, String habilidadesOperador, String habilidadesVehiculo, String user) {
        super();
        this.folioDespacho = folioDespacho;
        this.vehicleVIM = vehicleVIM;
        this.rfc = rfc;
        this.habilidadesOperador = habilidadesOperador;
        this.habilidadesVehiculo = habilidadesVehiculo;
        this.user = user;
    }

    public String getFolioDespacho() {
        return folioDespacho;
    }

    public void setFolioDespacho(String folioDespacho) {
        this.folioDespacho = folioDespacho;
    }

    public String getVehicleVIM() {
        return vehicleVIM;
    }

    public void setVehicleVIM(String vehicleVIM) {
        this.vehicleVIM = vehicleVIM;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getHabilidadesOperador() {
        return habilidadesOperador;
    }

    public void setHabilidadesOperador(String habilidadesOperador) {
        this.habilidadesOperador = habilidadesOperador;
    }

    public String getHabilidadesVehiculo() {
        return habilidadesVehiculo;
    }

    public void setHabilidadesVehiculo(String habilidadesVehiculo) {
        this.habilidadesVehiculo = habilidadesVehiculo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
