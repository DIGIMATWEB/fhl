package com.fhl.sistemadedistribucionfh.Cancelar.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.Cancelar.interactor.cancelInteractor;
import com.fhl.sistemadedistribucionfh.Cancelar.interactor.cancelInteractorImpl;
import com.fhl.sistemadedistribucionfh.Cancelar.view.cancelView;

import java.util.List;

public class cancelPresenterImpl  implements cancelPresenter{
    private Context context;
    private cancelView view;
    private cancelInteractor interactor;
    public cancelPresenterImpl(cancelView view,Context context){
        this.view=view;
        this.context=context;
        interactor=new cancelInteractorImpl(this,context);
    }
    @Override
    public void sendEvidence(List<String> directories, String folioTicket) {
        if(view!=null){
            interactor.sendEvidences(directories,folioTicket);
        }

    }
    @Override
    public void changemStatusManifestTicket(String currentManifest, String folioTicket) {
        if (view != null) {
            interactor.changemStatusManifestTicket(currentManifest,folioTicket);
        }
    }

    @Override
    public void okChangeStatus() {
        if(view!=null){
            view.okChangeStatus();
        }
    }

    @Override
    public void okSendEvidence() {
        if(view!=null){
            view.okSendEvidence();
        }
    }


}
