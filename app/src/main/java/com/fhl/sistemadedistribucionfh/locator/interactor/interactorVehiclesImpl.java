package com.fhl.sistemadedistribucionfh.locator.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrifitClientSGD;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitValidations;
import com.fhl.sistemadedistribucionfh.locator.model.dataVehicleLocation;
import com.fhl.sistemadedistribucionfh.locator.model.requestVehicleLocation;
import com.fhl.sistemadedistribucionfh.locator.model.responseVehicleLocation;
import com.fhl.sistemadedistribucionfh.locator.presenter.presenterVehicles;
import com.fhl.sistemadedistribucionfh.locator.util.serviceLocationVehicle;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class interactorVehiclesImpl implements interactorVehicles{
    private presenterVehicles presenter;
    private Context context;
    private Retrofit retrofit;
    private serviceLocationVehicle service;
    public interactorVehiclesImpl(presenterVehicles presenter, Context context) {
        this.presenter=presenter;
        this.context=context;
        retrofit= RetrifitClientSGD.getRetrofitInstance();
        service= retrofit.create(serviceLocationVehicle.class);
    }

    @Override
    public void requestLocation() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        if(token!=null){
            requestMyVehicles(token);
        }
    }

    private void requestMyVehicles(String token) {
        List<Integer> vehicles=new ArrayList<>();
        vehicles.clear();
        requestVehicleLocation request = new requestVehicleLocation(vehicles);
        Call<responseVehicleLocation> call= service.getVehicles(token,request);
        call.enqueue(new Callback<responseVehicleLocation>() {
            @Override
            public void onResponse(Call<responseVehicleLocation> call, Response<responseVehicleLocation> response) {
                validateResponse(response,context);
            }

            @Override
            public void onFailure(Call<responseVehicleLocation> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateResponse(Response<responseVehicleLocation> response, Context context) {
        if (response != null) {

            if (RetrofitValidations.checkSuccessCode(response.code())) {
                getVehicles(response, context);
            } else {
                Toast.makeText(context, "fail respose" + response.message(), Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void getVehicles(Response<responseVehicleLocation> response, Context context) {
           responseVehicleLocation resp = response.body();
                if(resp!=null){
                    String message = resp.getMessage();
                    int responseCode = resp.getStatus();
                    if(resp.getStatus()== GeneralConstants.RESPONSE_CODE_OK_PEP){
                       List<dataVehicleLocation> data = resp.getData();

                        if(data!=null){
                            presenter.setVehicles(data);
                        }else{
                            Log.e("manifestDetail","sin tickets asignados");
                           // Toast.makeText(context, "sin tickets asignados", Toast.LENGTH_SHORT).show();
                        }
                      //  presenter.hideDialog();
                    }else{
                        Log.e("manifestDetail","response not ok");//Toast.makeText(context, "response not ok" + response.message(), Toast.LENGTH_SHORT).show();
                       // presenter.hideDialog();
                    }

                } else{
                    Log.e("manifestDetail","response null");//Toast.makeText(context, "response null" + response.message(), Toast.LENGTH_SHORT).show();
                //    presenter.hideDialog();
                }
    }
}
