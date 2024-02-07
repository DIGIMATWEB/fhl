package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.sellosSalida.model;

public class sellosScanned {
    private String folio;
    private Boolean flag;
    public sellosScanned(String folio,Boolean flag){
        this.folio=folio;
        this.flag=flag;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }
}
