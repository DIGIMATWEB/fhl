
package com.fhl.sistemadedistribucionfh.gastos.model.gastosV2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GastosOperativo {

    @SerializedName("TipoGastoId")
    @Expose
    private Integer tipoGastoId;
    @SerializedName("TipoGasto")
    @Expose
    private TipoGasto tipoGasto;
    @SerializedName("Dispersion")
    @Expose
    private Dispersion dispersion;
    @SerializedName("Liquidacion")
    @Expose
    private Liquidacion liquidacion;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GastosOperativo() {
    }

    /**
     * 
     * @param tipoGastoId
     * @param tipoGasto
     * @param liquidacion
     * @param dispersion
     */
    public GastosOperativo(Integer tipoGastoId, TipoGasto tipoGasto, Dispersion dispersion, Liquidacion liquidacion) {
        super();
        this.tipoGastoId = tipoGastoId;
        this.tipoGasto = tipoGasto;
        this.dispersion = dispersion;
        this.liquidacion = liquidacion;
    }

    public Integer getTipoGastoId() {
        return tipoGastoId;
    }

    public void setTipoGastoId(Integer tipoGastoId) {
        this.tipoGastoId = tipoGastoId;
    }

    public TipoGasto getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(TipoGasto tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public Dispersion getDispersion() {
        return dispersion;
    }

    public void setDispersion(Dispersion dispersion) {
        this.dispersion = dispersion;
    }

    public Liquidacion getLiquidacion() {
        return liquidacion;
    }

    public void setLiquidacion(Liquidacion liquidacion) {
        this.liquidacion = liquidacion;
    }

}
