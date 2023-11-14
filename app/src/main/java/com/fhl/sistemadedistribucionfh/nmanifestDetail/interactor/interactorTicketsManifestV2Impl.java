package com.fhl.sistemadedistribucionfh.nmanifestDetail.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientNewlands;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientPep;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitValidations;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.model.dataTicketsManifest;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.model.requestTicketsManifest;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.model.responseTicketsManifest;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.requestTicketsManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.responseTicketsManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.presenter.presenterTicketsmanifestV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.util.serviceTicketsManifest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class interactorTicketsManifestV2Impl implements interactorTicketsManifestV2 {
    private serviceTicketsManifest service;
    private Context context;
    private presenterTicketsmanifestV2 presenter;
    private Retrofit retrofitClient;
    public interactorTicketsManifestV2Impl(presenterTicketsmanifestV2 presenter, Context context){
        this.presenter=presenter;
        this.context=context;
        //retrofitClient = RetrofitClientNewlands.getRetrofitInstance();
        retrofitClient = RetrofitClientPep.getRetrofitInstance();
        service = retrofitClient.create(serviceTicketsManifest.class);

    }
    @Override
    public void reqTickets(String ticket) {
        getAllTicketsV2(ticket);
    }

    public void getAllTicketsV2(String ticket) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        Log.e("TOKEN",""+token);
        String acceptHeaderValue = "text/plain";

        //requestTicketsManifestV2 request = new requestTicketsManifestV2(ticket);
        Call<responseTicketsManifestV2> call = service.getTicketsV2(ticket, acceptHeaderValue, token);
        call.enqueue(new Callback<responseTicketsManifestV2>() {
            @Override
            public void onResponse(Call<responseTicketsManifestV2> call, Response<responseTicketsManifestV2> response) {
                validateResponse(response,context);
            }

            @Override
            public void onFailure(Call<responseTicketsManifestV2> call, Throwable t) {
                Toast.makeText(context, "bad request"+t.getMessage(), Toast.LENGTH_SHORT).show();
                //presenter.setDatahardcode();
            }
        });
    }

    private void validateResponse(Response<responseTicketsManifestV2> response, Context context) {
        if (response != null) {

            if (RetrofitValidations.checkSuccessCode(response.code())) {
                getTickets(response, context);
            } else {
                Toast.makeText(context, "fail respose" + response.message(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getTickets(Response<responseTicketsManifestV2> response, Context context) {
        responseTicketsManifestV2 resp = response.body();
        if(resp!=null){
            String message = resp.getMessage();
            int responseCode = resp.getStatus();
            if(resp.getStatus()== GeneralConstants.RESPONSE_CODE_OK_PEP){
                List<dataTicketsManifestV2> data = resp.getData();

                if(data!=null){
                    presenter.setTickets(data);
                }else{
                    Toast.makeText(context, "sin tickets asignados", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(context, "response not ok" + response.message(), Toast.LENGTH_SHORT).show();
            }

        } else{
            Toast.makeText(context, "response null" + response.message(), Toast.LENGTH_SHORT).show();
        }
    }
}
