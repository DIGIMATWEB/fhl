package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.presenter.salidaViewPresenter;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.presenter.salidaViewPresenterImplements;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.util.serviceSalida;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientPep;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitValidations;
import com.fhl.sistemadedistribucionfh.login.model.modelProfile.profileResponse;
import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.dataManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.responseManifestV2;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class salidaInteractorImplementation  implements salidainteractor {
    private Context context;
    private salidaViewPresenter presenter;
    private serviceSalida service;
    private Retrofit retrofitClient;
    public salidaInteractorImplementation (salidaViewPresenterImplements presenter, Context context){
    this.presenter=presenter;
    this.context=context;
    retrofitClient = RetrofitClientPep.getRetrofitInstance();
    service = retrofitClient.create(serviceSalida.class);
    }

    @Override
    public void detailManifest(String codigoValidador) {
        Gson gson = new Gson();
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token2 = preferences.getString(GeneralConstants.USER_VALUES, null);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        Log.e("TOKEN",""+token);
        profileResponse profileData = gson.fromJson(token2, profileResponse.class);

        int idEmpleado = profileData.getUsuarioId();
        String idEmpleadoString = String.valueOf(idEmpleado);

        //IdEmpleado correcto
        //TODO Cambiar por el token correcto
        presenter.showProgress();
        Call<responseManifestV2> call = service.getManifestV2(token,  idEmpleadoString,codigoValidador);
        Log.e("requestmanifest",""+call.request().toString());
        call.enqueue(new Callback<responseManifestV2>() {
            @Override
            public void onResponse(Call<responseManifestV2> call, Response<responseManifestV2> response) {
                validateResponse(response, context);
            }

            @Override
            public void onFailure(Call<responseManifestV2> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
               presenter.hideProgress();
            }
        });
    }
    private void validateResponse(Response<responseManifestV2> response, Context context) {
        if (response!=null) {
            if(RetrofitValidations.checkSuccessCode(response.code())) {
                getManifest(response, context);
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
                if(response.code()==401){
                   // presenter.returnTologin();
                    presenter.hideProgress();
                }
            }
        }
    }

    private void getManifest(Response<responseManifestV2> response, Context context) {
        responseManifestV2 resp = response.body();
        if(resp!=null) {
            String message = resp.getMessage();
            int responseCode = resp.getStatus();
            if(resp.getStatus() == GeneralConstants.RESPONSE_CODE_OK_PEP) {

                List<dataManifestV2> data = resp.getData();
                Gson gson = new Gson();
                String json = gson.toJson(data);
                Log.e("respDatamanifest",""+json);
                if(data!=null) {
                    presenter.setmanifest(data);
                    presenter.hideProgress();
                } else {
                    Toast.makeText(context, "Sin tickets asignados.", Toast.LENGTH_SHORT).show();
                   presenter.hideProgress();
                }
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
              presenter.hideProgress();
            }
        } else {
            Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            presenter.hideProgress();
        }
    }
    @Override
    public void detailCortina() {

    }

    @Override
    public void detailtickets() {

    }
}
