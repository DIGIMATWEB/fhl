package com.companyname.mauitest.signature;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.companyname.mauitest.R;

public class signature extends AppCompatActivity {
    public static final String TAG = signature.class.getSimpleName();
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);
    }
}