package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.cortina.Anden;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.cortina.dataCortina;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.cortina.responseCortina;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.responseManifestSalidaV2;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.responseManifestSalidaV2data;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.sellos.ResponseSellos;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.presenter.salidaViewPresenter;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.presenter.salidaViewPresenterImplements;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.util.serviceSalida;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientFHManifest;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitValidations;
import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;
import com.fhl.sistemadedistribucionfh.login.model.modelProfile.profileResponse;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.responseTicketsManifestV2;
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
    retrofitClient = RetrofitClientFHManifest.getRetrofitInstance();
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
                Toast.makeText(context, "onfailure detailManifest: "+t.getMessage(), Toast.LENGTH_SHORT).show();
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
            if(resp.getStatus() == GeneralConstants.RESPONSE_CODE_OK_FH) {

                List<responseManifestSalidaV2data> data = resp.getData();
                Gson gson = new Gson();
                String json = gson.toJson(data);
                Log.e("respDatamanifest",""+json);
                if(data!=null) {
                    if(data.isEmpty()) {
                        Toast.makeText(context, "Sin informacion", Toast.LENGTH_SHORT).show();
                    }else {
                        presenter.setmanifest(data);
                    }
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
                Toast.makeText(context, "No tienes cortinas en este manifiesto", Toast.LENGTH_SHORT).show();
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
                   Toast.makeText(context, "No tienes cortinas en este manifiesto", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    private void getCortina(Response<responseCortina> response, Context context) {
        responseCortina resp = response.body();
        if(resp!=null) {
            String message = resp.getMessage();
            int responseCode = resp.getStatus();
            if(resp.getStatus() == GeneralConstants.RESPONSE_CODE_OK_FH) {

                dataCortina data = resp.getData();
//                Gson gson = new Gson();
//                String json = gson.toJson(data);
//                Log.e("respDatamanifest",""+json);
                if(data!=null) {
                    presenter.setCortina(data);
                    presenter.hideProgress();
                    //Toast.makeText(context, "No tienes cortinas en este manifiesto", Toast.LENGTH_SHORT).show();
                } else {//todo cambiar a codigo null
                   // presenter.goTickets();
                    String  qr= "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";
                    Anden  andend=new Anden(1,"Anden",false,"C-9",7,qr);
                    data= new dataCortina(0,"","","",0,andend,"");
                    presenter.setCortina(data);
                    //Toast.makeText(context, "No tienes cortinas en este manifiesto", Toast.LENGTH_SHORT).show();
                    // Toast.makeText(context, "Sin tickets asignados.", Toast.LENGTH_SHORT).show();
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
            if(resp.getStatus()== GeneralConstants.RESPONSE_CODE_OK_FH){
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

            if (resp.getStatus() == GeneralConstants.RESPONSE_CODE_OK_FH) {//cada ticket tiene N cantidad de sellos
                if(resp.getData()!=null) {


                    List<Sello> sellos = resp.getData().getSellos();

                    if (sellos != null) {
                        presenter.setSellos(sellos);
                    } else {
                        Log.e("dialogSalida","sellos null");
                        presenter.setSellos(null);
                    }
                }else {
                    Log.e("dialogSalida","datasellosnull");
                    List<Sello> sellos =new ArrayList<>();
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
