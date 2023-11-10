package com.fhl.sistemadedistribucionfh.nmanifestDetail.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.nmanifestDetail.interactor.interactorTicketsManifest;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.interactor.interactorTicketsManifestImpl;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.model.dataTicketsManifest;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.view.viewManifestDetails;

import java.util.List;

public class presenterTicketsManifestImpl implements presenterTicketsmanifest{
    private viewManifestDetails view;
    private Context context;
    private interactorTicketsManifest interactor;

    public  presenterTicketsManifestImpl(viewManifestDetails view,Context context){
        this.view=view;
        this.context=context;
        this.interactor=new interactorTicketsManifestImpl(this,context);
    }
    @Override
    public void setTickets(List<dataTicketsManifest> data) {
        if(view!=null){
            view.setTickets(data);
        }
    }

    @Override
    public void getTickets(String idmanifest) {
        if(view!=null){
            interactor.reqTickets(idmanifest);
        }
    }


}
