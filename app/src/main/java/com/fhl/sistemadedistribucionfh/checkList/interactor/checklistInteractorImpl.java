package com.fhl.sistemadedistribucionfh.checkList.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientNewlands;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientPep2;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitValidations;
import com.fhl.sistemadedistribucionfh.checkList.model.v1.dataChecklist;
import com.fhl.sistemadedistribucionfh.checkList.model.v1.requestChecklist;
import com.fhl.sistemadedistribucionfh.checkList.model.v1.responseChecklist;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.VehiculoVsCheck;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.dataChecklistV2;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.responseChecklistV2;
import com.fhl.sistemadedistribucionfh.checkList.presenter.checklistPresenter;
import com.fhl.sistemadedistribucionfh.checkList.util.serviceChecklist;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class checklistInteractorImpl implements checklistInteractor{
    private Context context;
    private  checklistPresenter presenter;
    private serviceChecklist service;
    private Retrofit retrofitClient;
    public checklistInteractorImpl(checklistPresenter presenter, Context context) {
        this.presenter=presenter;
        this.context=context;
        retrofitClient = RetrofitClientPep2.getRetrofitInstance();
        service=retrofitClient.create(serviceChecklist.class);

    }

    @Override
    public void requestChecklist() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
       // requestChecklist request= new requestChecklist("asfasfaesweqwf");
        Call<responseChecklistV2> call= service.getChecklist(token,6);
        call.enqueue(new Callback<responseChecklistV2>() {
            @Override
            public void onResponse(Call<responseChecklistV2> call, Response<responseChecklistV2> response) {
                validateResponseChecklist(response,context);
            }

            @Override
            public void onFailure(Call<responseChecklistV2> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
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
        responseChecklistV2 resp=response.body();
                     if(resp!=null){
                            String message = resp.getMessage();
                            int responseCode = resp.getStatus();
                           if(resp.getStatus()== GeneralConstants.RESPONSE_CODE_OK_PEP){
                              dataChecklistV2 mdata=resp.getData();
                                if(mdata!=null){
                                    List<VehiculoVsCheck> data=   mdata.getVehiculoVsChecklist();
                                    if(data!=null){
                                     presenter.setChecklist(mdata);
                                    }else{
                                        Toast.makeText(context, "sin tickets asignados", Toast.LENGTH_SHORT).show();
                                    }
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
