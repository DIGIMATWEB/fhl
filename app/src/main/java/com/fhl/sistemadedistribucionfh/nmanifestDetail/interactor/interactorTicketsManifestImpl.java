package com.fhl.sistemadedistribucionfh.nmanifestDetail.interactor;

import android.content.Context;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientNewlands;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitValidations;
import com.fhl.sistemadedistribucionfh.nmanifest.model.dataManifest;
import com.fhl.sistemadedistribucionfh.nmanifest.model.responseManifest;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.model.dataTicketsManifest;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.model.requestTicketsManifest;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.model.responseTicketsManifest;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.presenter.presenterTicketsmanifest;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.util.serviceTicketsManifest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class interactorTicketsManifestImpl implements  interactorTicketsManifest{
    private serviceTicketsManifest service;
    private Context context;
    private presenterTicketsmanifest presenter;
    private Retrofit retrofitClient;
    public interactorTicketsManifestImpl(presenterTicketsmanifest presenter,Context context){
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientNewlands.getRetrofitInstance();
        service=retrofitClient.create(serviceTicketsManifest.class);

    }
    @Override
    public void reqTickets(String ticket) {
        requestTicketsManifest request=new requestTicketsManifest(ticket);
        Call<responseTicketsManifest> call= service.getTickets(request);
        call.enqueue(new Callback<responseTicketsManifest>() {
            @Override
            public void onResponse(Call<responseTicketsManifest> call, Response<responseTicketsManifest> response) {
                validateResponse(response,context);
            }

            @Override
            public void onFailure(Call<responseTicketsManifest> call, Throwable t) {
                Toast.makeText(context, "bad request"+t.getMessage(), Toast.LENGTH_SHORT).show();
                //presenter.setDatahardcode();
            }
        });
    }

    private void validateResponse(Response<responseTicketsManifest> response, Context context) {
        if (response != null) {

            if (RetrofitValidations.checkSuccessCode(response.code())) {
                getTickets(response, context);
            } else {
                Toast.makeText(context, "fail respose" + response.message(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getTickets(Response<responseTicketsManifest> response, Context context) {
        responseTicketsManifest resp=response.body();
        if(resp!=null){
            String message = resp.getMessage();
            int responseCode = resp.getResconseCode();
            if(resp.getResconseCode()== GeneralConstants.RESPONSE_CODE_OK){
                List<dataTicketsManifest> data=resp.getData();

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
