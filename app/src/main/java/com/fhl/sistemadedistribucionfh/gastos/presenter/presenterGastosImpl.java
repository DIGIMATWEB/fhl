package com.fhl.sistemadedistribucionfh.gastos.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.gastos.interactor.gastosInteractor;
import com.fhl.sistemadedistribucionfh.gastos.interactor.gastosInteractorImpl;
import com.fhl.sistemadedistribucionfh.gastos.model.gastosV2.dataGastosOperativos;
import com.fhl.sistemadedistribucionfh.gastos.view.gastosView;

import java.util.List;

public class presenterGastosImpl implements presenterGastos{
    private Context context;
    private gastosInteractor interactor;
    private  gastosView view;
    public presenterGastosImpl(gastosView view, Context context){
        this.view=view;
        this.context=context;
        this.interactor=new gastosInteractorImpl(this,context);
    }
    @Override
    public void getGastos() {
        if(view!=null){
            interactor.requestGastos();
        }

    }

    @Override
    public void setGastos(List<dataGastosOperativos> data) {
        if(view!=null){
            view.setDataGastos(data);
        }
    }
}
