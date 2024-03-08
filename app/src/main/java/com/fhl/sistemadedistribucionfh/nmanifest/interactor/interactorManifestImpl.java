package com.fhl.sistemadedistribucionfh.nmanifest.interactor;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientNewlands;
import com.fhl.sistemadedistribucionfh.nmanifest.presenter.presentermanifest;
import com.fhl.sistemadedistribucionfh.nmanifest.util.manifestUtil;

import retrofit2.Retrofit;

public class interactorManifestImpl  implements  interactorManifest{
    private Context context;
    private presentermanifest presenter;
    private manifestUtil service;
    private Retrofit retrofitClient;

    public interactorManifestImpl(presentermanifest presenter, Context context) {
        this.context=context;
        this.presenter=presenter;
        retrofitClient = RetrofitClientNewlands.getRetrofitInstance();
        service=retrofitClient.create(manifestUtil.class);
    }
/*
    public void getAllmanifest() {
        requestManifest request=new requestManifest("6a85c5ed4f3f3a17e4983a66ca0b42a8");
        Call<responseManifest> call=service.getManifest(request);
        call.enqueue(new Callback<responseManifest>() {
            @Override
            public void onResponse(Call<responseManifest> call, Response<responseManifest> response) {
                validateResponse(response,context);
            }

            @Override
            public void onFailure(Call<responseManifest> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateResponse(Response<responseManifest> response, Context context) {
        if (response != null) {

            if (RetrofitValidations.checkSuccessCode(response.code())) {
                getManifest(response, context);
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getManifest(Response<responseManifest> response, Context context) {
        responseManifest resp=response.body();
                if(resp!=null){
                    String message = resp.getMessage();
                    int responseCode = resp.getResconseCode();
                   if(resp.getResconseCode()== GeneralConstants.RESPONSE_CODE_OK){
                        List<dataManifest> data=resp.getData();

                        if(data!=null){
                            presenter.setmanifest(data);
                        }else{
                            Toast.makeText(context, "sin tickets asignados", Toast.LENGTH_SHORT).show();
                       }
                    }else{
                        Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
                   }

            } else{
                    Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
                }
    }
*/
    @Override
    public void getmymanifest() {
        //getAllmanifest();
    }
}
