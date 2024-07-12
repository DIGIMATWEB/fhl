package com.fhl.sistemadedistribucionfh.locator.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.locator.interactor.interactorVehicles;
import com.fhl.sistemadedistribucionfh.locator.interactor.interactorVehiclesImpl;
import com.fhl.sistemadedistribucionfh.locator.model.dataVehicleLocation;
import com.fhl.sistemadedistribucionfh.locator.view.locatorView;

import java.util.List;

public class presenterVehiclesImpl implements presenterVehicles{
    private locatorView view;
    private Context context;
    private interactorVehicles interactor;

    public  presenterVehiclesImpl ( locatorView view,Context context){
        this.view=view;
        this.context=context;
        interactor= new interactorVehiclesImpl(this,context);
    }
    @Override
    public void getVehicles() {
        if(view!=null){
            interactor.requestLocation();
        }
    }

    @Override
    public void getVehicleinmanifestV2() {
        if(view!=null){
            interactor.requestVehicles();
        }
    }
    @Override
    public void setVehicles(List<dataVehicleLocation> data) {
        if(view!=null){
            view.setVehicles(data);
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

    @Override
    public void setVehicleinmanifestV2(String economico) {
        if(view!=null){
            view.setVehicleinmanifestV2(economico);
        }
    }


}
