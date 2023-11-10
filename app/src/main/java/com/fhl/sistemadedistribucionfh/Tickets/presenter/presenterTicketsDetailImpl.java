package com.fhl.sistemadedistribucionfh.Tickets.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.Tickets.interactor.ticketsInteractirImpl;
import com.fhl.sistemadedistribucionfh.Tickets.interactor.ticketsInteractor;
import com.fhl.sistemadedistribucionfh.Tickets.view.ticketsView;

public class presenterTicketsDetailImpl implements presenterTicketsDetail{
    private Context context;
    private ticketsView view;
    private ticketsInteractor interactor;


    public presenterTicketsDetailImpl(ticketsView view,Context context){
        this.view=view;
        this.context=context;
        this.interactor= new ticketsInteractirImpl(this,context);
    }

    @Override
    public void requestDetailTickets() {
        if(view!=null){
            interactor.getTicketsDetail();
        }

    }
}