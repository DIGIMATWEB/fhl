package com.fhl.sistemadedistribucionfh.mainContainerV2.modelV2;

import com.google.gson.annotations.SerializedName;

public class dataMenuItemsV2 implements Comparable<dataMenuItemsV2>{
    @SerializedName("ModuloPadreId")
    private Integer moduloPadreId;
    @SerializedName("ModuloPadre")
    private Object moduloPadre;
    @SerializedName("AplicacionId")
    private Integer aplicacionId;
    @SerializedName("Aplicacion")
    private Object aplicacion;
    @SerializedName("Orden")
    private Integer orden;
    @SerializedName("Titulo")
    private String titulo;
    @SerializedName("Icono")
    private String icono;
    @SerializedName("Url")
    private String url;
    @SerializedName("Id")
    private Integer id;
    @SerializedName("Trail")
    private String trail;

    public dataMenuItemsV2(Integer moduloPadreId, Object moduloPadre, Integer aplicacionId, Object aplicacion, Integer orden, String titulo, String icono, String url, Integer id, String trail) {
        super();
        this.moduloPadreId = moduloPadreId;
        this.moduloPadre = moduloPadre;
        this.aplicacionId = aplicacionId;
        this.aplicacion = aplicacion;
        this.orden = orden;
        this.titulo = titulo;
        this.icono = icono;
        this.url = url;
        this.id = id;
        this.trail = trail;
    }

    public Integer getModuloPadreId() {
        return moduloPadreId;
    }

    public void setModuloPadreId(Integer moduloPadreId) {
        this.moduloPadreId = moduloPadreId;
    }

    public Object getModuloPadre() {
        return moduloPadre;
    }

    public void setModuloPadre(Object moduloPadre) {
        this.moduloPadre = moduloPadre;
    }

    public Integer getAplicacionId() {
        return aplicacionId;
    }

    public void setAplicacionId(Integer aplicacionId) {
        this.aplicacionId = aplicacionId;
    }

    public Object getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(Object aplicacion) {
        this.aplicacion = aplicacion;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrail() {
        return trail;
    }

    public void setTrail(String trail) {
        this.trail = trail;
    }

    @Override
    public int compareTo(dataMenuItemsV2 menuItemsV2) {
        return getOrden().compareTo(menuItemsV2.getOrden());
    }
}
