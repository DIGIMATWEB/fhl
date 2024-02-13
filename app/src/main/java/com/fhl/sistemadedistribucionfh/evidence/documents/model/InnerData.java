
package com.fhl.sistemadedistribucionfh.evidence.documents.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class InnerData {

    @SerializedName("EvidenciaId")
    @Expose
    private Integer evidenciaId;
    @SerializedName("DocumentoId")
    @Expose
    private Integer documentoId;
    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("Extension")
    @Expose
    private String extension;

    /**
     * No args constructor for use in serialization
     * 
     */
    public InnerData() {
    }

    /**
     * 
     * @param extension
     * @param evidenciaId
     * @param documentoId
     * @param nombre
     */
    public InnerData(Integer evidenciaId, Integer documentoId, String nombre, String extension) {
        super();
        this.evidenciaId = evidenciaId;
        this.documentoId = documentoId;
        this.nombre = nombre;
        this.extension = extension;
    }

    public Integer getEvidenciaId() {
        return evidenciaId;
    }

    public void setEvidenciaId(Integer evidenciaId) {
        this.evidenciaId = evidenciaId;
    }

    public Integer getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(Integer documentoId) {
        this.documentoId = documentoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

}
