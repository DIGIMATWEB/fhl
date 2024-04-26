package com.fhl.sistemadedistribucionfh.nmanifestDetail.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.sellos.ResponseSellos;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientFHManifest;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitValidations;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.responseTicketsManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.presenter.presenterTicketsmanifestV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.util.serviceTicketsManifest;
import com.google.gson.Gson;

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
        retrofitClient = RetrofitClientFHManifest.getRetrofitInstance();
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

        //requestTicketsManifestV2 request = new requestTicketsManifestV2(ticket);
        Call<responseTicketsManifestV2> call = service.getTicketsV2(ticket, false, token);
        Gson gson =new Gson();
        String json = gson.toJson(call.request());
        Log.e("getticketsdetail",""+json);
        presenter.showDialog();
        call.enqueue(new Callback<responseTicketsManifestV2>() {
            @Override
            public void onResponse(Call<responseTicketsManifestV2> call, Response<responseTicketsManifestV2> response) {
                validateResponse(response,context);
            }

            @Override
            public void onFailure(Call<responseTicketsManifestV2> call, Throwable t) {
                Toast.makeText(context, "bad request"+t.getMessage(), Toast.LENGTH_SHORT).show();
                presenter.hideDialog();
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
                presenter.hideDialog();
            }
        }
    }

    private void getTickets(Response<responseTicketsManifestV2> response, Context context) {
        responseTicketsManifestV2 resp = response.body();
        if(resp!=null){
            String message = resp.getMessage();
            int responseCode = resp.getStatus();
            if(resp.getStatus()== GeneralConstants.RESPONSE_CODE_OK_FH){
                List<dataTicketsManifestV2> data = resp.getData();

                if(data!=null){
                    presenter.setTickets(data);
                }else{
                    Log.e("manifestDetail","sin tickets asignados");
                   // Toast.makeText(context, "sin tickets asignados", Toast.LENGTH_SHORT).show();
                }
                presenter.hideDialog();
            }else{
                Log.e("manifestDetail","response not ok");//Toast.makeText(context, "response not ok" + response.message(), Toast.LENGTH_SHORT).show();
                presenter.hideDialog();
            }

        } else{
            Log.e("manifestDetail","response null");//Toast.makeText(context, "response null" + response.message(), Toast.LENGTH_SHORT).show();
            presenter.hideDialog();
        }
    }
    @Override
    public void reqSellos(String folioDespachoId) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        if(token!=null) {
            requestDataSalida(token,folioDespachoId);
        }
    }
    private void requestDataSalida(String token,String code) {
        Call<ResponseSellos> call=service.getSalidaV2(token,code);
        Log.e("QR","code qr "+code);
        call.enqueue(new Callback<ResponseSellos>() {
            @Override
            public void onResponse(Call<ResponseSellos> call, Response<ResponseSellos> response) {
                validateResponseSellos(response,context);
            }

            @Override
            public void onFailure(Call<ResponseSellos> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void validateResponseSellos(Response<ResponseSellos> response, Context context) {
        if (response != null) {
            if (RetrofitValidations.checkSuccessCode(response.code())) {
                getDataQRv2(response, context);
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getDataQRv2(Response<ResponseSellos> response, Context context) {
        ResponseSellos resp = response.body();
        if(resp!=null) {
            String message = resp.getMessage();
            int responseCode = resp.getStatus();
            if(resp.getStatus() == GeneralConstants.RESPONSE_CODE_OK_FH) {


                if(resp.getData()!=null) {
                    presenter.setSellos(resp.getData().getSellos());
                } else {
                    //Toast.makeText(context, "Sin tickets asignados2.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
        }
    }
}
