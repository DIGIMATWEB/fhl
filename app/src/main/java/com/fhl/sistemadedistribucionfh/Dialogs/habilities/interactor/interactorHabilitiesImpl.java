package com.fhl.sistemadedistribucionfh.Dialogs.habilities.interactor;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.Dialogs.habilities.presenter.presenterHabilidades;
import com.fhl.sistemadedistribucionfh.Dialogs.habilities.util.serviceHabilities;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrifitClientSGD;
import com.fhl.sistemadedistribucionfh.locator.util.serviceLocationVehicle;

import retrofit2.Retrofit;

public class interactorHabilitiesImpl implements  interactorHabilities {
    private Context context;
    private presenterHabilidades presenter;
    private Retrofit retrofit;
    private serviceHabilities service;

    public interactorHabilitiesImpl(presenterHabilidades presenter,Context context){
    this.presenter=presenter;
    this.context=context;
        retrofit= RetrifitClientSGD.getRetrofitInstance();
        service= retrofit.create(serviceHabilities.class);
    }
    @Override
    public void getVehicle() {

    }
    @Override
    public void getDrirver() {

    }


}
