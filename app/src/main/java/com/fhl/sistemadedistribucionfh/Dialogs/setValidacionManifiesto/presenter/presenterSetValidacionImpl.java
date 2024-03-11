package com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.interactor.interactorSetValidacion;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.interactor.interactorSetValidacionImpl;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.view.viewSetValidacion;

public class presenterSetValidacionImpl implements  presenterSetValidacion{
    private Context context;
    private viewSetValidacion view;
    private interactorSetValidacion interactor;
    public  presenterSetValidacionImpl(viewSetValidacion view,Context context){
        this.view=view;
        this.context=context;
        interactor= new interactorSetValidacionImpl(this,context);
    }
    @Override
    public void setValidacionMenifest(String manifest) {

        if(view!=null){
            interactor.setValidacionMenifest(manifest);
        }
    }

    @Override
    public void setresponseValidacionMenifest(String code) {
        if(view!=null){
            view.statusValidacion(code);
        }
    }
}
