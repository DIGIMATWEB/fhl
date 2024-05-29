package com.fhl.sistemadedistribucionfh.nmanifest.presenterV2;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.nmanifest.interactorV2.interactorManifestImplV2;
import com.fhl.sistemadedistribucionfh.nmanifest.interactorV2.interactorManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.dataManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifest.viewV2.viewManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;

import java.util.List;

public class manifestImplV2 implements presentermanifestV2 {
    private Context context;
    private viewManifestV2 view;
    private interactorManifestV2 interactor;

    public manifestImplV2(viewManifestV2 view, Context context) {
        this.view = view;
        this.context = context;
        interactor = new interactorManifestImplV2(this, context);
    }

    @Override
    public void getmanifestV2() {
        if(view!=null) {
            interactor.getmymanifestV2();
        }
    }
    @Override
    public void getTicketByManigest(String idmanifest) {
        if(view!=null) {
            interactor.getTicketByManigest(idmanifest);
        }
    }

    @Override
    public void setTickets(List<dataTicketsManifestV2> data) {
        if(view!=null) {
            view.checkTickets(data);
        }
    }

    @Override
    public void setmanifestV2(List<dataManifestV2> data) {
        if(view!=null) {
            view.setAllmanifestV2(data);
        }
    }

    @Override
    public void returnTologin() {
        if(view!=null) {
            view.returnTologin();
        }
    }

    @Override
    public void showProgress() {
        if(view!=null) {
            view.showProgress();
        }
    }

    @Override
    public void hideProgress() {
        if(view!=null) {
            view.hideProgress();
        }
    }
}
