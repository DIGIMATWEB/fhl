package com.fhl.sistemadedistribucionfh.checkList.model.v2;

import com.google.gson.annotations.SerializedName;

public class VehiculoVsCheck {
    @SerializedName("Id")
    private Integer id;
    @SerializedName("Periodicidad")
    private Object periodicidad;
    @SerializedName("FechaInicio")
    private String fechaInicio;
    @SerializedName("ChecklistId")
    private Integer checklistId;
    @SerializedName("Checklist")
    private Checklist checklist;

    public VehiculoVsCheck(Integer id, String periodicidad, String fechaInicio, Integer checklistId, Checklist checklist) {
        super();
        this.id = id;
        this.periodicidad = periodicidad;
        this.fechaInicio = fechaInicio;
        this.checklistId = checklistId;
        this.checklist = checklist;
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

}
