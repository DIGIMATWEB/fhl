package com.fhl.sistemadedistribucionfh.locator.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrifitClientSGD;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientFHManifest;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitValidations;
import com.fhl.sistemadedistribucionfh.locator.model.dataVehicleLocation;
import com.fhl.sistemadedistribucionfh.locator.model.requestVehicleLocation;
import com.fhl.sistemadedistribucionfh.locator.model.responseVehicleLocation;
import com.fhl.sistemadedistribucionfh.locator.presenter.presenterVehicles;
import com.fhl.sistemadedistribucionfh.locator.util.serviceLocationVehicle;
import com.fhl.sistemadedistribucionfh.login.model.modelProfile.profileResponse;
import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.dataManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.responseManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifest.util.manifestUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class interactorVehiclesImpl implements interactorVehicles{
    private presenterVehicles presenter;
    private Context context;
    private Retrofit retrofit,retrofit2;
    private serviceLocationVehicle service;
    private manifestUtil service2;
    public interactorVehiclesImpl(presenterVehicles presenter, Context context) {
        this.presenter=presenter;
        this.context=context;
        retrofit= RetrifitClientSGD.getRetrofitInstance();
        service= retrofit.create(serviceLocationVehicle.class);

        retrofit2=RetrofitClientFHManifest.getRetrofitInstance();
        service2= retrofit2.create(manifestUtil.class);
    }

    @Override
    public void requestLocation() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        String listaVehiculos = preferences.getString(GeneralConstants.LISTA_VEHICULOS_LOCATOR, null);
        if(token!=null){
            List<Integer> vehicles =new ArrayList<>();
            vehicles.clear();
//            if(listaVehiculos!=null){
//                Gson gson = new Gson();
//                // Convert JSON string to List<Integer>
//                Type listType = new TypeToken<ArrayList<Integer>>(){}.getType();
//                 vehicles = gson.fromJson(listaVehiculos, listType);
//            }
            requestMyVehicles(token,vehicles);
        }
    }
    private void requestMyVehicles(String token, List<Integer> mvehicles) {
        List<Integer> vehicles=new ArrayList<>();
        vehicles.clear();
        requestVehicleLocation request = new requestVehicleLocation(mvehicles);
        Gson gson=new Gson();
        String json=gson.toJson(request);
        Log.e("Locator","json: "+json);
        Log.e("Locator","token: "+token);
        Call<responseVehicleLocation> call= service.getVehicles(token,request);

        call.enqueue(new Callback<responseVehicleLocation>() {
            @Override
            public void onResponse(Call<responseVehicleLocation> call, Response<responseVehicleLocation> response) {
                validateResponse(response,context);
            }

            @Override
            public void onFailure(Call<responseVehicleLocation> call, Throwable t) {
                Toast.makeText(context, "requestMyVehicles "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateResponse(Response<responseVehicleLocation> response, Context context) {
        if (response != null) {

            if (RetrofitValidations.checkSuccessCode(response.code())) {
                getVehicles(response, context);
            } else {
                presenter.setVehicles(null);
                //Toast.makeText(context, "fail respose " + response.message(), Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void getVehicles(Response<responseVehicleLocation> response, Context context) {
           responseVehicleLocation resp = response.body();
                if(resp!=null){
                    String message = resp.getMessage();
                    int responseCode = resp.getStatus();
                    if(resp.getStatus()== GeneralConstants.RESPONSE_CODE_OK_FH){
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

    @Override
    public void requestVehicles() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token2 = preferences.getString(GeneralConstants.USER_VALUES, null);
        String user = preferences.getString(GeneralConstants.OPERADOR_ID, null);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        Log.e("TOKEN",""+token);
      //  profileResponse profileData = gson.fromJson(token2, profileResponse.class);

//        Integer idEmpleado = profileData.getUsuarioId();
//        String idEmpleadoString ="";
//        if (idEmpleado != null) {
//            idEmpleadoString = String.valueOf(idEmpleado);
//        } else {
//            // Handle the case where profileData.getUsuarioId() returns null
//            idEmpleadoString = ""; // or any other default value you want to assign
//        }

        Log.e("empleado",""+user);
        //IdEmpleado correcto
        //TODO Cambiar por el token correcto
        presenter.showDialog();
        Call<responseManifestV2> call = service2.getManifestV2(token,  user);
        Log.e("requestmanifest",""+call.request().toString());
        call.enqueue(new Callback<responseManifestV2>() {
            @Override
            public void onResponse(Call<responseManifestV2> call, Response<responseManifestV2> response) {
                validateResponse2(response, context);
            }
            @Override
            public void onFailure(Call<responseManifestV2> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                presenter.hideDialog();
            }
        });
    }

    private void validateResponse2(Response<responseManifestV2> response, Context context) {
        if (response!=null) {
            if(RetrofitValidations.checkSuccessCode(response.code())) {
                getManifest(response, context);
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
                if(response.code()==401){
                    presenter.hideDialog();
                }
            }
        }
    }

    private void getManifest(Response<responseManifestV2> response, Context context) {
        responseManifestV2 resp = response.body();
        if(resp!=null) {
            String message = resp.getMessage();
            int responseCode = resp.getStatus();
            if(resp.getStatus() == GeneralConstants.RESPONSE_CODE_OK_FH) {

                List<dataManifestV2> data = resp.getData();
                Gson gson = new Gson();
                String json = gson.toJson(data);
                Log.e("respDatamanifest",""+json);
                if(data!=null) {
                    presenter.setVehicleinmanifestV2(data.get(0).getVehiculo().getEconomico());
                    presenter.hideDialog();

                } else {
                    Toast.makeText(context, "Sin tickets asignados.", Toast.LENGTH_SHORT).show();
                    presenter.hideDialog();
                }
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
                presenter.hideDialog();
            }
        } else {
            Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            presenter.hideDialog();
        }
    }

}
