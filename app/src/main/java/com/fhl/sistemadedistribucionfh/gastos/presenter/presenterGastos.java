package com.fhl.sistemadedistribucionfh.gastos.presenter;

import com.fhl.sistemadedistribucionfh.gastos.model.dataGastos;

import java.util.List;

public interface presenterGastos {
    void getGastos();
    void setGastos(List<dataGastos> data);
}
