package com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.interactor.validadorInteractorV2Impl;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.model.dataValidadorV2;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.view.validadorViewV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;

import java.util.List;

public class presenterValidadorImplements implements presenterValidadorDetail{
    private validadorViewV2 view;
    private Context context;
    private validadorInteractorV2Impl interactor;
    public presenterValidadorImplements (validadorViewV2 view,Context context){
        this.view=view;
        this.context=context;
        this.interactor = new validadorInteractorV2Impl(this,context);


    }
    @Override
    public void requestManifestDetail(int idEmpleado, String currentManifest) {
        if(view!=null){
            interactor.requestManifestDetail(idEmpleado,currentManifest);
        }
    }
    @Override
    public void getTicketByManifest(String currentManifest) {
        if(view!=null){
            interactor.getTicketByManifest(currentManifest);
        }
    }

    @Override
    public void setDetailTickets(List<dataTicketsManifestV2> data) {
        if(view!=null){
            view.setDetailTickets(data);
        }
    }

    @Override
    public void setManifestVehicleandDriver(List<dataValidadorV2> data) {
        if(view!=null) {
            view.setManifestVehicleandDriver(data);
        }
    }

    @Override
    public void error() {
        if(view!=null) {
            view.errorCode();
        }
    }


}
