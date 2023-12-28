package com.fhl.sistemadedistribucionfh.login.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.login.model.modelProfile.profileResponse;
import com.fhl.sistemadedistribucionfh.login.presenter.loginpresenter;
import com.fhl.sistemadedistribucionfh.login.presenter.loginpresenterImplementation;
import com.fhl.sistemadedistribucionfh.mainContainer.mainContainer;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

public class login extends AppCompatActivity implements View.OnClickListener,loginview {
    private ImageButton login;
    private loginpresenter presenter;
    private TextInputEditText user,pass;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        login=findViewById(R.id.loginenter);
        login.setOnClickListener(this);
        user=findViewById(R.id.user);
        pass=findViewById(R.id.pass);
        user.setOnClickListener(this);
        pass.setOnClickListener(this);
        presenter=new loginpresenterImplementation(this,getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginenter:
                //mainContainer
                presenter.requestLogin(user.getText().toString(),pass.getText().toString());

                break;
        }
    }

    @Override
    public void succesLogin() {
        if(token!=null) {
            presenter.requestProfileValues(token);
        }
    }

    @Override
    public void failLogin(String message) {
        Toast.makeText(this, "Error al acceder "+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveToken(String token) {
        this.token=token;
        SharedPreferences preferencias=getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString(GeneralConstants.TOKEN, token);
        editor.putString(GeneralConstants.STATUS_SALIDA,"1");
        editor.commit();
    }

    @Override
    public void saveUserValues(profileResponse body) {
        if(body!=null) {
            Gson gson = new Gson();
            String jsonStringProfileData = gson.toJson(body);
            SharedPreferences preferencias = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();
            editor.putString(GeneralConstants.USER_VALUES, jsonStringProfileData);
            editor.commit();
            Log.e("jsonProfile","profileData "+ jsonStringProfileData);
        }else{
            Toast.makeText(this, "No se guardaron datos del usuario", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(this, mainContainer.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP);//
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
