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
    public void getdriverHabilities() {
        if(view!=null){
            interactor.getDrirver();
        }
    }

    @Override
    public void getVehicleHabilities() {
        if(view!=null){
            interactor.getVehicle();
        }
    }

    @Override
    public void setDriverHailities(String habilidades) {
        if(view!=null){
            view.setDriverHailities(habilidades);
        }
    }

    @Override
    public void setDriverVehicle(String habilidadVehiculos) {
        if(view!=null){
            view.setVehicleHailities(habilidadVehiculos);
        }

    }

    @Override
    public void setresponseValidacionMenifest(String code) {
        if(view!=null){
            view.statusValidacion(code);
        }
    }


}
