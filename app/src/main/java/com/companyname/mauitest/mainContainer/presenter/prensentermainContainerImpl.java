package com.companyname.mauitest.mainContainer.presenter;

import android.content.Context;

import com.companyname.mauitest.mainContainer.interactor.interactormainContainer;
import com.companyname.mauitest.mainContainer.interactor.interactormainContainerImpl;
import com.companyname.mauitest.mainContainer.mainContainer;
import com.companyname.mauitest.mainContainer.model.dataMenuItems;

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
