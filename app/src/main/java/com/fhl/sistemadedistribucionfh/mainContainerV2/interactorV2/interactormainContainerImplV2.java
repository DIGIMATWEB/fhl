package com.fhl.sistemadedistribucionfh.mainContainerV2.interactorV2;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClienFH;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitValidations;
import com.fhl.sistemadedistribucionfh.login.model.modelProfile.profileResponse;
import com.fhl.sistemadedistribucionfh.mainContainerV2.modelV2.dataMenuItemsV2;
import com.fhl.sistemadedistribucionfh.mainContainerV2.modelV2.responseMenuItemsV2;
import com.fhl.sistemadedistribucionfh.mainContainerV2.presenterV2.presentermainContainerImplV2;
import com.fhl.sistemadedistribucionfh.mainContainerV2.utilV2.mainServiceV2;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class interactormainContainerImplV2 implements interactormainContainerV2 {
    private presentermainContainerImplV2 presenter;
    private Context context;
    private mainServiceV2 service;
    private Retrofit retrofitClient;

    public interactormainContainerImplV2(presentermainContainerImplV2 presenter, Context context) {
        this.context = context;
        this.presenter = presenter;
        retrofitClient = RetrofitClienFH.getRetrofitInstance();
        service = retrofitClient.create(mainServiceV2.class);
    }

    @Override
    public void getMenusV2() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        if(token!=null)
        {
            requestMenusV2(token);
        }
    }

    private void requestMenusV2(String token) {
        Gson gson = new Gson();
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token2 = preferences.getString(GeneralConstants.USER_VALUES, null);
        Log.e("TOKEN",""+token);
        profileResponse profileData = gson.fromJson(token2, profileResponse.class);

        //Id del Operador
        int idEmpleado = profileData.getUsuarioId();

        //Bearer token (no es un token normal)
        String token3 = "Bearer " + token;

        //Peticion Correcta
        Call<responseMenuItemsV2> call = service.getMenusV2(idEmpleado, 7, token3);

        //Peticion con el Operador Hardcode
        //Call<responseMenuItemsV2> call = service.getMenusV2(6, 7, token3);

        call.enqueue(new Callback<responseMenuItemsV2>() {
            @Override
            public void onResponse(Call<responseMenuItemsV2> call, Response<responseMenuItemsV2> response) {
                Log.d("Failure", "This is my message" + response.body());
                validateResponseV2(response, context);
            }

            @Override
            public void onFailure(Call<responseMenuItemsV2> call, Throwable t) {
                Log.d("Failure", "This is my message" + t.getMessage());
                Log.d("Failure", "This is my message" + call.clone());
                Toast.makeText(context, ""+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateResponseV2(Response<responseMenuItemsV2> response, Context context) {
        if (response!=null){
            if(RetrofitValidations.checkSuccessCode(response.code())) {
                if (response.isSuccessful()) {
                    getMenuV2(response, context);
                } else {
                    // Manejar caso de respuesta no exitosa
                    Log.d("Response", "Server response not successful. Code: " + response.code());
                    Toast.makeText(context, "Server response not successful", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, ""+ response.message(), Toast.LENGTH_SHORT).show();
                if(response.code() == 200) {
                    //Algo
                } else {
                    Toast.makeText(context, ""+ response.message(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void getMenuV2(Response<responseMenuItemsV2> response, Context context) {
        responseMenuItemsV2 resp = response.body();
        if(resp!=null){
            String message = resp.getMessage();
            int responseCode = resp.getStatus();

            if(responseCode == GeneralConstants.RESPONSE_CODE_OK_PEP) {
                try {
                    // Obtener la cadena JSON interna
                    String jsonString = response.body().getData();

                    // Parsear la cadena JSON a un JSONArray
                    JSONArray jsonArray = new JSONArray(jsonString);

                    // Crear una lista para almacenar los objetos dataMenuItemsV2
                    List<dataMenuItemsV2> menuItemsList = new ArrayList<>();

                    // Crear un objeto Gson para convertir JSON a objetos Java
                    Gson gson = new Gson();

                    // Iterar sobre el JSONArray y convertir cada objeto JSON a un objeto dataMenuItemsV2
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        dataMenuItemsV2 menuItem = gson.fromJson(jsonObject.toString(), dataMenuItemsV2.class);
                        menuItemsList.add(menuItem);
                    }

                    // Filtrar los elementos que tienen AplicacionId: 7
                    menuItemsList.removeIf(menuItemsV2 -> menuItemsV2.getAplicacionId() != 7);

                    // Filtrar los elementos que tienen Id: 54
                    menuItemsList.removeIf(menuItemsV2 -> menuItemsV2.getId() == 54);

                    // Filtrar los elementos que tienen Id: 55
                    menuItemsList.removeIf(menuItemsV2 -> menuItemsV2.getId() == 55);

                    // Ordenar la lista por el campo "Orden"
                    Collections.sort(menuItemsList);

                    // Ahora, menuItemsList contiene la lista de objetos dataMenuItemsV2
                    Log.d("Parsed Objects", "Menu Items List: " + menuItemsList.size());
                    //Log.d("Parsed Objects", "Menu Items List: " + menuItemsList.get(0).getTitulo());

                    // Continuar con el procesamiento de la respuesta
                    presenter.setMenusV2(menuItemsList);
                } catch (JSONException e) {
                    e.printStackTrace();
                    // Manejar el error de análisis JSON
                    Log.e("JSON Parsing Error", "Error parsing JSON: " + e.getMessage());
                    Toast.makeText(context, "Error parsing JSON", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, ""+ response.message(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, ""+ response.message(), Toast.LENGTH_SHORT).show();
        }
    }
}
