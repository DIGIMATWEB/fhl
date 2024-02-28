package com.fhl.sistemadedistribucionfh.checkList.model.v2;

import com.google.gson.annotations.SerializedName;

public class Checklist {
    @SerializedName("Nombre")
    private String nombre;
    @SerializedName("PonderacionMaxima")
    private Integer ponderacionMaxima;
    @SerializedName("FechaVencimiento")
    private String fechaVencimiento;
    @SerializedName("QuienEjecuta")
    private String quienEjecuta;
    @SerializedName("Id")
    private Integer id;

    public Checklist(String nombre, Integer ponderacionMaxima, String fechaVencimiento, String quienEjecuta, Integer id) {
        super();
        this.nombre = nombre;
        this.ponderacionMaxima = ponderacionMaxima;
        this.fechaVencimiento = fechaVencimiento;
        this.quienEjecuta = quienEjecuta;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPonderacionMaxima() {
        return ponderacionMaxima;
    }

    public void setPonderacionMaxima(Integer ponderacionMaxima) {
        this.ponderacionMaxima = ponderacionMaxima;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getQuienEjecuta() {
        return quienEjecuta;
    }

    public void setQuienEjecuta(String quienEjecuta) {
        this.quienEjecuta = quienEjecuta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
