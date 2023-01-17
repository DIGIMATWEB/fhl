package com.newlandapps.fhl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.newlandapps.fhl.login.login;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        continueSplash();
    }
    private void continueSplash()
    {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                    goToLoginContainer();

            }
        },3000);
    }

    private void goToLoginContainer() {
        Intent intent = new Intent(MainActivity.this, login.class);
        startActivity(intent);
        finish();

    }
}