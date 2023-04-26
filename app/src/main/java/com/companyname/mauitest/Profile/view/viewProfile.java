package com.companyname.mauitest.Profile.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.companyname.mauitest.R;
import com.companyname.mauitest.Retrofit.GeneralConstants;
import com.companyname.mauitest.login.view.login;
import com.companyname.mauitest.nmanifest.mmanifest;

public class viewProfile extends Fragment implements View.OnClickListener {
    public static final String TAG = viewProfile.class.getSimpleName();
    private ConstraintLayout logout;
    private Bundle mainbundle;
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
