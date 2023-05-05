package com.companyname.mauitest.nmanifest.presenter;

import android.content.Context;

import com.companyname.mauitest.nmanifest.interactor.interactorManifest;
import com.companyname.mauitest.nmanifest.interactor.interactorManifestImpl;
import com.companyname.mauitest.nmanifest.model.dataManifest;
import com.companyname.mauitest.nmanifest.view.viewManifest;

import java.util.List;

public class manifestImpl implements presentermanifest{

    private Context context;
    private viewManifest view;
    private interactorManifest interactor;
    public manifestImpl(viewManifest view,Context context){
        this.view=view;
        this.context=context;
        interactor=new interactorManifestImpl(this,context);
    }
    @Override
    public void getmanifest() {
        if(view!=null){
            interactor.getmymanifest();
        }
    }

    @Override
    public void setmanifest(List<dataManifest> data) {
        if(view!=null){
            view.setAllmanifest(data);
        }
    }
}
