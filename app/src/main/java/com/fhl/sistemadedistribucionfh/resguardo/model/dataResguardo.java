package com.fhl.sistemadedistribucionfh.resguardo.model;

import com.google.gson.annotations.SerializedName;

public class dataResguardo {
    @SerializedName("registra")
    private String registra;
    @SerializedName("articulo")
    private String articulo;
    @SerializedName("IMEI")
    private String imei;
    @SerializedName("entrada")
    private String entrada;
    @SerializedName("salida")
    private String salida;

    public dataResguardo(String registra, String articulo, String imei, String entrada, String salida) {
        super();
        this.registra = registra;
        this.articulo = articulo;
        this.imei = imei;
        this.entrada = entrada;
        this.salida = salida;
    }

    public String getRegistra() {
        return registra;
    }

    public void setRegistra(String registra) {
        this.registra = registra;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }
}
