package com.fhl.sistemadedistribucionfh.nmanifestDetail.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.interactor.interactorTicketsManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.interactor.interactorTicketsManifestV2Impl;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.view.viewManifestDetailsV2;

import java.util.List;

public class presenterTicketsManifestImplV2 implements presenterTicketsmanifestV2 {
    private viewManifestDetailsV2 view;
    private Context context;
    private interactorTicketsManifestV2 interactor;

    public presenterTicketsManifestImplV2(viewManifestDetailsV2 view, Context context){
        this.view=view;
        this.context=context;
        this.interactor=new interactorTicketsManifestV2Impl(this,context);
    }
    @Override
    public void setTickets(List<dataTicketsManifestV2> data) {
        if(view!=null){
            view.setTickets(data);
        }
    }

    @Override
    public void getTickets(String folioDespachoId) {
        if(view!=null){
            interactor.reqTickets(folioDespachoId);
        }
    }

    @Override
    public void getSellos(String folioDespachoId) {
        if(view!=null){
            interactor.reqSellos(folioDespachoId);
        }
    }

    @Override
    public void setSellos(List<Sello> response) {
        if(view!=null){
            view.setSellos(response);
        }
    }

    @Override
    public void showDialog() {
        if(view!=null){
            view.showDialog();
        }
    }

    @Override
    public void hideDialog() {
        if(view!=null){
            if(view!=null){
                view.hideDialog();
            }
        }
    }


}
