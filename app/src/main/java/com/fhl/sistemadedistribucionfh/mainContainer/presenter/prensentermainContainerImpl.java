package com.fhl.sistemadedistribucionfh.mainContainer.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.mainContainer.interactor.interactormainContainer;
import com.fhl.sistemadedistribucionfh.mainContainer.interactor.interactormainContainerImpl;
import com.fhl.sistemadedistribucionfh.mainContainer.mainContainer;
import com.fhl.sistemadedistribucionfh.mainContainer.model.dataMenuItems;

import java.util.List;

public class prensentermainContainerImpl  implements  presentermainContainer{
    private Context context;
    private mainContainer view;
    private interactormainContainer interactor;
    public prensentermainContainerImpl(mainContainer view, Context context) {
        this.view=view;
        this.context=context;
        this.interactor=new interactormainContainerImpl(this,context);
    }

    @Override
    public void requestMenus() {
        if(view!=null){
            interactor.getMenus();
        }
    }

    @Override
    public void setMenus(List<dataMenuItems> data) {
        if(view!=null){
            view.setMenus(data);
        }
    }
}
