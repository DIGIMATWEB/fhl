package com.fhl.sistemadedistribucionfh.resguardo.interactor;

import android.content.Context;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitValidations;
import com.fhl.sistemadedistribucionfh.resguardo.model.dataResguardo;
import com.fhl.sistemadedistribucionfh.resguardo.model.requestResguardo;
import com.fhl.sistemadedistribucionfh.resguardo.model.responseResguardo;
import com.fhl.sistemadedistribucionfh.resguardo.presenter.presenterResguardo;
import com.fhl.sistemadedistribucionfh.resguardo.util.serviceResguardo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class resgiuardoInteractorImpl implements  resguardoInteractor{
      private Context context;
        private presenterResguardo presenter;
        private serviceResguardo service;
        private Retrofit retrofitClient;
    public resgiuardoInteractorImpl(presenterResguardo presenter, Context context){
        this.presenter=presenter;
        this.context=context;
//        retrofitClient = RetrofitClientNewlands.getRetrofitInstance();
//                service=retrofitClient.create(serviceResguardo.class);
    }
    @Override
    public void requestResguardo() {
        requestResguardo request=new requestResguardo("adsada");
//        Call<responseResguardo> call = service.getResguardo(request);
//        call.enqueue(new Callback<responseResguardo>() {
//            @Override
//            public void onResponse(Call<responseResguardo> call, Response<responseResguardo> response) {
//                validateResponseResguardo(response,context);
//            }
//
//            @Override
//            public void onFailure(Call<responseResguardo> call, Throwable t) {
//                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void validateResponseResguardo(Response<responseResguardo> response, Context context) {
        if (response != null) {

            if (RetrofitValidations.checkSuccessCode(response.code())) {
                getResguardo(response, context);
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getResguardo(Response<responseResguardo> response, Context context) {
        responseResguardo resp=response.body();
        if(resp!=null){
            String message = resp.getMessage();
            int responseCode = resp.getResconseCode();
            if(resp.getResconseCode()== GeneralConstants.RESPONSE_CODE_OK){
                List<dataResguardo> data=resp.getData();

                if(data!=null){
                    presenter.setResguardos(data);
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
}
