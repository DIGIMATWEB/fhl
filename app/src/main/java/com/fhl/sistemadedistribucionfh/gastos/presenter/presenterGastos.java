package com.fhl.sistemadedistribucionfh.gastos.presenter;

import com.fhl.sistemadedistribucionfh.gastos.model.gastosV2.dataGastosOperativos;

import java.util.List;

public interface presenterGastos {
    void getGastos();
    void setGastos(List<dataGastosOperativos> data);
}
