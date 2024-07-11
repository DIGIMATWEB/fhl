package com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.sellosSalida.sellosSalida;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.interactor.sellosInteractor;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.interactor.sellosInteractorImpl;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.view.sellosSummaryView;
import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;

import java.util.List;

public class presenterSelloImpl implements presenterSello{
    private sellosSalida view;
    private Context context;
    private sellosInteractor interactor;

    public presenterSelloImpl(sellosSalida view, Context context){
        this.view=view;
        this.context=context;
        interactor= new sellosInteractorImpl(this,context);
    }

    @Override
    public void requestManifestdetail(String currentManifest) {
        if(view!=null){
            interactor.requestManifestdetail(currentManifest);
        }
    }

    @Override
    public void reqSellos(String currentManifest) {
        if(view!=null){
            interactor.reqSellos(currentManifest);
        }
    }

    @Override
    public void setSello(Integer manifestId, List<Sello> sellos) {
        if(view!=null){
            interactor.setSellos(manifestId,sellos);
        }
    }

    @Override
    public void getMessageSello() {
        if(view!=null){
            view.setMessageSello();
        }
    }

    @Override
    public void saveManifestId(Integer id) {
        if(view!=null){
            view.saveManifestId(id);
        }
    }

    @Override
    public void setSellos(List<Sello> sellos) {
        if(view!=null){
            view.seteginsellos(sellos);
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
            view.hideDialog();
        }
    }
}
