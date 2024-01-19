package com.fhl.sistemadedistribucionfh.mainContainerV2.presenterV2;

import com.fhl.sistemadedistribucionfh.mainContainerV2.modelV2.dataMenuItemsV2;

import java.util.List;

public interface presentermainContainerV2 {
    void requestMenusV2();

    //Aqui va el metodo para pedir el menu
    void setMenusV2(List<dataMenuItemsV2> data);

}
