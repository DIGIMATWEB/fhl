package com.fhl.sistemadedistribucionfh.Dialogs.Reasons.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.model.requestReasons;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.model.responseReasons;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.presenter.dialogReasonsPresenter;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.presenter.dialogReasonsPresenterImpl;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.util.serviceDialogReasons;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClientNewlands;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class dialogReasonsInteractorImpl implements dialogReasonsInteractor {
    private Context context;
    private  dialogReasonsPresenter presenter;
    private Retrofit retrofit;
    private serviceDialogReasons service;

    public dialogReasonsInteractorImpl(dialogReasonsPresenter presenter, Context context) {
        this.presenter=presenter;
        this.context=context;
        retrofit= RetrofitClientNewlands.getRetrofitInstance();
        service= retrofit.create(serviceDialogReasons.class);
    }

    @Override
    public void requestMReasons() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        requestReasons request= new requestReasons(token);
        Call<responseReasons> call= service.getReasons(request);
        call.enqueue(new Callback<responseReasons>() {
            @Override
            public void onResponse(Call<responseReasons> call, Response<responseReasons> response) {
                if(response.code()==200) {
                    if (response.body().getResconseCode() == 105) {
                        if(response.body().getData()!=null){
                            presenter.setReasons(response.body().getData());
                        }else{
                            Toast.makeText(context, "No hay lista de razones", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(context, ""+response.message()+":"+response.code(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, ""+response.message()+":"+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<responseReasons> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
