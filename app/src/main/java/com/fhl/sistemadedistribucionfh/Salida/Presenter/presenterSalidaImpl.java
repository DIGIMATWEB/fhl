package com.fhl.sistemadedistribucionfh.Salida.Presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.Salida.Interactor.interactorSalida;
import com.fhl.sistemadedistribucionfh.Salida.Interactor.interactorSalidaImpl;
import com.fhl.sistemadedistribucionfh.Salida.Model.test.Ticket;
import com.fhl.sistemadedistribucionfh.Salida.Model.v2.ResponseSalida;
import com.fhl.sistemadedistribucionfh.Salida.View.salidaViewinterface;

import java.util.List;

import retrofit2.Response;

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

    @Override
    public void setSalida(Response<ResponseSalida> response) {
        if (view != null){
            view.setSalida(response);
        }
    }
}
