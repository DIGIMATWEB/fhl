
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
    private Integer altura;
    @SerializedName("Largo")
    @Expose
    private Integer largo;
    @SerializedName("Ancho")
    @Expose
    private Integer ancho;
    @SerializedName("Valor")
    @Expose
    private Integer valor;
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
    private Integer clavePeligrosoId;
    @SerializedName("UNId")
    @Expose
    private String uNId;
    @SerializedName("EmbalajeId")
    @Expose
    private String embalajeId;

    public Item(Integer id, String descripcion, Integer piezas, Integer altura, Integer largo, Integer ancho, Integer valor, Double peso, String productoId, String unidadMedidaId, Integer clavePeligrosoId, String uNId, String embalajeId) {
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

    public Integer getAltura() {
        return altura;
    }

    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    public Integer getLargo() {
        return largo;
    }

    public void setLargo(Integer largo) {
        this.largo = largo;
    }

    public Integer getAncho() {
        return ancho;
    }

    public void setAncho(Integer ancho) {
        this.ancho = ancho;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
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

    public Integer getClavePeligrosoId() {
        return clavePeligrosoId;
    }

    public void setClavePeligrosoId(Integer clavePeligrosoId) {
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
