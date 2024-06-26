
package com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cliente implements Serializable {

    @SerializedName("RazonSocial")
   
    private String razonSocial;
    @SerializedName("Rfc")
   
    private String rfc;
    @SerializedName("Id")
   
    private Integer id;
    @SerializedName("AxaptaId")
    private String AxaptaId;
    public Cliente(String razonSocial, String rfc, Integer id, String AxaptaId) {
        super();
        this.razonSocial = razonSocial;
        this.rfc = rfc;
        this.id = id;
        this.AxaptaId=  AxaptaId;
    }

    public String getRazonSocial() {
        if(razonSocial!=null) {
            return razonSocial;
        }else{
            return "";
        }
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

    public String getAxaptaId() {
        return AxaptaId;
    }

    public void setAxaptaId(String axaptaId) {
        AxaptaId = axaptaId;
    }
}
