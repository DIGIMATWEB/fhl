package com.fhl.sistemadedistribucionfh.checkList.model.v2;

import com.google.gson.annotations.SerializedName;

public class DespachoV {
    @SerializedName("FechaCreacion")
    private String fechaCreacion;
    @SerializedName("Id")
    private Integer id;
    @SerializedName("FolioDespacho")
    private String folioDespacho;

    public DespachoV(String fechaCreacion, Integer id, String folioDespacho) {
        super();
        this.fechaCreacion = fechaCreacion;
        this.id = id;
        this.folioDespacho = folioDespacho;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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
