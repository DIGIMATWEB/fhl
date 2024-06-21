package com.fhl.sistemadedistribucionfh.login.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrifitClientSGD;
import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitClienFH;
import com.fhl.sistemadedistribucionfh.login.model.modelLogin.requestLogin;
import com.fhl.sistemadedistribucionfh.login.model.modelLogin.responseLogin;
import com.fhl.sistemadedistribucionfh.login.model.modelProfile.profileResponse;
import com.fhl.sistemadedistribucionfh.login.model.roles.responseUserRole;
import com.fhl.sistemadedistribucionfh.login.presenter.loginpresenter;
import com.fhl.sistemadedistribucionfh.login.presenter.loginpresenterImplementation;
import com.fhl.sistemadedistribucionfh.login.util.serviceLogin;
import com.fhl.sistemadedistribucionfh.login.util.serviceRole;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class loginInteractorImplementation implements loginInteractor{
    private Context context;
    private loginpresenter presenter;
    private serviceLogin service;
    private serviceRole service2;
    private Retrofit retrofitClient,retrofitClient2;
    private Boolean checkboxState = false;
    public loginInteractorImplementation(loginpresenterImplementation presenter, Context context) {
        this.context=context;
        this.presenter=presenter;

        retrofitClient = RetrofitClienFH.getRetrofitInstance();
        service=retrofitClient.create(serviceLogin.class);

        retrofitClient2= RetrifitClientSGD.getRetrofitInstance();
        service2=retrofitClient2.create(serviceRole.class);
    }

    @Override
    public void myrequestLogin(String user, String password, Boolean checkBoxState) {
        checkboxState = checkBoxState;
        getToken(user,password);
    }

    @Override
    public void requestProfileValues(String token) {
    Call< profileResponse> call=service.getData("Bearer "+token);
    call.enqueue(new Callback<profileResponse>() {
        @Override
        public void onResponse(Call<profileResponse> call, Response<profileResponse> response) {
            if(response.code()==200) {
                presenter.saveUserValues(response.body());
            }else {
                presenter.saveUserValues(null);
            }
        }

        @Override
        public void onFailure(Call<profileResponse> call, Throwable t) {
            presenter.FailureLogin(t.getMessage());
            presenter.saveUserValues(null);
        }
    });
    }



    private void getToken(String user, String password) {
        requestLogin request= new requestLogin(user,password);
        Call<responseLogin> call=service.getLogin(request);
        call.enqueue(new Callback<responseLogin>() {
            @Override
            public void onResponse(Call<responseLogin> call, Response<responseLogin> response) {
                if(response.code()==200){
                    presenter.continueWithoutSave(checkboxState);
                    presenter.saveToken(response.body().getToken());
                    Log.e("token",""+response.body().getToken());
                    presenter.succesLogin();
                }else{
                    presenter.FailureLogin(response.message());
                }
            }

            @Override
            public void onFailure(Call<responseLogin> call, Throwable t) {
                presenter.FailureLogin(t.getMessage());
            }
        });
    }
    @Override
    public void requestProfileRole(String token, String idEmpleadoString) {
    Call<responseUserRole> call=service2.getRole(token,Integer.valueOf(idEmpleadoString));
    call.enqueue(new Callback<responseUserRole>() {
        @Override
        public void onResponse(Call<responseUserRole> call, Response<responseUserRole> response) {
            if(response.code()==200){
                if(response.body()!=null){
                    if(response.body().getData()!=null){
                        if(response.body().getData().getTipoPerfilesId()!=null){
                            SharedPreferences preferencias = context.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferencias.edit();
                            editor.putString(GeneralConstants.USER_ROLE,String.valueOf(response.body().getData().getTipoPerfilesId()));
                            editor.commit();
                        }
                    }
                }
                presenter.setRole();
            }else{
                presenter.setRole();
            }
        }

        @Override
        public void onFailure(Call<responseUserRole> call, Throwable t) {
            presenter.setRole();
        }
    });
    }
}
