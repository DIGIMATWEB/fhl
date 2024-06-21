package com.fhl.sistemadedistribucionfh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;

import com.fhl.sistemadedistribucionfh.HelpPermissions.HelpPermissionsViewImpl;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.login.view.login;
import com.fhl.sistemadedistribucionfh.mainContainer.mainContainer;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout constrainReference;
    private int width;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        constrainReference=findViewById(R.id.mainViewConstrain);
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String savedWith = preferences.getString(GeneralConstants.WITH_USER, null);
        if(savedWith==null) {
            constrainReference.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    // Now the width should be initialized
                    width = constrainReference.getWidth();
                    Log.e("rvLayout", "W: " + width);

                    // After obtaining width, you can proceed with setting up the RecyclerView

                    SharedPreferences preferencias = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferencias.edit();
                    editor.putString(GeneralConstants.WITH_USER, String.valueOf(width));
                    editor.commit();
                    // Remove the listener to avoid redundant calls
                    constrainReference.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
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