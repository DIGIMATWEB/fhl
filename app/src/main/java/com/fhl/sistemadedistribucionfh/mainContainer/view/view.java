package com.fhl.sistemadedistribucionfh.mainContainer.view;

import com.fhl.sistemadedistribucionfh.mainContainer.model.dataMenuItems;
import com.fhl.sistemadedistribucionfh.mainContainerV2.modelV2.dataMenuItemsV2;

import java.util.List;

public interface view {
    void setMenus(List<dataMenuItems> data);

    void setMenusV2(List<dataMenuItemsV2> data);
}
