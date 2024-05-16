package com.fhl.sistemadedistribucionfh.Dialogs.habilities.model.driver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataresponseDriver {
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("rfc")
    @Expose
    private String rfc;
    @SerializedName("habilidades")
    @Expose
    private String habilidades;
    @SerializedName("tipoVehiculo")
    @Expose
    private String tipoVehiculo;

    public DataresponseDriver(String nombre, String rfc, String habilidades, String tipoVehiculo) {
        super();
        this.nombre = nombre;
        this.rfc = rfc;
        this.habilidades = habilidades;
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }
}
