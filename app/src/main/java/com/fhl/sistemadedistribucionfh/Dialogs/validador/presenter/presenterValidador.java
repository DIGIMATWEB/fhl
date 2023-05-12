package com.fhl.sistemadedistribucionfh.Dialogs.validador.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.Dialogs.validador.interactor.interactorValidador;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.interactor.interactorValidadorImpl;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.model.dataValidador;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.view.validadorView;

import java.util.List;

public class presenterValidador implements presenterValidadorInterface{
    private Context context;
    private validadorView view;
    private interactorValidador interactor;
    public  presenterValidador(validadorView view,Context context){
        this.view=view;
        this.context=context;
        this.interactor= new interactorValidadorImpl(this,context);
    }
    @Override
    public void requestDespacho(String codigo) {
        if(view!=null){
            interactor.requestmDespachos(codigo);
        }
    }

    @Override
    public void setDespachotoView(List<dataValidador> data) {
        if(view!=null){
            view.setCode(data);
        }
    }

    @Override
    public void errorValidador() {
        if(view!=null){
            view.errorValidador();
        }
    }
}
