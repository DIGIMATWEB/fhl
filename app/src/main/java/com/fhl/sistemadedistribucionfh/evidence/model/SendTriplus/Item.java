
package com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Item implements Serializable {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Descripcion")
    @Expose
    private String descripcion;
    @SerializedName("Piezas")
    @Expose
    private Integer piezas;
    @SerializedName("Altura")
    @Expose
    private Double altura;
    @SerializedName("Largo")
    @Expose
    private Double largo;
    @SerializedName("Ancho")
    @Expose
    private Double ancho;
    @SerializedName("Valor")
    @Expose
    private Double valor;
    @SerializedName("Peso")
    @Expose
    private Double peso;
    @SerializedName("ProductoId")
    @Expose
    private String productoId;
    @SerializedName("UnidadMedidaId")
    @Expose
    private String unidadMedidaId;
    @SerializedName("ClavePeligrosoId")
    @Expose
    private String clavePeligrosoId;
    @SerializedName("UNId")
    @Expose
    private String uNId;
    @SerializedName("EmbalajeId")
    @Expose
    private String embalajeId;

    public Item(Integer id, String descripcion, Integer piezas, Double altura, Double largo, Double ancho, Double valor, Double peso, String productoId, String unidadMedidaId, String clavePeligrosoId, String uNId, String embalajeId) {
        super();
        this.id = id;
     this.descripcion = descripcion;
        this.piezas = piezas;
        this.altura = altura;
        this.largo = largo;
        this.ancho = ancho;
        this.valor = valor;
        this.peso = peso;
        this.productoId = productoId;
        this.unidadMedidaId = unidadMedidaId;
        this.clavePeligrosoId = clavePeligrosoId;
        this.uNId = uNId;
        this.embalajeId = embalajeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Integer getPiezas() {
        return piezas;
    }

    public void setPiezas(Integer piezas) {
        this.piezas = piezas;
    }
    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }
    public Double getLargo() {
        return largo;
    }

    public void setLargo(Double largo) {
        this.largo = largo;
    }
    public Double getAncho() {
        return ancho;
    }

    public void setAncho(Double ancho) {
        this.ancho = ancho;
    }
    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }
    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }
    public String getUnidadMedidaId() {
        return unidadMedidaId;
    }

    public void setUnidadMedidaId(String unidadMedidaId) {
        this.unidadMedidaId = unidadMedidaId;
    }
    public String getClavePeligrosoId() {
        return clavePeligrosoId;
    }

    public void setClavePeligrosoId(String clavePeligrosoId) {
        this.clavePeligrosoId = clavePeligrosoId;
    }
    public String getUNId() {
        return uNId;
    }

    public void setUNId(String uNId) {
        this.uNId = uNId;
    }
    public String getEmbalajeId() {
        return embalajeId;
    }

    public void setEmbalajeId(String embalajeId) {
        this.embalajeId = embalajeId;
    }

}
