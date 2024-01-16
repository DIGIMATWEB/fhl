
package com.fhl.sistemadedistribucionfh.Tickets.model.ticketsdetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cliente {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("RazonSocial")
    @Expose
    private String razonSocial;
    @SerializedName("RFC")
    @Expose
    private String rfc;
    public Cliente(Integer id, String razonSocial, String rfc) {
        super();
        this.id = id;
        this.razonSocial = razonSocial;
        this.rfc = rfc;
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

}
