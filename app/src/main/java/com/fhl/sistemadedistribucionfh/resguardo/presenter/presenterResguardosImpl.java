package com.fhl.sistemadedistribucionfh.resguardo.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.resguardo.interactor.resgiuardoInteractorImpl;
import com.fhl.sistemadedistribucionfh.resguardo.interactor.resguardoInteractor;
import com.fhl.sistemadedistribucionfh.resguardo.model.dataResguardo;
import com.fhl.sistemadedistribucionfh.resguardo.view.resguardoView;

import java.util.List;

public class presenterResguardosImpl implements  presenterResguardo{
    private Context context;
    private resguardoView view;
    private resguardoInteractor interactor;

    public presenterResguardosImpl(resguardoView view, Context context){
        this.view=view;
        this.context=context;
        this.interactor=new resgiuardoInteractorImpl(this,context);
    }
    @Override
    public void getResguardos() {
        if(view!=null){
            interactor.requestResguardo();
        }
    }

    @Override
    public void setResguardos(List<dataResguardo> data) {
        if(view!=null){
            view.setResguardos(data);
        }
    }
}
