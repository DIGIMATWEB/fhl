
package com.fhl.sistemadedistribucionfh.gastos.model.gastosV2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Liquidacion {

    @SerializedName("Moneda")
    @Expose
    private Moneda__1 moneda;
    @SerializedName("MonedaIdMonto")
    @Expose
    private Integer monedaIdMonto;
    @SerializedName("Monto")
    @Expose
    private Integer monto;
    @SerializedName("Id")
    @Expose
    private Integer id;

    public Liquidacion(Moneda__1 moneda, Integer monedaIdMonto, Integer monto, Integer id) {
        super();
        this.moneda = moneda;
        this.monedaIdMonto = monedaIdMonto;
        this.monto = monto;
        this.id = id;
    }

    public Moneda__1 getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda__1 moneda) {
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
