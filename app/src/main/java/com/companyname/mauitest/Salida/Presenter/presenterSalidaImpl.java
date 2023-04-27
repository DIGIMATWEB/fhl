package com.companyname.mauitest.Salida.Presenter;

import android.content.Context;

import com.companyname.mauitest.Salida.Interactor.interactorSalida;
import com.companyname.mauitest.Salida.Interactor.interactorSalidaImpl;
import com.companyname.mauitest.Salida.Model.Ticket;
import com.companyname.mauitest.Salida.View.salidaViewinterface;

import java.util.List;

public class presenterSalidaImpl implements presenterSalida{
    private Context context;
    private salidaViewinterface view;
    private interactorSalida interactor;
    public presenterSalidaImpl(salidaViewinterface view,Context context)
    {
        this.context=context;
        this.view=view;
        this.interactor= new interactorSalidaImpl(this,context);
    }
    @Override
    public void requestSalida(String code) {
        if (view != null){
            interactor.requestSalida(code);
        }
    }

    @Override
    public void setDataTickets(List<Ticket> data) {
        if (view != null){
            view.setTickets(data);
        }
    }

    @Override
    public void setQRImage(String qr) {
        if (view != null){
            view.setQR(qr);
        }
    }

    @Override
    public void setDireccion(String direccionEntrega) {
        if (view != null){
            view.setDireccion(direccionEntrega);
        }
    }
}