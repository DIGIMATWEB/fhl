package com.fhl.sistemadedistribucionfh.checkList.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientFHManifest;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientFHVehicles;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitValidations;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.VehiculoVsCheck;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.dataChecklistV2;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.responseChecklistV2;
import com.fhl.sistemadedistribucionfh.checkList.presenter.checklistPresenter;
import com.fhl.sistemadedistribucionfh.checkList.util.serviceChecklist;
import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.dataManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.responseManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifest.util.manifestUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class checklistInteractorImpl implements checklistInteractor {
    private Context context;
    private checklistPresenter presenter;
    private serviceChecklist service;
    private manifestUtil service2;
    private Retrofit retrofitClient,retrofitClient2;

    public checklistInteractorImpl(checklistPresenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
        retrofitClient = RetrofitClientFHVehicles.getRetrofitInstance();
        service = retrofitClient.create(serviceChecklist.class);

        retrofitClient2 = RetrofitClientFHManifest.getRetrofitInstance();
        service2 = retrofitClient2.create(manifestUtil.class);

    }

    @Override
    public void requestChecklist() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        String vehicle= preferences.getString(GeneralConstants.VEHICLEID,null);
        // requestChecklist request= new requestChecklist("asfasfaesweqwf");
        Call<responseChecklistV2> call = service.getChecklist(token, Integer.valueOf(vehicle));
        call.enqueue(new Callback<responseChecklistV2>() {
            @Override
            public void onResponse(Call<responseChecklistV2> call, Response<responseChecklistV2> response) {
                validateResponseChecklist(response, context);
            }

            @Override
            public void onFailure(Call<responseChecklistV2> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void validateResponseChecklist(Response<responseChecklistV2> response, Context context) {
        if (response != null) {

            if (RetrofitValidations.checkSuccessCode(response.code())) {
                getChecklist(response, context);
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getChecklist(Response<responseChecklistV2> response, Context context) {
        responseChecklistV2 resp = response.body();
        if (resp != null) {
            String message = resp.getMessage();
            int responseCode = resp.getStatus();
            if (resp.getStatus() == GeneralConstants.RESPONSE_CODE_OK_PEP) {
                dataChecklistV2 mdata = resp.getData();
                if (mdata != null) {
                    List<VehiculoVsCheck> data = mdata.getVehiculoVsChecklist();

                    if (data != null) {
                        Gson gson= new Gson();
                        String jsonChecklist=gson.toJson(mdata);
                        Log.e("checklist",""+jsonChecklist);
                        presenter.setChecklist(mdata);
                    } else {
                        Toast.makeText(context, "sin tickets asignados", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "sin tickets asignados", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void requestManifestVehicle() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        String user = preferences.getString(GeneralConstants.OPERADOR_ID, null);
        Call<responseManifestV2> call = service2.getManifestV2(token,  user);
        Log.e("requestmanifest",""+call.request().toString());
        call.enqueue(new Callback<responseManifestV2>() {
            @Override
            public void onResponse(Call<responseManifestV2> call, Response<responseManifestV2> response) {
                validateResponse(response, context);
            }
            @Override
            public void onFailure(Call<responseManifestV2> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void validateResponse(Response<responseManifestV2> response, Context context) {
        if (response!=null) {
            if(RetrofitValidations.checkSuccessCode(response.code())) {
                getManifest(response, context);
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();

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
                    if (data.get(0) != null) {
                        Log.e("vehicleId",""+data.get(0).getVehiculoId());
                        SharedPreferences preferencias = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferencias.edit();
                        editor.putString(GeneralConstants.VEHICLEID,String.valueOf(data.get(0).getVehiculoId()));
                        editor.commit();
                        presenter.continueChecklist();
                    }
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
}
