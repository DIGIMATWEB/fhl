package com.fhl.sistemadedistribucionfh.checkList.model.v2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pregunta {
    @SerializedName("Respuestas")
    @Expose
    private List<Respuesta> respuestas;
    @SerializedName("CheckListId")
    @Expose
    private Integer checkListId;
    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("TipoCampo")
    @Expose
    private Integer tipoCampo;
    @SerializedName("Categoria")
    @Expose
    private String categoria;
    @SerializedName("GeneraFalla")
    @Expose
    private Boolean generaFalla;
    @SerializedName("FotoRequerida")
    @Expose
    private Boolean fotoRequerida;
    @SerializedName("Evidencia")
    @Expose
    private Boolean evidencia;
    @SerializedName("Usuario")
    @Expose
    private String usuario;
    @SerializedName("Id")
    @Expose
    private Integer id;

    public Pregunta(List<Respuesta> respuestas, Integer checkListId, String nombre, Integer tipoCampo, String categoria, Boolean generaFalla, Boolean fotoRequerida, Boolean evidencia, String usuario, Integer id) {
        super();
        this.respuestas = respuestas;
        this.checkListId = checkListId;
        this.nombre = nombre;
        this.tipoCampo = tipoCampo;
        this.categoria = categoria;
        this.generaFalla = generaFalla;
        this.fotoRequerida = fotoRequerida;
        this.evidencia = evidencia;
        this.usuario = usuario;
        this.id = id;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public Integer getCheckListId() {
        return checkListId;
    }

    public void setCheckListId(Integer checkListId) {
        this.checkListId = checkListId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTipoCampo() {
        return tipoCampo;
    }

    public void setTipoCampo(Integer tipoCampo) {
        this.tipoCampo = tipoCampo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Boolean getGeneraFalla() {
        return generaFalla;
    }

    public void setGeneraFalla(Boolean generaFalla) {
        this.generaFalla = generaFalla;
    }

    public Boolean getFotoRequerida() {
        return fotoRequerida;
    }

    public void setFotoRequerida(Boolean fotoRequerida) {
        this.fotoRequerida = fotoRequerida;
    }

    public Boolean getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(Boolean evidencia) {
        this.evidencia = evidencia;
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
