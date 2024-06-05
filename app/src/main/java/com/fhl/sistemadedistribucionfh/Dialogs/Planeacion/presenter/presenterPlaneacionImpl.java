package com.fhl.sistemadedistribucionfh.Dialogs.Planeacion.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.Dialogs.Planeacion.interactor.interactorPlaneacion;
import com.fhl.sistemadedistribucionfh.Dialogs.Planeacion.interactor.interactorPlaneacionImpl;
import com.fhl.sistemadedistribucionfh.Dialogs.Planeacion.view.validadorPlaneacionView;

public class presenterPlaneacionImpl implements presenterPlaneacion{
    private Context context;
    private validadorPlaneacionView view;
    private interactorPlaneacion interactor;

    public presenterPlaneacionImpl(validadorPlaneacionView view,Context context){
        this.view=view;
        this.context=context;
        interactor= new interactorPlaneacionImpl(this,context);
    }

    @Override
    public void getTicketData(String folio) {
        if(view!=null){
            interactor.getTicketData(folio);
        }
    }
    @Override
    public void setStatusTicket(String folio, Integer i) {
        if(view!=null){
            interactor.setStatusTicket(folio,i);
        }
    }
    @Override
    public void setTicketValue(String answer,Integer value) {
        if(view!=null){
            view.setValidadorResponse(answer,value);
        }
    }

    @Override
    public void failResponse() {
        if(view!=null){
            view.failResponse();
        }
    }


}
