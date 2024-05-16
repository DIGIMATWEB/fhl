package com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Dialogs.habilities.model.driver.responseDriver;
import com.fhl.sistemadedistribucionfh.Dialogs.habilities.model.vehicle.responseVehicle;
import com.fhl.sistemadedistribucionfh.Dialogs.habilities.util.serviceHabilities;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.Validadorset;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.requestSetValidacion;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.responseSetValidacion;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.presenter.presenterSetValidacion;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.util.serviceSetValidacionManifest;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrifitClientSGD;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientFHManifest;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitValidations;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class interactorSetValidacionImpl implements interactorSetValidacion{
    private presenterSetValidacion presenter;
    private Context context;
    private serviceSetValidacionManifest service;
    private Retrofit retrofitClient;
    private Retrofit retrofit;
    private serviceHabilities service2;

    public interactorSetValidacionImpl(presenterSetValidacion presenter, Context context) {
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientFHManifest.getRetrofitInstance();
        service = retrofitClient.create(serviceSetValidacionManifest.class);

        retrofit= RetrifitClientSGD.getRetrofitInstance();
        service2= retrofit.create(serviceHabilities.class);
    }

    @Override
    public void setValidacionMenifest(String manifest) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        if(token!=null){
            requestValidacionManifest(manifest,token);
        }
    }



    private void requestValidacionManifest(String manifest, String token) {
       Validadorset newset=new Validadorset("","Correcto");
       requestSetValidacion request = new requestSetValidacion(manifest,newset);
       Call<responseSetValidacion> call= service.getValidacion(token,request);
       call.enqueue(new Callback<responseSetValidacion>() {
           @Override
           public void onResponse(Call<responseSetValidacion> call, Response<responseSetValidacion> response) {
               validateSetValidacionManifest(response, context);
           }

           @Override
           public void onFailure(Call<responseSetValidacion> call, Throwable t) {
               presenter.setresponseValidacionMenifest("401");

           }
       });

    }

    private void validateSetValidacionManifest(Response<responseSetValidacion> response, Context context) {
        if (response!=null) {
            if(RetrofitValidations.checkSuccessCode(response.code())) {
                setValidacion(response, context);
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
                if(response.code()==401){
                    // presenter.returnTologin();
                    presenter.setresponseValidacionMenifest("401");
                }
            }
        }
    }

    private void setValidacion(Response<responseSetValidacion> response, Context context) {
         responseSetValidacion resp = response.body();
                if(resp!=null) {
                    String message = resp.getMessage();
                    int responseCode = resp.getStatus();
                    if(resp.getStatus() == GeneralConstants.RESPONSE_CODE_OK_FH) {

                        presenter.setresponseValidacionMenifest("105");

                    } else {
                        presenter.setresponseValidacionMenifest("107");
                    }
                } else {

                    presenter.setresponseValidacionMenifest("401");
                }
    }
    @Override
    public void getDrirver() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        String operador= preferences.getString(GeneralConstants.OPERADOR_ID,null);
        if(token!=null){
            goGetDriver(token,operador);
        }
    }

    private void goGetDriver(String token, String operador) {
        Call<responseDriver> call= service2.getDriverInfo(token,Integer.valueOf(operador));
        call.enqueue(new Callback<responseDriver>() {
            @Override
            public void onResponse(Call<responseDriver> call, Response<responseDriver> response) {
                validategoGetDriver(response, context);
            }

            @Override
            public void onFailure(Call<responseDriver> call, Throwable t) {
                Log.e("errorGetDriver",""+t.getMessage());
            }
        });
    }

    private void validategoGetDriver(Response<responseDriver> response, Context context) {
        if (response!=null) {
            if(RetrofitValidations.checkSuccessCode(response.code())) {
                setValidaciongoGetDriver(response, context);
            } else {
                Log.e("errorGetDriver",""+response.code());
            }
        }
    }

    private void setValidaciongoGetDriver(Response<responseDriver> response, Context context) {
        responseDriver resp= response.body();
        if(resp!=null) {
            String message = resp.getMessage();
            int responseCode = resp.getStatus();
            if(resp.getStatus() == GeneralConstants.RESPONSE_CODE_OK_FH) {
                presenter.setDriverHailities(resp.getData().getHabilidades());
            } else {
               Log.e("errorGetVehicle",""+message);
            }
        } else {
            Log.e("errorGetVehicle","respuesta nula");
        }
    }

    @Override
    public void getVehicle() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        String vehiculo= preferences.getString(GeneralConstants.VEHICLEID,null);
        if(token!=null){
            goGetVehicle(token,vehiculo);
        }
    }

    private void goGetVehicle(String token, String vehiculo) {
        Call<responseVehicle> call= service2.getVehicleInfor(token,Integer.valueOf(vehiculo));
        call.enqueue(new Callback<responseVehicle>() {
            @Override
            public void onResponse(Call<responseVehicle> call, Response<responseVehicle> response) {
                validategoGetVehicle(response, context);
            }

            @Override
            public void onFailure(Call<responseVehicle> call, Throwable t) {
                Log.e("errorGetVehicle",""+t.getMessage());
            }
        });
    }

    private void validategoGetVehicle(Response<responseVehicle> response, Context context) {
        if (response!=null) {
            if(RetrofitValidations.checkSuccessCode(response.code())) {
                setValidaciongoGetVehicle(response, context);
            } else {
                Log.e("errorGetVehicle",""+response.code());
            }
        }
    }

    private void setValidaciongoGetVehicle(Response<responseVehicle> response, Context context) {
        responseVehicle resp= response.body();
                if(resp!=null) {
                    String message = resp.getMessage();
                    int responseCode = resp.getStatus();
                    if(resp.getStatus() == GeneralConstants.RESPONSE_CODE_OK_FH) {
                        presenter.setDriverVehicle(resp.getData().getHabilidadVehiculos());
                    } else {
                       Log.e("errorGetVehicle",""+message);
                    }
                } else {
                    Log.e("errorGetVehicle","respuesta nula");
                }
    }
}
