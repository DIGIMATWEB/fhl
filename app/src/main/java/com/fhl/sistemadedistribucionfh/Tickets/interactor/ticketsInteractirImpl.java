package com.fhl.sistemadedistribucionfh.Tickets.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClienFH;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientPep;
import com.fhl.sistemadedistribucionfh.Tickets.model.ResoponseTicketsDetail;
import com.fhl.sistemadedistribucionfh.Tickets.presenter.presenterTicketsDetail;
import com.fhl.sistemadedistribucionfh.Tickets.presenter.presenterTicketsDetailImpl;
import com.fhl.sistemadedistribucionfh.Tickets.util.ticketsService;
import com.fhl.sistemadedistribucionfh.login.model.modelProfile.profileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ticketsInteractirImpl implements ticketsInteractor{
    private presenterTicketsDetail presenter;
    private Context context;
    private ticketsService service;
    private Retrofit retrofitClient;
    public ticketsInteractirImpl(presenterTicketsDetail presenter, Context context) {
        this.context=context;
        this.presenter=presenter;
        retrofitClient = RetrofitClientPep.getRetrofitInstance();
        service = retrofitClient.create(ticketsService.class);
    }

    @Override
    public void getTicketsDetail() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        if(token!=null){
            requestDetailTicket(token);
        }

    }

    private void requestDetailTicket(String token) {
        Call<ResoponseTicketsDetail> call=service.getTicketsByManifiesto(token,"10871872","00000002");
        call.enqueue(new Callback<ResoponseTicketsDetail>() {
            @Override
            public void onResponse(Call<ResoponseTicketsDetail> call, Response<ResoponseTicketsDetail> response) {
                if(response.code()==200) {
                  Log.e("requestDetailTicket",""+response.body());
                  presenter.setTikets(response.body().getData());
                }else {
                    Log.e("requestDetailTicket",""+response.code()+" "+response.message());
                }
            }

            @Override
            public void onFailure(Call<ResoponseTicketsDetail> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
