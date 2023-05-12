package com.fhl.sistemadedistribucionfh.Dialogs.validador.interactor;

import android.content.Context;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Dialogs.validador.model.dataValidador;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.model.requestValidador;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.model.responseValidador;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.presenter.presenterValidador;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.util.utilValidador;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientNewlands;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitValidations;
import com.fhl.sistemadedistribucionfh.Salida.Model.Ticket;
import com.fhl.sistemadedistribucionfh.Salida.Model.responseSalida;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class interactorValidadorImpl implements  interactorValidador{
    private presenterValidador presenter;
    private Context context;
    private utilValidador service;
    private Retrofit retrofitClient;
    public interactorValidadorImpl(presenterValidador presenter, Context context) {
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientNewlands.getRetrofitInstance();
        service=retrofitClient.create(utilValidador.class);
    }

    @Override
    public void requestmDespachos(String codigo) {
    requestValidador request= new requestValidador("sdada",codigo);
        Call<responseValidador> call= service.getDespachos(request);
        call.enqueue(new Callback<responseValidador>() {
            @Override
            public void onResponse(Call<responseValidador> call, Response<responseValidador> response) {
                validateResponse(response,context);
            }

            @Override
            public void onFailure(Call<responseValidador> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                presenter.errorValidador();
            }
        });
    }

    private void validateResponse(Response<responseValidador> response, Context context) {
        if (response != null) {

            if (RetrofitValidations.checkSuccessCode(response.code())) {
                getDatadespachos(response, context);
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getDatadespachos(Response<responseValidador> response, Context context) {
        responseValidador resp=response.body();
        if(resp!=null){
            String message = resp.getMessage();
            int responseCode = resp.getResconseCode();
            if(responseCode== GeneralConstants.RESPONSE_CODE_OK){
                List<dataValidador> data=resp.getData();
                if(data!=null){
                        presenter.setDespachotoView(data);
                }else{
                    Toast.makeText(context, "sin datos en el despacho", Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
        }
    }
}
