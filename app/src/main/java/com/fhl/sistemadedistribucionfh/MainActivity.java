package com.fhl.sistemadedistribucionfh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.login.view.login;
import com.fhl.sistemadedistribucionfh.mainContainer.mainContainer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        continueSplash();
    }
    private void continueSplash()
    {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if(token!=null)
                {
                    tokenExist();
                }else{
                    goToLoginContainer();
                }

            }
        },3000);
    }

    private void goToLoginContainer() {
        Intent intent = new Intent(MainActivity.this, login.class);
        startActivity(intent);
        finish();

    }
    public void tokenExist() {
        Intent intent = new Intent(this, mainContainer.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP);//

        startActivity(intent);
    }
}