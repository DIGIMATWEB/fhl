package com.companyname.mauitest.mainContainer.presenter;

import com.companyname.mauitest.mainContainer.model.dataMenuItems;

import java.util.List;

public interface presentermainContainer {
    void requestMenus();
    void setMenus(List<dataMenuItems> data);
}
