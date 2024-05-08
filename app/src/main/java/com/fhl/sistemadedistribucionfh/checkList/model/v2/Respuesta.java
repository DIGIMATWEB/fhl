package com.fhl.sistemadedistribucionfh.checkList.model.v2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Respuesta implements Serializable {
    @SerializedName("CheckListId")
    @Expose
    private Integer checkListId;
    @SerializedName("PreguntaId")
    @Expose
    private Integer preguntaId;
    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("Ponderacion")
    @Expose
    private Integer ponderacion;
    @SerializedName("Usuario")
    @Expose
    private String usuario;
    @SerializedName("Id")
    @Expose
    private Integer id;

    /**
     * No args constructor for use in serialization
     *
     */
    public Respuesta() {
    }

    /**
     *
     * @param checkListId
     * @param ponderacion
     * @param usuario
     * @param preguntaId
     * @param id
     * @param nombre
     */
    public Respuesta(Integer checkListId, Integer preguntaId, String nombre, Integer ponderacion, String usuario, Integer id) {
        super();
        this.checkListId = checkListId;
        this.preguntaId = preguntaId;
        this.nombre = nombre;
        this.ponderacion = ponderacion;
        this.usuario = usuario;
        this.id = id;
    }

    public Integer getCheckListId() {
        return checkListId;
    }

    public void setCheckListId(Integer checkListId) {
        this.checkListId = checkListId;
    }

    public Integer getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(Integer preguntaId) {
        this.preguntaId = preguntaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPonderacion() {
        return ponderacion;
    }

    public void setPonderacion(Integer ponderacion) {
        this.ponderacion = ponderacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
