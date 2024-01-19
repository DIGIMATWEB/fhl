package com.fhl.sistemadedistribucionfh.mainContainerV2.presenterV2;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.mainContainer.mainContainer;
import com.fhl.sistemadedistribucionfh.mainContainerV2.interactorV2.interactormainContainerImplV2;
import com.fhl.sistemadedistribucionfh.mainContainerV2.interactorV2.interactormainContainerV2;
import com.fhl.sistemadedistribucionfh.mainContainerV2.modelV2.dataMenuItemsV2;

import java.util.List;

public class presentermainContainerImplV2 implements presentermainContainerV2 {
    private Context context;
    private mainContainer view;
    private interactormainContainerV2 interactor;

    public presentermainContainerImplV2(mainContainer view, Context context) {
        this.view = view;
        this.context = context;
        this.interactor = new interactormainContainerImplV2(this, context);
    }

    @Override
    public void requestMenusV2() {
        if(view!=null) {
            interactor.getMenusV2();
        }
    }

    @Override
    public void setMenusV2(List<dataMenuItemsV2> data) {
        if(view!=null) {
            view.setMenusV2(data);
        }
    }
}
