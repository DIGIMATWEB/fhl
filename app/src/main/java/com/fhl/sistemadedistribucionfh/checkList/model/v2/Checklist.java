package com.fhl.sistemadedistribucionfh.checkList.model.v2;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Checklist {

    @SerializedName("CorreosNotificacion")
    private List<String> correosNotificacion;
    @SerializedName("Preguntas")
    private List<Pregunta> preguntas;
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

    /**
     * No args constructor for use in serialization
     *
     */
    public Checklist() {
    }

    /**
     *
     * @param ponderacionMaxima
     * @param fechaVencimiento
     * @param correosNotificacion
     * @param preguntas
     * @param id
     * @param quienEjecuta
     * @param nombre
     */
    public Checklist(List<String> correosNotificacion, List<Pregunta> preguntas, String nombre, Integer ponderacionMaxima, String fechaVencimiento, String quienEjecuta, Integer id) {
        super();
        this.correosNotificacion = correosNotificacion;
        this.preguntas = preguntas;
        this.nombre = nombre;
        this.ponderacionMaxima = ponderacionMaxima;
        this.fechaVencimiento = fechaVencimiento;
        this.quienEjecuta = quienEjecuta;
        this.id = id;
    }

    public List<String> getCorreosNotificacion() {
        return correosNotificacion;
    }

    public void setCorreosNotificacion(List<String> correosNotificacion) {
        this.correosNotificacion = correosNotificacion;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
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
