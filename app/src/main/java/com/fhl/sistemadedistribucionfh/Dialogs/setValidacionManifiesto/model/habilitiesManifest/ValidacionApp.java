package com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.habilitiesManifest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ValidacionApp {
    @SerializedName("HabilidadesVehiculos")
    
    private List<HabilidadesVehiculo> habilidadesVehiculos;
    @SerializedName("DocumentosVencimientosVehiculos")
    
    private List<DocumentosVencimientosVehiculo> documentosVencimientosVehiculos;
    @SerializedName("HabilidadesOperadores")
    
    private List<HabilidadesOperadore> habilidadesOperadores;
    @SerializedName("DocumentosOperadoresVencimientos")
    
    private List<DocumentosOperadoresVencimiento> documentosOperadoresVencimientos;
    @SerializedName("HabilidadesAuxiliares")
    
    private List<HabilidadesAuxiliare> habilidadesAuxiliares;
    @SerializedName("DocumentosAuxiliaresVencimientos")
    
    private List<DocumentosAuxiliaresVencimiento> documentosAuxiliaresVencimientos;
    @SerializedName("EvidenciasCargas")
    
    private List<EvidenciasCarga> evidenciasCargas;
    @SerializedName("EvidenciasLlegadas")
    
    private List<EvidenciasLlegada> evidenciasLlegadas;
    @SerializedName("ChecklistRequeridos")
    
    private List<ChecklistRequerido> checklistRequeridos;

    public ValidacionApp(List<HabilidadesVehiculo> habilidadesVehiculos, List<DocumentosVencimientosVehiculo> documentosVencimientosVehiculos, List<HabilidadesOperadore> habilidadesOperadores, List<DocumentosOperadoresVencimiento> documentosOperadoresVencimientos, List<HabilidadesAuxiliare> habilidadesAuxiliares, List<DocumentosAuxiliaresVencimiento> documentosAuxiliaresVencimientos, List<EvidenciasCarga> evidenciasCargas, List<EvidenciasLlegada> evidenciasLlegadas, List<ChecklistRequerido> checklistRequeridos) {
        super();
        this.habilidadesVehiculos = habilidadesVehiculos;
        this.documentosVencimientosVehiculos = documentosVencimientosVehiculos;
        this.habilidadesOperadores = habilidadesOperadores;
        this.documentosOperadoresVencimientos = documentosOperadoresVencimientos;
        this.habilidadesAuxiliares = habilidadesAuxiliares;
        this.documentosAuxiliaresVencimientos = documentosAuxiliaresVencimientos;
        this.evidenciasCargas = evidenciasCargas;
        this.evidenciasLlegadas = evidenciasLlegadas;
        this.checklistRequeridos = checklistRequeridos;
    }

    public List<HabilidadesVehiculo> getHabilidadesVehiculos() {
        return habilidadesVehiculos;
    }

    public void setHabilidadesVehiculos(List<HabilidadesVehiculo> habilidadesVehiculos) {
        this.habilidadesVehiculos = habilidadesVehiculos;
    }

    public List<DocumentosVencimientosVehiculo> getDocumentosVencimientosVehiculos() {
        return documentosVencimientosVehiculos;
    }

    public void setDocumentosVencimientosVehiculos(List<DocumentosVencimientosVehiculo> documentosVencimientosVehiculos) {
        this.documentosVencimientosVehiculos = documentosVencimientosVehiculos;
    }

    public List<HabilidadesOperadore> getHabilidadesOperadores() {
        return habilidadesOperadores;
    }

    public void setHabilidadesOperadores(List<HabilidadesOperadore> habilidadesOperadores) {
        this.habilidadesOperadores = habilidadesOperadores;
    }

    public List<DocumentosOperadoresVencimiento> getDocumentosOperadoresVencimientos() {
        return documentosOperadoresVencimientos;
    }

    public void setDocumentosOperadoresVencimientos(List<DocumentosOperadoresVencimiento> documentosOperadoresVencimientos) {
        this.documentosOperadoresVencimientos = documentosOperadoresVencimientos;
    }

    public List<HabilidadesAuxiliare> getHabilidadesAuxiliares() {
        return habilidadesAuxiliares;
    }

    public void setHabilidadesAuxiliares(List<HabilidadesAuxiliare> habilidadesAuxiliares) {
        this.habilidadesAuxiliares = habilidadesAuxiliares;
    }

    public List<DocumentosAuxiliaresVencimiento> getDocumentosAuxiliaresVencimientos() {
        return documentosAuxiliaresVencimientos;
    }

    public void setDocumentosAuxiliaresVencimientos(List<DocumentosAuxiliaresVencimiento> documentosAuxiliaresVencimientos) {
        this.documentosAuxiliaresVencimientos = documentosAuxiliaresVencimientos;
    }

    public List<EvidenciasCarga> getEvidenciasCargas() {
        return evidenciasCargas;
    }

    public void setEvidenciasCargas(List<EvidenciasCarga> evidenciasCargas) {
        this.evidenciasCargas = evidenciasCargas;
    }

    public List<EvidenciasLlegada> getEvidenciasLlegadas() {
        return evidenciasLlegadas;
    }

    public void setEvidenciasLlegadas(List<EvidenciasLlegada> evidenciasLlegadas) {
        this.evidenciasLlegadas = evidenciasLlegadas;
    }

    public List<ChecklistRequerido> getChecklistRequeridos() {
        return checklistRequeridos;
    }

    public void setChecklistRequeridos(List<ChecklistRequerido> checklistRequeridos) {
        this.checklistRequeridos = checklistRequeridos;
    }
}
