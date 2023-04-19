package com.companyname.mauitest.mainContainer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.companyname.mauitest.R;

public class tabBar extends Fragment implements View.OnClickListener {
    public static final String TAG = tabBar.class.getSimpleName();
    private  ImageView menuButon;
    private mainContainer mactivity;
    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabbar, container, false);
        mactivity= (mainContainer) getActivity();
        initView(view);
        return view;
    }

    private void initView(View view) {
        menuButon=view.findViewById(R.id.menuButon);
        menuButon.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menuButon:
                mactivity.action();
                break;
        }
    }
}
