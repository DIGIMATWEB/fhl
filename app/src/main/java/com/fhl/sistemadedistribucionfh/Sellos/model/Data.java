
package com.fhl.sistemadedistribucionfh.Sellos.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data  implements Serializable {

    @SerializedName("QrCodigo")
    @Expose
    private String qrCodigo;
    @SerializedName("Sellos")
    @Expose
    private List<Sello> sellos;
    @SerializedName("Origen")
    @Expose
    private String origen;
    @SerializedName("Destino")
    @Expose
    private String destino;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("FolioDespacho")
    @Expose
    private String folioDespacho;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param qrCodigo
     * @param sellos
     * @param origen
     * @param destino
     * @param id
     * @param folioDespacho
     */
    public Data(String qrCodigo, List<Sello> sellos, String origen, String destino, Integer id, String folioDespacho) {
        super();
        this.qrCodigo = qrCodigo;
        this.sellos = sellos;
        this.origen = origen;
        this.destino = destino;
        this.id = id;
        this.folioDespacho = folioDespacho;
    }

    public String getQrCodigo() {
        return qrCodigo;
    }

    public void setQrCodigo(String qrCodigo) {
        this.qrCodigo = qrCodigo;
    }

    public List<Sello> getSellos() {
        return sellos;
    }

    public void setSellos(List<Sello> sellos) {
        this.sellos = sellos;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFolioDespacho() {
        return folioDespacho;
    }

    public void setFolioDespacho(String folioDespacho) {
        this.folioDespacho = folioDespacho;
    }

}
