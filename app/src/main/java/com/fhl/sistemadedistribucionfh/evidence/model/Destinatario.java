
package com.fhl.sistemadedistribucionfh.evidence.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Destinatario {

    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("Compania")
    @Expose
    private String compania;
    @SerializedName("Telefono")
    @Expose
    private String telefono;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Calle")
    @Expose
    private String calle;
    @SerializedName("Colonia")
    @Expose
    private String colonia;
    @SerializedName("Localidad")
    @Expose
    private String localidad;
    @SerializedName("Municipio")
    @Expose
    private String municipio;
    @SerializedName("Estado")
    @Expose
    private String estado;
    @SerializedName("Pais")
    @Expose
    private String pais;
    @SerializedName("CodigoPostal")
    @Expose
    private Integer codigoPostal;
    @SerializedName("Coordenadas")
    @Expose
    private String coordenadas;
    @SerializedName("Id")
    @Expose
    private Integer id;

    public Destinatario(String nombre, String compania, String telefono, String email, String calle, String colonia, String localidad, String municipio, String estado, String pais, Integer codigoPostal, String coordenadas, Integer id) {
        super();
        this.nombre = nombre;
        this.compania = compania;
        this.telefono = telefono;
        this.email = email;
        this.calle = calle;
        this.colonia = colonia;
        this.localidad = localidad;
        this.municipio = municipio;
        this.estado = estado;
        this.pais = pais;
        this.codigoPostal = codigoPostal;
        this.coordenadas = coordenadas;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
