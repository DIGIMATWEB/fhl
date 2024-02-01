package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.cortina.dataCortina;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.cortina.responseCortina;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.responseManifestSalidaV2;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.responseManifestSalidaV2data;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.sellos.ResponseSellos;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.presenter.salidaViewPresenter;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.presenter.salidaViewPresenterImplements;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.util.serviceSalida;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientPep;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitValidations;
import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;
import com.fhl.sistemadedistribucionfh.login.model.modelProfile.profileResponse;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.responseTicketsManifestV2;
import com.google.gson.Gson;

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
        Call<responseManifestSalidaV2> call = service.getManifestV2(token,  idEmpleadoString,codigoValidador);
        Log.e("requestmanifest",""+call.request().toString());
        call.enqueue(new Callback<responseManifestSalidaV2>() {
            @Override
            public void onResponse(Call<responseManifestSalidaV2> call, Response<responseManifestSalidaV2> response) {
                validateResponse(response, context);
            }

            @Override
            public void onFailure(Call<responseManifestSalidaV2> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
               presenter.hideProgress();
            }
        });
    }
    private void validateResponse(Response<responseManifestSalidaV2> response, Context context) {
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

    private void getManifest(Response<responseManifestSalidaV2> response, Context context) {
        responseManifestSalidaV2 resp = response.body();
        if(resp!=null) {
            String message = resp.getMessage();
            int responseCode = resp.getStatus();
            if(resp.getStatus() == GeneralConstants.RESPONSE_CODE_OK_PEP) {

                List<responseManifestSalidaV2data> data = resp.getData();
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
    public void detailCortina(String codigoValidador) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        Call<responseCortina> call = service.getcortina(token,codigoValidador,"");
        call.enqueue(new Callback<responseCortina>() {
            @Override
            public void onResponse(Call<responseCortina> call, Response<responseCortina> response) {
                validateResponseCortina(response, context);
            }

            @Override
            public void onFailure(Call<responseCortina> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                presenter.hideProgress();
            }
        });
    }

    private void validateResponseCortina(Response<responseCortina> response, Context context) {
        if (response!=null) {
            if(RetrofitValidations.checkSuccessCode(response.code())) {
                getCortina(response, context);
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
                if(response.code()==401){
                    // presenter.returnTologin();
                    presenter.hideProgress();
                }
            }
        }
    }

    private void getCortina(Response<responseCortina> response, Context context) {
        responseCortina resp = response.body();
        if(resp!=null) {
            String message = resp.getMessage();
            int responseCode = resp.getStatus();
            if(resp.getStatus() == GeneralConstants.RESPONSE_CODE_OK_PEP) {

                dataCortina data = resp.getData();
//                Gson gson = new Gson();
//                String json = gson.toJson(data);
//                Log.e("respDatamanifest",""+json);
                if(data!=null) {
                    presenter.setCortina(data);
                    presenter.hideProgress();
                } else {
                    presenter.goTickets();
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
    public void detailtickets(String currentManifest) {
        Log.e("detailticketsSalida",""+currentManifest);
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        Log.e("TOKEN",""+token);

        //requestTicketsManifestV2 request = new requestTicketsManifestV2(ticket);
        Call<responseTicketsManifestV2> call = service.getTicketsV2(token,currentManifest);
        call.enqueue(new Callback<responseTicketsManifestV2>() {
            @Override
            public void onResponse(Call<responseTicketsManifestV2> call, Response<responseTicketsManifestV2> response) {
                validateResponsetickets(response,context);
            }

            @Override
            public void onFailure(Call<responseTicketsManifestV2> call, Throwable t) {
                Toast.makeText(context, "bad request"+t.getMessage(), Toast.LENGTH_SHORT).show();
                //presenter.setDatahardcode();
            }
        });
    }



    private void validateResponsetickets(Response<responseTicketsManifestV2> response, Context context) {
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
    @Override
    public void detailSellos(String currentManifest) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        Call<ResponseSellos> call=service.getSalidaV2(token,currentManifest);
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
                getDataSellos(response, context);
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getDataSellos(Response<ResponseSellos> response, Context context) {
        ResponseSellos resp = response.body();
        if(resp!=null) {
            String message = resp.getMessage();
            int responseCode = resp.getStatus();

            if (resp.getStatus() == GeneralConstants.RESPONSE_CODE_OK_PEP) {//cada ticket tiene N cantidad de sellos
                List<Sello> sellos= resp.getData().getSellos();

                    if (sellos != null) {
                        presenter.setSellos(sellos);
                    }

            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
        }
    }
}
