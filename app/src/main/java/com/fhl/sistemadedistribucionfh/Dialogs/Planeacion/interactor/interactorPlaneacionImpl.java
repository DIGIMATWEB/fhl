package com.fhl.sistemadedistribucionfh.Dialogs.Planeacion.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Dialogs.Planeacion.model.responseGetPlaneacion;
import com.fhl.sistemadedistribucionfh.Dialogs.Planeacion.presenter.presenterPlaneacionImpl;
import com.fhl.sistemadedistribucionfh.Dialogs.Planeacion.util.utilPlaneacion;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.util.serviceDialogReasons;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrifitClientSGD;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientFHManifest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class interactorPlaneacionImpl implements  interactorPlaneacion{
    private presenterPlaneacionImpl presenter;
    private Context context;
    private Retrofit retrofit;
    private utilPlaneacion service;
    public interactorPlaneacionImpl(presenterPlaneacionImpl presenter, Context context) {
        this.presenter=presenter;
        this.context=context;
        retrofit= RetrofitClientFHManifest.getRetrofitInstance();
        service= retrofit.create(utilPlaneacion.class);
    }

    @Override
    public void getTicketData(String folio) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        Call<responseGetPlaneacion> call= service.getTicketData(token,folio);
        call.enqueue(new Callback<responseGetPlaneacion>() {
            @Override
            public void onResponse(Call<responseGetPlaneacion> call, Response<responseGetPlaneacion> response) {
                if(response.code()==200) {
                    if (response.body().getStatus() == 200) {
                        if(response.body().getData()!=null){
                            presenter.setTicketValue(response.body().getData().get(0).getEmpaque());
                        }else{
                            presenter.failResponse();
                        }
                    }else{
                        Toast.makeText(context, ""+response.message()+":"+response.code(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, ""+response.message()+":"+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<responseGetPlaneacion> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setStatusTicket(String folio, Integer i) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        Call<responseGetPlaneacion> call= service.setTicketStatus(token,folio,i);
        call.enqueue(new Callback<responseGetPlaneacion>() {
            @Override
            public void onResponse(Call<responseGetPlaneacion> call, Response<responseGetPlaneacion> response) {
                if(response.code()==200) {
                    if (response.body().getStatus() == 200) {
//                        if(response.body().getData()!=null){
//
//                        }else{
//
//                        }
                        Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, ""+response.message()+":"+response.code(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, ""+response.message()+":"+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<responseGetPlaneacion> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
