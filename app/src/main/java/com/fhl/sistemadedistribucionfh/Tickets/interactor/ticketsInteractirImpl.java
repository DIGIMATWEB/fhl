package com.fhl.sistemadedistribucionfh.Tickets.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientFHManifest;
import com.fhl.sistemadedistribucionfh.Tickets.model.ticketsdetail.ResoponseTicketsDetail;
import com.fhl.sistemadedistribucionfh.Tickets.presenter.presenterTicketsDetail;
import com.fhl.sistemadedistribucionfh.Tickets.util.ticketsService;

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
        retrofitClient = RetrofitClientFHManifest.getRetrofitInstance();
        service = retrofitClient.create(ticketsService.class);
    }

    @Override
    public void getTicketsDetail(String folioDespachoId, String folioTicket) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        if(token!=null){
            requestDetailTicket(token,folioDespachoId,folioTicket);
        }

    }

    private void requestDetailTicket(String token, String folioDespachoId, String folioTicket) {
        Call<ResoponseTicketsDetail> call=service.getTicketsByManifiesto(token,folioDespachoId,folioTicket);//todo cambiar el manifiesto y la pila de tickets ppor el valor real
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
