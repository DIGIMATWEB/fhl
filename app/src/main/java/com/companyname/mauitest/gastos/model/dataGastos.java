package com.companyname.mauitest.gastos.model;

import com.google.gson.annotations.SerializedName;

public class dataGastos {

    @SerializedName("manifestGastos")
    private String manifestGastos;
    @SerializedName("operadorGastos")
    private String operadorGastos;
    @SerializedName("statusGastos")
    private String statusGastos;

    public dataGastos(String manifestGastos, String operadorGastos, String statusGastos) {
        super();
        this.manifestGastos = manifestGastos;
        this.operadorGastos = operadorGastos;
        this.statusGastos = statusGastos;
    }

    public String getManifestGastos() {
        return manifestGastos;
    }

    public void setManifestGastos(String manifestGastos) {
        this.manifestGastos = manifestGastos;
    }

    public String getOperadorGastos() {
        return operadorGastos;
    }

    public void setOperadorGastos(String operadorGastos) {
        this.operadorGastos = operadorGastos;
    }

    public String getStatusGastos() {
        return statusGastos;
    }

    public void setStatusGastos(String statusGastos) {
        this.statusGastos = statusGastos;
    }

}
