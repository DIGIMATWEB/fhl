package com.companyname.mauitest.gastos.presenter;

import com.companyname.mauitest.gastos.model.dataGastos;

import java.util.List;

public interface presenterGastos {
    void getGastos();
    void setGastos(List<dataGastos> data);
}
