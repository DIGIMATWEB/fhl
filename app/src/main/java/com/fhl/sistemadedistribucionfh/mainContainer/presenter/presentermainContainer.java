package com.fhl.sistemadedistribucionfh.mainContainer.presenter;

import com.fhl.sistemadedistribucionfh.mainContainer.model.dataMenuItems;

import java.util.List;

public interface presentermainContainer {
    void requestMenus();
    void setMenus(List<dataMenuItems> data);
}
