
package com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vehiculo {

    @SerializedName("Placa")
    @Expose
    private String placa;
    @SerializedName("Economico")
    @Expose
    private String economico;
    @SerializedName("Vin")
    @Expose
    private String vin;
    @SerializedName("Anio")
    @Expose
    private Integer anio;
    @SerializedName("TanqueCombustible")
    @Expose
    private Integer tanqueCombustible;
    @SerializedName("MarcaId")
    @Expose
    private Integer marcaId;
    @SerializedName("Marca")
    @Expose
    private Marca marca;
    @SerializedName("ModeloId")
    @Expose
    private Integer modeloId;
    @SerializedName("Modelo")
    @Expose
    private Modelo modelo;
    @SerializedName("Id")
    @Expose
    private Integer id;

    @SerializedName("CodigoBarras ")
    private String barcodeVehicle;

    public Vehiculo(String placa, String economico, String vin, Integer anio, Integer tanqueCombustible, Integer marcaId, Marca marca, Integer modeloId, Modelo modelo, Integer id,String barcodeVehicle) {
        super();
        this.placa = placa;
        this.economico = economico;
        this.vin = vin;
        this.anio = anio;
        this.tanqueCombustible = tanqueCombustible;
        this.marcaId = marcaId;
        this.marca = marca;
        this.modeloId = modeloId;
        this.modelo = modelo;
        this.id = id;
        this.barcodeVehicle=barcodeVehicle;
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

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getTanqueCombustible() {
        return tanqueCombustible;
    }

    public void setTanqueCombustible(Integer tanqueCombustible) {
        this.tanqueCombustible = tanqueCombustible;
    }

    public Integer getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Integer marcaId) {
        this.marcaId = marcaId;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Integer getModeloId() {
        return modeloId;
    }

    public void setModeloId(Integer modeloId) {
        this.modeloId = modeloId;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBarcodeVehicle() {
        return barcodeVehicle;
    }

    public void setBarcodeVehicle(String barcodeVehicle) {
        this.barcodeVehicle = barcodeVehicle;
    }
}
