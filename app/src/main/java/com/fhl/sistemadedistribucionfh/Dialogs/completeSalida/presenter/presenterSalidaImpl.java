package com.fhl.sistemadedistribucionfh.Dialogs.completeSalida.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.Dialogs.completeSalida.dialogCompletedSalida;
import com.fhl.sistemadedistribucionfh.Dialogs.completeSalida.interactor.interactorSalida;
import com.fhl.sistemadedistribucionfh.Dialogs.completeSalida.interactor.interactorSalidaImpl;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.dataTicketsDetailsendtrip;

import java.util.List;

public class presenterSalidaImpl implements presenterSalida{
    private dialogCompletedSalida view;
    private Context context;
    private interactorSalida interactor;
    public presenterSalidaImpl(dialogCompletedSalida view, Context context){
        this.view=view;
        this.context= context;
        interactor= new interactorSalidaImpl(this,context);
    }

    @Override
    public void requestDetailTicketsSendtriplus(boolean isArray, Integer iterateidTickets, String currentManifest, String folioTicket, String ticket) {
        if(view!=null){
            interactor. requestDetailTicketsSendtriplus( isArray, iterateidTickets,currentManifest,folioTicket,ticket);
        }
    }

    @Override
    public void sendSentriplus(String currentManifest, List<dataTicketsDetailsendtrip> dataTicketSendtrip, String sentripPlusFlow) {
        if(view!=null){
            interactor.sendSentriplus(currentManifest,dataTicketSendtrip,sentripPlusFlow);
        }
    }

    @Override
    public void changeStatusManifestTicket(String currentManifest, String changeStatusTicket, String sentripPlusFlow) {
        if(view!=null){
            interactor.changeStatusManifestTicket(currentManifest,changeStatusTicket,sentripPlusFlow);
        }
    }

    @Override
    public void requestTokenAvocado() {
        if(view!=null){
            interactor.tokenAvocado();
        }
    }

    @Override
    public void nextRequest() {
        if(view!=null){
            view.nextRequest();
        }
    }

    @Override
    public void validateSendtrip() {
        if(view!=null){
            view.startSendtriplus();
        }
    }
}
