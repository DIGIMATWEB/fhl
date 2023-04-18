package com.companyname.mauitest.login.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.companyname.mauitest.R;
import com.companyname.mauitest.Retrofit.GeneralConstants;
import com.companyname.mauitest.login.presenter.loginpresenter;
import com.companyname.mauitest.login.presenter.loginpresenterImplementation;
import com.companyname.mauitest.mainContainer.mainContainer;
import com.google.android.material.textfield.TextInputEditText;

public class login extends AppCompatActivity implements View.OnClickListener,loginview {
    private ImageButton login;
    private loginpresenter presenter;
    private TextInputEditText user,pass;
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
        Intent intent = new Intent(this, mainContainer.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP);//
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void failLogin(String message) {
        Toast.makeText(this, "Error al acceder "+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveToken(String token) {
        SharedPreferences preferencias=getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString(GeneralConstants.TOKEN, token);
        editor.commit();
    }
}
