package com.fhl.sistemadedistribucionfh.locator.view;

import com.fhl.sistemadedistribucionfh.locator.model.dataVehicleLocation;

import java.util.List;

public interface locatorView {
    void setVehicles(List<dataVehicleLocation> data);
    void showDialog();
    void hideDialog();

    void setVehicleinmanifestV2(String economico);
}
