package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.cortina;

import com.google.gson.annotations.SerializedName;

public class dataCortina {


    @SerializedName("Id")
    private Integer id;
    @SerializedName("Origen")
    private String origen;
    @SerializedName("Destino")
    private String destino;
    @SerializedName("FechaCreacion")
    private String fechaCreacion;
    @SerializedName("AndenId")
    private Integer andenId;
    @SerializedName("Anden")
    private Anden anden;
    @SerializedName("FolioDespacho")
    private String folioDespacho;

    public dataCortina(Integer id, String origen, String destino, String fechaCreacion, Integer andenId, Anden anden, String folioDespacho) {
        super();
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.fechaCreacion = fechaCreacion;
        this.andenId = andenId;
        this.anden = anden;
        this.folioDespacho = folioDespacho;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        if(destino!=null){
        return destino;
        }else{
            return "Destino no valido registrado en la cortina";
        }
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getAndenId() {
        return andenId;
    }

    public void setAndenId(Integer andenId) {
        this.andenId = andenId;
    }

    public Anden getAnden() {
        return anden;
    }

    public void setAnden(Anden anden) {
        this.anden = anden;
    }

    public String getFolioDespacho() {
        return folioDespacho;
    }

    public void setFolioDespacho(String folioDespacho) {
        this.folioDespacho = folioDespacho;
    }

}
