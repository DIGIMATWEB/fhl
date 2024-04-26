package com.fhl.sistemadedistribucionfh.checkList.model.v2;

import com.fhl.sistemadedistribucionfh.checkList.Questions.model.sendChecklist.SendCheck;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VehiculoVsCheck {
    @SerializedName("Id")
    private Integer id;
    @SerializedName("Periodicidad")
    private Object periodicidad;
    @SerializedName("FechaInicio")
    private String fechaInicio;
    @SerializedName("FechaAplicado")
    private String fechaAplicado;
    @SerializedName("MesAplicado")
    private String mesAplicado;
    @SerializedName("DiaAplicado")
    private String diaAplicado;
    @SerializedName("DespachoId")
    private Integer despachoId;
    @SerializedName("Despacho")
    private DespachoV despacho;
    @SerializedName("ChecklistId")
    private Integer checklistId;
    @SerializedName("Checklist")
    private Checklist checklist=null;
    @SerializedName("AplicadoId")
    private Integer aplicadoId;
    @SerializedName("Aplicado")
    private List<SendCheck> aplicado;

    public VehiculoVsCheck(Integer id, String periodicidad, String fechaInicio, String fechaAplicado, String mesAplicado, String diaAplicado, Integer despachoId, DespachoV despacho, Integer checklistId, Checklist checklist, Integer aplicadoId, List<SendCheck> aplicado) {
        super();
        this.id = id;
        this.periodicidad = periodicidad;
        this.fechaInicio = fechaInicio;
        this.fechaAplicado = fechaAplicado;
        this.mesAplicado = mesAplicado;
        this.diaAplicado = diaAplicado;
        this.despachoId = despachoId;
        this.despacho = despacho;
        this.checklistId = checklistId;
        this.checklist = checklist;
        this.aplicadoId = aplicadoId;
        this.aplicado = aplicado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaAplicado() {
        return fechaAplicado;
    }

    public void setFechaAplicado(String fechaAplicado) {
        this.fechaAplicado = fechaAplicado;
    }

    public String getMesAplicado() {
        return mesAplicado;
    }

    public void setMesAplicado(String mesAplicado) {
        this.mesAplicado = mesAplicado;
    }

    public String getDiaAplicado() {
        return diaAplicado;
    }

    public void setDiaAplicado(String diaAplicado) {
        this.diaAplicado = diaAplicado;
    }

    public Integer getDespachoId() {
        return despachoId;
    }

    public void setDespachoId(Integer despachoId) {
        this.despachoId = despachoId;
    }

    public DespachoV getDespacho() {
        return despacho;
    }

    public void setDespacho(DespachoV despacho) {
        this.despacho = despacho;
    }

    public Integer getChecklistId() {
        return checklistId;
    }

    public void setChecklistId(Integer checklistId) {
        this.checklistId = checklistId;
    }

    public Checklist getChecklist() {
        return checklist;
    }

    public void setChecklist(Checklist checklist) {
        this.checklist = checklist;
    }

    public Integer getAplicadoId() {
        return aplicadoId;
    }

    public void setAplicadoId(Integer aplicadoId) {
        this.aplicadoId = aplicadoId;
    }

    public List<SendCheck> getAplicado() {
        return aplicado;
    }

    public void setAplicado(List<SendCheck> aplicado) {
        this.aplicado = aplicado;
    }
}
