package com.fhl.sistemadedistribucionfh.locator.presenter;

import com.fhl.sistemadedistribucionfh.locator.model.dataVehicleLocation;

import java.util.List;

public interface presenterVehicles {
    void setVehicles(List<dataVehicleLocation> data);

    void getVehicles();
}
