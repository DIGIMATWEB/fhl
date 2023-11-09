
package com.fhl.sistemadedistribucionfh.Tickets.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Item {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("piezas")
    @Expose
    private Integer piezas;
    @SerializedName("altura")
    @Expose
    private Integer altura;
    @SerializedName("largo")
    @Expose
    private Integer largo;
    @SerializedName("ancho")
    @Expose
    private Integer ancho;
    @SerializedName("monedaIdValor")
    @Expose
    private Object monedaIdValor;
    @SerializedName("valor")
    @Expose
    private Integer valor;
    @SerializedName("peso")
    @Expose
    private Integer peso;
    @SerializedName("productoId")
    @Expose
    private Integer productoId;
    @SerializedName("unidadMedidaId")
    @Expose
    private Integer unidadMedidaId;
    @SerializedName("clavePeligrosoId")
    @Expose
    private Integer clavePeligrosoId;
    @SerializedName("unId")
    @Expose
    private Integer unId;
    @SerializedName("embalajeId")
    @Expose
    private Integer embalajeId;
    @SerializedName("trail")
    @Expose
    private Object trail;

    public Item(Integer id, String descripcion, Integer piezas, Integer altura, Integer largo, Integer ancho, Object monedaIdValor, Integer valor, Integer peso, Integer productoId, Integer unidadMedidaId, Integer clavePeligrosoId, Integer unId, Integer embalajeId, Object trail) {
        super();
        this.id = id;
        this.descripcion = descripcion;
        this.piezas = piezas;
        this.altura = altura;
        this.largo = largo;
        this.ancho = ancho;
        this.monedaIdValor = monedaIdValor;
        this.valor = valor;
        this.peso = peso;
        this.productoId = productoId;
        this.unidadMedidaId = unidadMedidaId;
        this.clavePeligrosoId = clavePeligrosoId;
        this.unId = unId;
        this.embalajeId = embalajeId;
        this.trail = trail;
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

    public Object getMonedaIdValor() {
        return monedaIdValor;
    }

    public void setMonedaIdValor(Object monedaIdValor) {
        this.monedaIdValor = monedaIdValor;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public Integer getUnidadMedidaId() {
        return unidadMedidaId;
    }

    public void setUnidadMedidaId(Integer unidadMedidaId) {
        this.unidadMedidaId = unidadMedidaId;
    }

    public Integer getClavePeligrosoId() {
        return clavePeligrosoId;
    }

    public void setClavePeligrosoId(Integer clavePeligrosoId) {
        this.clavePeligrosoId = clavePeligrosoId;
    }

    public Integer getUnId() {
        return unId;
    }

    public void setUnId(Integer unId) {
        this.unId = unId;
    }

    public Integer getEmbalajeId() {
        return embalajeId;
    }

    public void setEmbalajeId(Integer embalajeId) {
        this.embalajeId = embalajeId;
    }

    public Object getTrail() {
        return trail;
    }

    public void setTrail(Object trail) {
        this.trail = trail;
    }

}
