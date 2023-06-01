package com.fhl.sistemadedistribucionfh.HelpPermissions;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import com.fhl.sistemadedistribucionfh.MainActivity;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.login.view.login;

public class HelpPermissionsViewImpl extends AppCompatActivity implements View.OnClickListener {

    private ActivityResultContracts.RequestMultiplePermissions RequestPermissionLauncher;
    ActivityResultLauncher<Intent> activityResultLauncher;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1001;
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1002;
    private static final int MY_PERMISSIONS_REQUEST_INTERNET = 1003;

    Button btnPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_permissions);

        //initRequestPermissions();
        btnPermissions = findViewById(R.id.buttonPermissions);
        btnPermissions.setOnClickListener(this);

        getShared();
    }

    public void getShared() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String permissionAccess = preferences.getString(GeneralConstants.PERMISSION_ACCESS, null);

        if (permissionAccess != null) {
            goToLoginContainer();
        } else {
            //RequestPermissions();
            //goToLoginContainer();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonPermissions:
                RequestPermissions();
                //permissionGranted();
                break;
        }
    }

    //Esto es de Kotlin
    private void initRequestPermissions() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){

            } else {
                //activityResultLauncher.launch(Manifest.permission.CAMERA);
            }
        }
    }

    private void RequestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Request the permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);

        } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Request the permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CAMERA);
        } else {
            // Permission has already been granted
            // Access the location data
            // ...
            goToLoginContainer();

            String permisos = "true";
            SharedPreferences preference = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preference.edit();
            editor.putString(GeneralConstants.PERMISSION_ACCESS, permisos);
            editor.commit();
            Toast.makeText(this, "Todos los permisos.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(this, "Permiso de Ubicacion concedido", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);
            } else {
                //Advertencia de permisos
                //Toast.makeText(this, "Permisos de Ubicacion no concedidos", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Se necesitan los permisos para el funcionamiento de la aplicacion.", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == MY_PERMISSIONS_REQUEST_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(this, "Permiso de Camara concedido", Toast.LENGTH_SHORT).show();
                RequestPermissions();
            } else {
                //Advertencia de permisos
                //Toast.makeText(this, "Permisos de Camara no concedidos", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Se necesitan los permisos para el funcionamiento de la aplicacion.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    private void permissionGranted() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Request the permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Request the permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CAMERA);
        } else {
            // Permission has already been granted
            // Access the location data
            // ...
            goToLoginContainer();

            String permisos = "true";
            SharedPreferences preference = getApplicationContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preference.edit();
            editor.putString(GeneralConstants.PERMISSION_ACCESS, permisos);
            editor.commit();
            Toast.makeText(this, "Todos los permisos.", Toast.LENGTH_SHORT).show();
        }
    }

    private void goToLoginContainer() {
        Intent intent = new Intent(HelpPermissionsViewImpl.this, login.class);
        startActivity(intent);
        finish();
    }
}
