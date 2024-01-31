
package com.fhl.sistemadedistribucionfh.Sellos.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Sello {

    @SerializedName("QrCodigo")
    @Expose
    private String qrCodigo;
    @SerializedName("NumeroSello")
    @Expose
    private String numeroSello;
    @SerializedName("DespachoId")
    @Expose
    private Integer despachoId;
    @SerializedName("Id")
    @Expose
    private Integer id;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Sello() {
    }

    /**
     * 
     * @param qrCodigo
     * @param numeroSello
     * @param despachoId
     * @param id
     */
    public Sello(String qrCodigo, String numeroSello, Integer despachoId, Integer id) {
        super();
        this.qrCodigo = qrCodigo;
        this.numeroSello = numeroSello;
        this.despachoId = despachoId;
        this.id = id;
    }

    public String getQrCodigo() {
        return qrCodigo;
    }

    public void setQrCodigo(String qrCodigo) {
        this.qrCodigo = qrCodigo;
    }

    public String getNumeroSello() {
        return numeroSello;
    }

    public void setNumeroSello(String numeroSello) {
        this.numeroSello = numeroSello;
    }

    public Integer getDespachoId() {
        return despachoId;
    }

    public void setDespachoId(Integer despachoId) {
        this.despachoId = despachoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
