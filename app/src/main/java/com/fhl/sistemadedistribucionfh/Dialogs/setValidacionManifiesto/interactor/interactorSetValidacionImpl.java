package com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.Validadorset;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.requestSetValidacion;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.responseSetValidacion;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.presenter.presenterSetValidacion;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.util.serviceSetValidacionManifest;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientFHManifest;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitValidations;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class interactorSetValidacionImpl implements interactorSetValidacion{
    private presenterSetValidacion presenter;
    private Context context;
    private serviceSetValidacionManifest service;
    private Retrofit retrofitClient;

    public interactorSetValidacionImpl(presenterSetValidacion presenter, Context context) {
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientFHManifest.getRetrofitInstance();
        service = retrofitClient.create(serviceSetValidacionManifest.class);
    }

    @Override
    public void setValidacionMenifest(String manifest) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        if(token!=null){
            requestValidacionManifest(manifest,token);
        }
    }

    private void requestValidacionManifest(String manifest, String token) {
       Validadorset newset=new Validadorset("","Correcto");
       requestSetValidacion request = new requestSetValidacion(manifest,newset);
       Call<responseSetValidacion> call= service.getValidacion(token,request);
       call.enqueue(new Callback<responseSetValidacion>() {
           @Override
           public void onResponse(Call<responseSetValidacion> call, Response<responseSetValidacion> response) {
               validateSetValidacionManifest(response, context);
           }

           @Override
           public void onFailure(Call<responseSetValidacion> call, Throwable t) {
               presenter.setresponseValidacionMenifest("401");

           }
       });

    }

    private void validateSetValidacionManifest(Response<responseSetValidacion> response, Context context) {
        if (response!=null) {
            if(RetrofitValidations.checkSuccessCode(response.code())) {
                setValidacion(response, context);
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
                if(response.code()==401){
                    // presenter.returnTologin();
                    presenter.setresponseValidacionMenifest("401");
                }
            }
        }
    }

    private void setValidacion(Response<responseSetValidacion> response, Context context) {
         responseSetValidacion resp = response.body();
                if(resp!=null) {
                    String message = resp.getMessage();
                    int responseCode = resp.getStatus();
                    if(resp.getStatus() == GeneralConstants.RESPONSE_CODE_OK_FH) {

                        presenter.setresponseValidacionMenifest("105");

                    } else {
                        presenter.setresponseValidacionMenifest("107");
                    }
                } else {

                    presenter.setresponseValidacionMenifest("401");
                }
    }
}
