package com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.interactor.interactorSetValidacion;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.interactor.interactorSetValidacionImpl;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.view.viewSetValidacion;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.dataTicketsDetailsendtrip;

import java.util.List;

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
    public void getVehicleHabilities(Integer claveVehicleID) {
        if(view!=null){
            interactor.getVehicle(claveVehicleID);
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

    @Override
    public void setDatosValidador(String manifest, String vehicleVin, String rfcUser, String jsonHabDriver, String jsonHabVehicles, String user){
        if(view!=null){
            interactor.setDatosValidador(manifest, vehicleVin, rfcUser, jsonHabDriver, jsonHabVehicles, user);
        }
    }

    @Override
    public void requestTicketsByManifest(String manifest) {
        if(view!=null){
            interactor.requestTicketsByManifest(manifest);
        }
    }

    @Override
    public void setDetailTickets(List<dataTicketsDetailsendtrip> data) {
        if(view!=null){
            view.setDetailTickets(data);
        }
    }

}
