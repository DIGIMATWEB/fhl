package com.fhl.sistemadedistribucionfh.Dialogs.habilities.model.vehicle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataresponseVehicle {

    @SerializedName("placa")
    @Expose
    private String placa;
    @SerializedName("economico")
    @Expose
    private String economico;
    @SerializedName("vin")
    @Expose
    private String vin;
    @SerializedName("habilidadVehiculos")
    @Expose
    private String habilidadVehiculos;

    public DataresponseVehicle(String placa, String economico, String vin, String habilidadVehiculos) {
        super();
        this.placa = placa;
        this.economico = economico;
        this.vin = vin;
        this.habilidadVehiculos = habilidadVehiculos;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getEconomico() {
        return economico;
    }

    public void setEconomico(String economico) {
        this.economico = economico;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getHabilidadVehiculos() {
        return habilidadVehiculos;
    }

    public void setHabilidadVehiculos(String habilidadVehiculos) {
        this.habilidadVehiculos = habilidadVehiculos;
    }
}
