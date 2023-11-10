package com.fhl.sistemadedistribucionfh.nmanifest.interactorV2;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientPep;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitValidations;
import com.fhl.sistemadedistribucionfh.login.model.modelProfile.profileResponse;
import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.dataManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.requestManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.responseManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifest.presenterV2.presentermanifestV2;
import com.fhl.sistemadedistribucionfh.nmanifest.util.manifestUtil;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class interactorManifestImplV2 implements interactorManifestV2 {
    private Context context;
    private presentermanifestV2 presenter;
    private manifestUtil service;
    private Retrofit retrofitClient;

    public interactorManifestImplV2(presentermanifestV2 presenter, Context context) {
        this.context = context;
        this.presenter = presenter;
        retrofitClient = RetrofitClientPep.getRetrofitInstance();
        service = retrofitClient.create(manifestUtil.class);
    }

    public void getAllmanifestV2() {
        Gson gson = new Gson();
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token2 = preferences.getString(GeneralConstants.USER_VALUES, null);
        String jsonStringProfileData = gson.toJson(token2);
        profileResponse profileData = gson.fromJson(jsonStringProfileData, profileResponse.class);

        int idEmpleado = profileData.getEmpleadoId();
        String idEmpleadoString = String.valueOf(idEmpleado);

        //TODO cambiar la variable
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySWQiOiI2IiwiRW1wbG95ZWVJZCI6IjciLCJQcm9maWxlSW1hZ2VJZCI6IjEwMDc4IiwiRW1wbG95ZWVOdW1iZXIiOiI4ODg4OCIsIlVzZXJOYW1lIjoidXNyUGhvZW5peEFkbWluIiwiTmFtZSI6IkFkbWluaXN0cmFkb3IgU0dEIiwiRW1haWwiOiJqaG9uYXRoYW5AZ3BzcGhvZW5peC5jb20iLCJNb2JpbGVQaG9uZSI6IjU1NTU1NTU1NTUiLCJEYXRlT2ZCaXJ0aCI6IjEvMS8wMDAxIiwiQ2xpZW50cyI6IltdIiwiZXhwIjoxNjk5NTc3OTk2fQ.uS6asmgV5aeE9MfhwolApagW4Mwiy3L-OV4tQ7thx4Q";
        //TODO Cambiar por el operador correcto
        requestManifestV2 request = new requestManifestV2(idEmpleado);
        //TODO Cambiar por el token correcto
        Call<responseManifestV2> call = service.getManifestV2(token, "text/plain", idEmpleadoString);
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

                if(data!=null) {
                    presenter.setmanifestV2(data);
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

    @Override
    public void getmymanifestV2() {
        getAllmanifestV2();
    }
}
