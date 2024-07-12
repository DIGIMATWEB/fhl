package com.fhl.sistemadedistribucionfh.locator.presenter;

import com.fhl.sistemadedistribucionfh.locator.model.dataVehicleLocation;

import java.util.List;

public interface presenterVehicles {
    void setVehicles(List<dataVehicleLocation> data);

    void getVehicles();
    void showDialog();
    void hideDialog();

    void setVehicleinmanifestV2(String economico);

    void getVehicleinmanifestV2();
}
