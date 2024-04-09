
package com.fhl.sistemadedistribucionfh.gastos.model.gastosV2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dispersion {

    @SerializedName("Moneda")
    @Expose
    private Moneda moneda;
    @SerializedName("MonedaIdMonto")
    @Expose
    private Integer monedaIdMonto;
    @SerializedName("Monto")
    @Expose
    private Integer monto;
    @SerializedName("Id")
    @Expose
    private Integer id;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Dispersion() {
    }

    /**
     * 
     * @param monto
     * @param moneda
     * @param id
     * @param monedaIdMonto
     */
    public Dispersion(Moneda moneda, Integer monedaIdMonto, Integer monto, Integer id) {
        super();
        this.moneda = moneda;
        this.monedaIdMonto = monedaIdMonto;
        this.monto = monto;
        this.id = id;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public Integer getMonedaIdMonto() {
        return monedaIdMonto;
    }

    public void setMonedaIdMonto(Integer monedaIdMonto) {
        this.monedaIdMonto = monedaIdMonto;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
