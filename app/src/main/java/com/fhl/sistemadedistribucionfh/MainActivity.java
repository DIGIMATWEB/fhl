package com.fhl.sistemadedistribucionfh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.fhl.sistemadedistribucionfh.HelpPermissions.HelpPermissionsViewImpl;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.login.view.login;
import com.fhl.sistemadedistribucionfh.mainContainer.mainContainer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        continueSplash();
    }
    private void continueSplash()
    {
        SharedPreferences preferencesCheck = getApplicationContext().getSharedPreferences(GeneralConstants.CHECK_BOX_STATE, Context.MODE_PRIVATE);
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstants.TOKEN, null);
        String checkBoxState = preferencesCheck.getString(GeneralConstants.CHECK_BOX_STATE, null);
        Log.e("Cierre", "Se esta cerrando. " + checkBoxState);
        Log.e("TOKEN",""+token);

        /*if (checkBoxState!=null) {

        } else {
            checkBoxState = "false";
        }*/

        /*if (checkBoxState.equals("false")) {
            //SharedPreferences preferences = this.getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            SharedPreferences.Editor editor2 = preferencesCheck.edit();
            editor2.clear();
            editor.clear();
            editor.apply();
            editor2.apply();
            //preferences.edit().clear().commit();

            /*Intent intent = new Intent(this, login.class);
            Bundle extras = intent.getExtras();
            if (extras != null) {
                extras.clear();
                //mainbundle.clear();
            }*/
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //startActivity(intent);
        /*} else {
            //Nada
        }*/

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if(token!=null) {
                    if (checkBoxState.equals("false")) {
                        permissionsScreen();
                    } else {
                        tokenExist();
                    }
                }else{
                    //goToLoginContainer();
                    permissionsScreen();
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

    public void permissionsScreen() {
        Intent intent = new Intent(this, HelpPermissionsViewImpl.class);
        startActivity(intent);
        finish();;
    }
}