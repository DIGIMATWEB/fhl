package com.fhl.sistemadedistribucionfh.Profile.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.login.model.modelProfile.profileResponse;
import com.fhl.sistemadedistribucionfh.login.view.login;
import com.google.gson.Gson;

public class viewProfile extends Fragment implements View.OnClickListener {
    public static final String TAG = viewProfile.class.getSimpleName();
    private ConstraintLayout logout;
    private Bundle mainbundle;
    private TextView nombre,correoElectronico,telefono;
    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mainbundle=savedInstanceState;
        initView(view);
        return view;
    }

    private void initView(View view) {
        logout=view.findViewById(R.id.logout);
        logout.setOnClickListener(this);

        nombre=view.findViewById(R.id.textView34);
        correoElectronico=view.findViewById(R.id.email);
        telefono=view.findViewById(R.id.telefono);

        setValues();
    }

    private void setValues() {
        SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String profileValues = preferences.getString(GeneralConstants.USER_VALUES, null);
        if(profileValues!=null)
        {
            Gson gson = new Gson();
            profileResponse profileData = gson.fromJson(profileValues, profileResponse.class);
            nombre.setText( profileData.getNombre());
            correoElectronico.setText(profileData.getCorreoElectronico());
            telefono.setText(profileData.getTelefonoMovil());
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.logout:
                SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.clear();
                editor.apply();
                //preferences.edit().clear().commit();

                Intent intent = new Intent(getActivity(), login.class);
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    extras.clear();
                    mainbundle.clear();
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }
}
