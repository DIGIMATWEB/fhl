package com.fhl.sistemadedistribucionfh.Dialogs.habilities.model.driver;

public class habiltiesDriver {
    private String habilidad;
    private Boolean isSelected;
    public habiltiesDriver(String habilidad,Boolean isSelected){
        this.habilidad=habilidad;
        this.isSelected=isSelected;
    }
    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
