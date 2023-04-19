package com.companyname.mauitest.Profile.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.companyname.mauitest.R;
import com.companyname.mauitest.nmanifest.mmanifest;

public class viewProfile extends Fragment {
    public static final String TAG = viewProfile.class.getSimpleName();
    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
    }
}
