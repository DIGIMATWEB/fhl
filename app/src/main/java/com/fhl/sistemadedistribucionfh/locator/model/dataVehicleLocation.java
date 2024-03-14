package com.fhl.sistemadedistribucionfh.locator.model;

import com.google.gson.annotations.SerializedName;

public class dataVehicleLocation {
    @SerializedName("id")
    private Integer id;
    @SerializedName("placa")
    private String placa;
    @SerializedName("economico")
    private String economico;
    @SerializedName("vin")
    private String vin;
    @SerializedName("latitud")
    private Object latitud;
    @SerializedName("longitud")
    private Object longitud;
    @SerializedName("foto")
    private Object foto;

    public dataVehicleLocation(Integer id, String placa, String economico, String vin, Object latitud, Object longitud, Object foto) {
        super();
        this.id = id;
        this.placa = placa;
        this.economico = economico;
        this.vin = vin;
        this.latitud = latitud;
        this.longitud = longitud;
        this.foto = foto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Object getLatitud() {
        return latitud;
    }

    public void setLatitud(Object latitud) {
        this.latitud = latitud;
    }

    public Object getLongitud() {
        return longitud;
    }

    public void setLongitud(Object longitud) {
        this.longitud = longitud;
    }

    public Object getFoto() {
        return foto;
    }

    public void setFoto(Object foto) {
        this.foto = foto;
    }
}
