package com.fhl.sistemadedistribucionfh.cerrarViaje.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.cerrarViaje.interactor.cancelInteractor;
import com.fhl.sistemadedistribucionfh.cerrarViaje.interactor.cancelInteractorImpl;
import com.fhl.sistemadedistribucionfh.cerrarViaje.view.cancelView;

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
    public void sendEvidence(List<String> directories) {
        if(view!=null){
            interactor.sendEvidences(directories);
        }

    }

    @Override
    public void okSendEvidence() {
        if(view!=null){
            view.okSendEvidence();
        }
    }
}
