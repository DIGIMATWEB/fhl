package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.cortina;

import com.google.gson.annotations.SerializedName;

public class Anden {


    @SerializedName("CedisID")
    private Integer cedisID;
    @SerializedName("Nombre")
    private String nombre;
    @SerializedName("AndenCortina")
    private Boolean andenCortina;
    @SerializedName("CodigoAnden")
    private String codigoAnden;
    @SerializedName("Id")
    private Integer id;

    public Anden(Integer cedisID, String nombre, Boolean andenCortina, String codigoAnden, Integer id) {
        super();
        this.cedisID = cedisID;
        this.nombre = nombre;
        this.andenCortina = andenCortina;
        this.codigoAnden = codigoAnden;
        this.id = id;
    }

    public Integer getCedisID() {
        return cedisID;
    }

    public void setCedisID(Integer cedisID) {
        this.cedisID = cedisID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getAndenCortina() {
        return andenCortina;
    }

    public void setAndenCortina(Boolean andenCortina) {
        this.andenCortina = andenCortina;
    }

    public String getCodigoAnden() {
        return codigoAnden;
    }

    public void setCodigoAnden(String codigoAnden) {
        this.codigoAnden = codigoAnden;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
