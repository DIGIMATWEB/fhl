package com.companyname.mauitest.checkList.interactor;

import android.content.Context;
import android.widget.Toast;

import com.companyname.mauitest.Retrofit.GeneralConstants;
import com.companyname.mauitest.Retrofit.RetrofitClientNewlands;
import com.companyname.mauitest.Retrofit.RetrofitValidations;
import com.companyname.mauitest.checkList.model.dataChecklist;
import com.companyname.mauitest.checkList.model.requestChecklist;
import com.companyname.mauitest.checkList.model.responseChecklist;
import com.companyname.mauitest.checkList.presenter.checkListPresenterImpl;
import com.companyname.mauitest.checkList.presenter.checklistPresenter;
import com.companyname.mauitest.checkList.util.serviceChecklist;

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
        retrofitClient = RetrofitClientNewlands.getRetrofitInstance();
        service=retrofitClient.create(serviceChecklist.class);

    }

    @Override
    public void requestChecklist() {
        requestChecklist request= new requestChecklist("asfasfaesweqwf");
        Call<responseChecklist> call= service.getChecklist(request);
        call.enqueue(new Callback<responseChecklist>() {
            @Override
            public void onResponse(Call<responseChecklist> call, Response<responseChecklist> response) {
                validateResponseChecklist(response,context);
            }

            @Override
            public void onFailure(Call<responseChecklist> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void validateResponseChecklist(Response<responseChecklist> response, Context context) {
        if (response != null) {

            if (RetrofitValidations.checkSuccessCode(response.code())) {
                getChecklist(response, context);
            } else {
                Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getChecklist(Response<responseChecklist> response, Context context) {
        responseChecklist resp=response.body();
                        if(resp!=null){
                            String message = resp.getMessage();
                            int responseCode = resp.getResconseCode();
                           if(resp.getResconseCode()== GeneralConstants.RESPONSE_CODE_OK){
                                List<dataChecklist> data=resp.getData();

                                if(data!=null){
                                    presenter.setChecklist(data);
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
