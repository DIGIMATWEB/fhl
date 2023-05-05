package com.companyname.mauitest.gastos.interactor;

import android.content.Context;
import android.widget.Toast;

import com.companyname.mauitest.Retrofit.GeneralConstants;
import com.companyname.mauitest.Retrofit.RetrofitClientNewlands;
import com.companyname.mauitest.Retrofit.RetrofitValidations;
import com.companyname.mauitest.checkList.model.dataChecklist;
import com.companyname.mauitest.checkList.model.responseChecklist;
import com.companyname.mauitest.gastos.model.dataGastos;
import com.companyname.mauitest.gastos.model.requestGastos;
import com.companyname.mauitest.gastos.model.responseGastos;
import com.companyname.mauitest.gastos.presenter.presenterGastos;
import com.companyname.mauitest.gastos.util.serviceGastos;

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
        retrofitClient = RetrofitClientNewlands.getRetrofitInstance();
        service=retrofitClient.create(serviceGastos.class);
    }
    @Override
    public void requestGastos() {
        requestGastos request=new requestGastos("asdada");
        Call<responseGastos> call=service.getGastos(request);
        call.enqueue(new Callback<responseGastos>() {
            @Override
            public void onResponse(Call<responseGastos> call, Response<responseGastos> response) {
                validateResponseGastos(response,context);
            }

            @Override
            public void onFailure(Call<responseGastos> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void validateResponseGastos(Response<responseGastos> response, Context context) {
        if (response != null) {

            if (RetrofitValidations.checkSuccessCode(response.code())) {
                getGastos(response, context);
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getGastos(Response<responseGastos> response, Context context) {
        responseGastos resp=response.body();
        if(resp!=null){
            String message = resp.getMessage();
            int responseCode = resp.getResconseCode();
            if(resp.getResconseCode()== GeneralConstants.RESPONSE_CODE_OK){
                List<dataGastos> data=resp.getData();

                if(data!=null){
                    presenter.setGastos(data);
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

