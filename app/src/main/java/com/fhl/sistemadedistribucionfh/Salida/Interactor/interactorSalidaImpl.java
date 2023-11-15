package com.fhl.sistemadedistribucionfh.Salida.Interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientNewlands;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientPep;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitValidations;
import com.fhl.sistemadedistribucionfh.Salida.Model.test.Ticket;
import com.fhl.sistemadedistribucionfh.Salida.Model.test.requestSalida;
import com.fhl.sistemadedistribucionfh.Salida.Model.test.responseSalida;
import com.fhl.sistemadedistribucionfh.Salida.Model.v2.ResponseSalida;
import com.fhl.sistemadedistribucionfh.Salida.Presenter.presenterSalida;
import com.fhl.sistemadedistribucionfh.Salida.Presenter.presenterSalidaImpl;
import com.fhl.sistemadedistribucionfh.Salida.Util.serviceSalida;
import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.dataManifestV2;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class interactorSalidaImpl implements interactorSalida {
    private Context context;
    private presenterSalida presenter;
    private serviceSalida service;
    private Retrofit retrofitClient;

    public interactorSalidaImpl(presenterSalidaImpl presenter, Context context) {
          this.context=context;
          this.presenter=presenter;
          retrofitClient = RetrofitClientPep.getRetrofitInstance();
          service=retrofitClient.create(serviceSalida.class);
    }

    @Override
    public void requestSalida(String code) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        if(token!=null) {
            requestDataSalida(token,code);
        }

    }

    private void requestDataSalida(String token,String code) {
        Call<ResponseSalida> call=service.getSalidaV2(token,code);
        Log.e("QR","code qr "+code);
        call.enqueue(new Callback<ResponseSalida>() {
            @Override
            public void onResponse(Call<ResponseSalida> call, Response<ResponseSalida> response) {
                validateResponse(response,context);
            }

            @Override
            public void onFailure(Call<ResponseSalida> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateResponse(Response<ResponseSalida> response, Context context) {
        if (response != null) {
            if (RetrofitValidations.checkSuccessCode(response.code())) {
                getDataQRv2(response, context);
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getDataQRv2(Response<ResponseSalida> response, Context context) {
        ResponseSalida resp = response.body();
        if(resp!=null) {
            String message = resp.getMessage();
            int responseCode = resp.getStatus();
            if(resp.getStatus() == GeneralConstants.RESPONSE_CODE_OK_PEP) {


                if(resp.getData()!=null) {
                    presenter.setSalida(response);
                } else {
                    Toast.makeText(context, "Sin tickets asignados.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
        }
    }

//    private void getDataQR(Response<responseSalida> response, Context context) {
//        responseSalida resp=response.body();
//        if(resp!=null){
//            String message = resp.getMessge();
//            int responseCode = resp.getCode();
//            if(resp.getCode()== GeneralConstants.RESPONSE_CODE_OK){
//                List<Ticket> data=resp.getTickets();
//                String qr=resp.getQr();
//                String direccionEntrega=resp.getEnvio();
//                if(direccionEntrega!=null){
//                    presenter.setDireccion(direccionEntrega);
//                }
//                if(qr!=null){
//                    presenter.setQRImage(qr);
//                }else{
//                    Toast.makeText(context, "no hay qr", Toast.LENGTH_SHORT).show();
//                }
//                if(data!=null){
//                    presenter.setDataTickets(data);
//                }else{
//                    Toast.makeText(context, "sin tickets asignados", Toast.LENGTH_SHORT).show();
//                }
//            }else{
//                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
//            }
//        }else{
//            Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
//        }
//    }
}
