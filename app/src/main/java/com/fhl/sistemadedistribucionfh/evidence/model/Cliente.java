
package com.fhl.sistemadedistribucionfh.evidence.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cliente {

    @SerializedName("RazonSocial")
    @Expose
    private String razonSocial;
    @SerializedName("Rfc")
    @Expose
    private String rfc;
    @SerializedName("Id")
    @Expose
    private Integer id;
    public Cliente(String razonSocial, String rfc, Integer id) {
        super();
        this.razonSocial = razonSocial;
        this.rfc = rfc;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
