package com.companyname.mauitest.Salida.Interactor;

import android.content.Context;
import android.widget.Toast;

import com.companyname.mauitest.Retrofit.GeneralConstants;
import com.companyname.mauitest.Retrofit.RetrofitClientNewlands;
import com.companyname.mauitest.Retrofit.RetrofitValidations;
import com.companyname.mauitest.Salida.Model.Ticket;
import com.companyname.mauitest.Salida.Model.requestSalida;
import com.companyname.mauitest.Salida.Model.responseSalida;
import com.companyname.mauitest.Salida.Presenter.presenterSalida;
import com.companyname.mauitest.Salida.Presenter.presenterSalidaImpl;
import com.companyname.mauitest.Salida.Util.serviceSalida;

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
          retrofitClient = RetrofitClientNewlands.getRetrofitInstance();
          service=retrofitClient.create(serviceSalida.class);
    }

    @Override
    public void requestSalida(String code) {
        requestDataSalida(code);

    }

    private void requestDataSalida(String code) {
        requestSalida request=new requestSalida("asdasds",code);
        Call<responseSalida> call=service.getSalida(request);
        call.enqueue(new Callback<responseSalida>() {
            @Override
            public void onResponse(Call<responseSalida> call, Response<responseSalida> response) {
                validateResponse(response,context);
            }

            @Override
            public void onFailure(Call<responseSalida> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateResponse(Response<responseSalida> response, Context context) {
        if (response != null) {

            if (RetrofitValidations.checkSuccessCode(response.code())) {
                getDataQR(response, context);
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getDataQR(Response<responseSalida> response, Context context) {
        responseSalida resp=response.body();
        if(resp!=null){
            String message = resp.getMessge();
            int responseCode = resp.getCode();
            if(resp.getCode()== GeneralConstants.RESPONSE_CODE_OK){
                List<Ticket> data=resp.getTickets();
                String qr=resp.getQr();
                String direccionEntrega=resp.getEnvio();
                if(direccionEntrega!=null){
                    presenter.setDireccion(direccionEntrega);
                }
                if(qr!=null){
                    presenter.setQRImage(qr);
                }else{
                    Toast.makeText(context, "no hay qr", Toast.LENGTH_SHORT).show();
                }
                if(data!=null){
                    presenter.setDataTickets(data);
                }else{
                    Toast.makeText(context, "sin tickets asignados", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
        }
    }
}
