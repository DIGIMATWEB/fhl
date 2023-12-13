
package com.fhl.sistemadedistribucionfh.nmanifest.modelV2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vehiculo {

    @SerializedName("Placa")
    @Expose
    private String placa;
    @SerializedName("Economico")
    @Expose
    private String economico;
    @SerializedName("VIN")
    @Expose
    private String vin;
    @SerializedName("TanqueCombustible")
    @Expose
    private Integer tanqueCombustible;
    @SerializedName("Anio")
    @Expose
    private Integer anio;
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

    /**
     * No args constructor for use in serialization
     * 
     */
    public Vehiculo() {
    }

    /**
     * 
     * @param tanqueCombustible
     * @param marca
     * @param marcaId
     * @param economico
     * @param vin
     * @param modeloId
     * @param modelo
     * @param anio
     * @param placa
     */
    public Vehiculo(String placa, String economico, String vin, Integer tanqueCombustible, Integer anio, Integer marcaId, Marca marca, Integer modeloId, Modelo modelo) {
        super();
        this.placa = placa;
        this.economico = economico;
        this.vin = vin;
        this.tanqueCombustible = tanqueCombustible;
        this.anio = anio;
        this.marcaId = marcaId;
        this.marca = marca;
        this.modeloId = modeloId;
        this.modelo = modelo;
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

    public Integer getTanqueCombustible() {
        return tanqueCombustible;
    }

    public void setTanqueCombustible(Integer tanqueCombustible) {
        this.tanqueCombustible = tanqueCombustible;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
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

}
