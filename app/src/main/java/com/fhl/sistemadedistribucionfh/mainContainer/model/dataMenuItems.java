package com.fhl.sistemadedistribucionfh.mainContainer.model;

import com.google.gson.annotations.SerializedName;

public class dataMenuItems {

    @SerializedName("cveObject")
    private String cveObject;
    @SerializedName("menuName")
    private String menuName;

    public dataMenuItems(String cveObject, String menuName) {
        super();
        this.cveObject = cveObject;
        this.menuName = menuName;
    }

    public String getCveObject() {
        return cveObject;
    }

    public void setCveObject(String cveObject) {
        this.cveObject = cveObject;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
