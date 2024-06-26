package com.fhl.sistemadedistribucionfh.mainContainer.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.mainContainer.presenter.prensentermainContainerImpl;
import com.fhl.sistemadedistribucionfh.mainContainer.util.mainService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class interactormainContainerImpl implements interactormainContainer{
    private prensentermainContainerImpl presenter;
    private Context context;
    private mainService service;
    private Retrofit retrofitClient;
    public interactormainContainerImpl(prensentermainContainerImpl presenter, Context context) {
         this.context=context;
         this.presenter=presenter;
//                retrofitClient = RetrofitClientNewlands.getRetrofitInstance();
//                service=retrofitClient.create(mainService.class);
    }

    @Override
    public void getMenus() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        if(token!=null)
        {
            //requestMenus(token);
        }
    }
/*
    private void requestMenus(String token ) {
        requestMenuItems request=new requestMenuItems(token);
        Call<responseMenuItems> call= service.getMenus(request);
        call.enqueue(new Callback<responseMenuItems>() {
            @Override
            public void onResponse(Call<responseMenuItems> call, Response<responseMenuItems> response) {
                validateResponseCode(response,context);
            }

            @Override
            public void onFailure(Call<responseMenuItems> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateResponseCode(Response<responseMenuItems> response, Context context) {
        if (response != null) {
            if (RetrofitValidations.checkSuccessCode(response.code())) {
                getDataMenus(response, context);
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getDataMenus(Response<responseMenuItems> response, Context context) {
        responseMenuItems resp=response.body();
        if(resp!=null){
            int code= resp.getResconseCode();
            String message= resp.getMessage();
            List<dataMenuItems> data=resp.getData();
            if(code== GeneralConstants.RESPONSE_CODE_OK){
                if (data != null) {
                    Gson gson = new Gson();
                    String json = gson.toJson(data);
                    Log.e("respDatamenu",""+json);
                    presenter.setMenus(data);
                   // presenter.hideDialog();
                } else {
                   // presenter.hideDialog();
                    Toast.makeText(context, "Sin informacion de menús" , Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
        }

    }*/
}
