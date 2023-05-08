package com.fhl.sistemadedistribucionfh.nmanifest.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.nmanifest.interactor.interactorManifest;
import com.fhl.sistemadedistribucionfh.nmanifest.interactor.interactorManifestImpl;
import com.fhl.sistemadedistribucionfh.nmanifest.model.dataManifest;
import com.fhl.sistemadedistribucionfh.nmanifest.view.viewManifest;

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
