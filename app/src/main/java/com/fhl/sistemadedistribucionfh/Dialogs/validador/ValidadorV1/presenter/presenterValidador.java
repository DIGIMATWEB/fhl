package com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV1.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV1.interactor.interactorValidadorImpl;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV1.model.dataValidador;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV1.view.validadorView;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV1.interactor.interactorValidador;

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
