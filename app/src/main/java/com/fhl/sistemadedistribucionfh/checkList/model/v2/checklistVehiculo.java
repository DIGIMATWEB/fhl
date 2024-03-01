package com.fhl.sistemadedistribucionfh.checkList.model.v2;

import com.google.gson.annotations.SerializedName;

public class checklistVehiculo {
    @SerializedName("Placa")
    private String placa;
    @SerializedName("Economico")
    private String economico;
    @SerializedName("Vin")
    private String vin;
    @SerializedName("Id")
    private Integer id;


    public checklistVehiculo(String placa, String economico, String vin, Integer id) {
        super();
        this.placa = placa;
        this.economico = economico;
        this.vin = vin;
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
