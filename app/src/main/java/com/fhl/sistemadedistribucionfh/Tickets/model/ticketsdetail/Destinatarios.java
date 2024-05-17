
package com.fhl.sistemadedistribucionfh.Tickets.model.ticketsdetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Destinatarios implements Serializable {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("RazonSocial")
    @Expose
    private String razonSocial;
    @SerializedName("RFC")
    @Expose
    private String rfc;
    @SerializedName("Coordenadas")
    @Expose
    private String coordenadas;
    @SerializedName("CodigoPostal")
    @Expose
    private Integer codigoPostal;
    @SerializedName("ContactoId")
    @Expose
    private Integer contactoId;
    @SerializedName("Contacto")
    @Expose
    private Contacto contacto;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Destinatarios() {
    }

    /**
     * 
     * @param razonSocial
     * @param contacto
     * @param codigoPostal
     * @param id
     * @param coordenadas
     * @param contactoId
     * @param rfc
     */
    public Destinatarios(Integer id, String razonSocial, String rfc, String coordenadas, Integer codigoPostal, Integer contactoId, Contacto contacto) {
        super();
        this.id = id;
        this.razonSocial = razonSocial;
        this.rfc = rfc;
        this.coordenadas = coordenadas;
        this.codigoPostal = codigoPostal;
        this.contactoId = contactoId;
        this.contacto = contacto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Integer getContactoId() {
        return contactoId;
    }

    public void setContactoId(Integer contactoId) {
        this.contactoId = contactoId;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

}
