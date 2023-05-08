package com.fhl.sistemadedistribucionfh.resguardo.presenter;

import com.fhl.sistemadedistribucionfh.resguardo.model.dataResguardo;

import java.util.List;

public interface presenterResguardo {
    void getResguardos();
    void setResguardos(List<dataResguardo> data);
}
