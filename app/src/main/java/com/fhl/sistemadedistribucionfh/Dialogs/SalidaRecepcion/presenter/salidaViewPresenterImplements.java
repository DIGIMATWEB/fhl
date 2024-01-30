package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.interactor.salidaInteractorImplementation;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.interactor.salidainteractor;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.cortina.dataCortina;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.responseManifestSalidaV2data;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.view.salidaView;

import java.util.List;

public class salidaViewPresenterImplements implements salidaViewPresenter{

    private salidaView view;
    private Context context;
    private salidainteractor interactor;

    public salidaViewPresenterImplements(salidaView view,Context context){
        this.view=view;
        this.context=context;
        this.interactor= new salidaInteractorImplementation(this,context);
    }
    @Override
    public void requestManifest(String codigoValidador) {
        if (view!=null){
            interactor.detailManifest(codigoValidador);
        }
    }

    @Override
    public void requestCortinas(String codigoValidador) {
        if (view!=null){
            interactor.detailCortina(codigoValidador);
        }
    }

    @Override
    public void requestTickets() {
        if (view!=null){

        }
    }
    @Override
    public void hideProgress() {
        if (view!=null){
                view.hideProgress();
        }
    }

    @Override
    public void showProgress() {
        if (view!=null){
            view.showProgress();
        }
    }

    @Override
    public void setCortina(dataCortina data) {
        if (view!=null){
            view.setdataCortina(data);
        }
    }

    @Override
    public void goTickets() {
        if (view!=null){
            view.goticketsifNull();
        }
    }

    @Override
    public void setmanifest(List<responseManifestSalidaV2data> data) {
        if (view!=null){
            view.setManifestCard(data);
        }
    }


}
