package com.fhl.sistemadedistribucionfh.gastos.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClient;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientFHManifest;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitValidations;
import com.fhl.sistemadedistribucionfh.gastos.model.dataGastos;
import com.fhl.sistemadedistribucionfh.gastos.model.gastosV2.ResponseGastosv2;
import com.fhl.sistemadedistribucionfh.gastos.model.gastosV2.dataGastosOperativos;
import com.fhl.sistemadedistribucionfh.gastos.model.requestGastos;
import com.fhl.sistemadedistribucionfh.gastos.model.responseGastos;
import com.fhl.sistemadedistribucionfh.gastos.presenter.presenterGastos;
import com.fhl.sistemadedistribucionfh.gastos.util.serviceGastos;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class gastosInteractorImpl implements gastosInteractor{
    private Context context;
    private presenterGastos presenter;
    private serviceGastos service;
    private Retrofit retrofitClient;

    public gastosInteractorImpl(presenterGastos presenter,Context context){
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientFHManifest.getRetrofitInstance();
        service=retrofitClient.create(serviceGastos.class);
    }
    @Override
    public void requestGastos() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String user = preferences.getString(GeneralConstants.OPERADOR_ID, null);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        Call<ResponseGastosv2> call = service.getgastos(token,  user,true);
        call.enqueue(new Callback<ResponseGastosv2>() {
            @Override
            public void onResponse(Call<ResponseGastosv2> call, Response<ResponseGastosv2> response) {
                validateResponseGastos(response,context);
            }

            @Override
            public void onFailure(Call<ResponseGastosv2> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateResponseGastos(Response<ResponseGastosv2> response, Context context) {
        if (response != null) {

            if (RetrofitValidations.checkSuccessCode(response.code())) {
                getGastos(response, context);
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getGastos(Response<ResponseGastosv2> response, Context context) {
        ResponseGastosv2 resp=response.body();
        if(resp!=null){
            String message = resp.getMessage();
            int responseCode = resp.getStatus();
            if(responseCode== 200){
                List<dataGastosOperativos> data=resp.getData();

                if(data!=null){

                    List<dataGastosOperativos> fdata= new ArrayList<>();
                    fdata.clear();
                    for(dataGastosOperativos gasto:data){
                        if( gasto.getGastosOperativos()!=null){
                            fdata.add(gasto);
                        }
                    }
                    if(fdata!=null) {
                        presenter.setGastos(fdata);
                    }
                }else{
                    Toast.makeText(context, "sin tickets asignados", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            }

        } else{
            Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
        }
    }

}

