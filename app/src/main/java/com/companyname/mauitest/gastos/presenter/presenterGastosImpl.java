package com.companyname.mauitest.gastos.presenter;

import android.content.Context;

import com.companyname.mauitest.gastos.interactor.gastosInteractor;
import com.companyname.mauitest.gastos.interactor.gastosInteractorImpl;
import com.companyname.mauitest.gastos.model.dataGastos;
import com.companyname.mauitest.gastos.view.gastosView;

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
    public void setGastos(List<dataGastos> data) {
        if(view!=null){
            view.setDataGastos(data);
        }
    }
}
