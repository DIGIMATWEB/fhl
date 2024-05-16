package com.fhl.sistemadedistribucionfh.Dialogs.habilities.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.Dialogs.habilities.view.dialogCompleteValidadorView;

public class presenterHabilidadesImpl implements presenterHabilidades{
    private Context context;
    private dialogCompleteValidadorView view;

    public presenterHabilidadesImpl(dialogCompleteValidadorView view,Context context){
        this.view=view;
        this.context=context;
    }

    @Override
    public void getdriverHabilities() {
        if(view!=null){

        }
    }

    @Override
    public void getVehicleHabilities() {
        if(view!=null){

        }
    }
}
