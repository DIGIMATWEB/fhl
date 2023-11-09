
package com.fhl.sistemadedistribucionfh.Tickets.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Remitente {

    @SerializedName("razonSocial")
    @Expose
    private String razonSocial;
    @SerializedName("calle")
    @Expose
    private String calle;
    @SerializedName("numero")
    @Expose
    private String numero;
    @SerializedName("ciudad")
    @Expose
    private String ciudad;
    @SerializedName("estado")
    @Expose
    private String estado;
    @SerializedName("pais")
    @Expose
    private String pais;
    @SerializedName("codigoPostal")
    @Expose
    private String codigoPostal;
    @SerializedName("correoElectronico")
    @Expose
    private String correoElectronico;
    @SerializedName("telefono")
    @Expose
    private String telefono;
    @SerializedName("coordenadas")
    @Expose
    private String coordenadas;
    @SerializedName("checkpoint")
    @Expose
    private Object checkpoint;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Remitente() {
    }

    /**
     * 
     * @param checkpoint
     * @param razonSocial
     * @param estado
     * @param numero
     * @param codigoPostal
     * @param ciudad
     * @param calle
     * @param telefono
     * @param coordenadas
     * @param correoElectronico
     * @param pais
     */
    public Remitente(String razonSocial, String calle, String numero, String ciudad, String estado, String pais, String codigoPostal, String correoElectronico, String telefono, String coordenadas, Object checkpoint) {
        super();
        this.razonSocial = razonSocial;
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
        this.estado = estado;
        this.pais = pais;
        this.codigoPostal = codigoPostal;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.coordenadas = coordenadas;
        this.checkpoint = checkpoint;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
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

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Object getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(Object checkpoint) {
        this.checkpoint = checkpoint;
    }

}
