package com.fhl.sistemadedistribucionfh.mainContainer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.Profile.view.viewProfile;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.checkList.view.checkList;
import com.fhl.sistemadedistribucionfh.locator.view.locator;
import com.fhl.sistemadedistribucionfh.mainContainer.adapterMenu.adapterBottomMenu;
import com.fhl.sistemadedistribucionfh.mainContainerV2.modelV2.dataMenuItemsV2;
import com.fhl.sistemadedistribucionfh.mlkit.BarcodeScannerActivity;
import com.fhl.sistemadedistribucionfh.nmanifest.viewV2.mmanifestV2;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class menuV2 extends Fragment {
    public static final String TAG = menuV2.class.getSimpleName();
    private ImageView mainM,mpedido,miscompras,Mordenes,mprofile;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private RecyclerView rv;
    private adapterBottomMenu adapter;
    private mainContainer mactivity;
    private ConstraintLayout constrainReference;
    private int width;
    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_v2, container, false);

        mactivity= (mainContainer) getActivity();
        initView(view);
        return view;
    }

    private void initView(View view) {
        constrainReference=view.findViewById(R.id. constrainReference);
        width = constrainReference.getLayoutParams().width;
        rv=view.findViewById(R.id.rvHM);
        constrainReference.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Now the width should be initialized
                width = constrainReference.getWidth();
                Log.e("rvLayout", "W: " + width);

                // After obtaining width, you can proceed with setting up the RecyclerView
                SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                String role = preferences.getString(GeneralConstants.MENU_USER_SET, null);
                if (role != null) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<ArrayList<dataMenuItemsV2>>() {}.getType();
                    List<dataMenuItemsV2> menuList = gson.fromJson(role, type);
                    setMenu(menuList);
                }

                // Remove the listener to avoid redundant calls
                constrainReference.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void setMenu(List<dataMenuItemsV2> dataV2) {
        Log.e("rvLayout","W: "+width);
        adapter = new adapterBottomMenu(this, dataV2, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(linearLayoutManager);
        int aproxVal=0;
        if(dataV2.size()<4) {
             aproxVal = (dataV2.size() + 1) * 2;
             if(dataV2.size()!=2) {
                 if (aproxVal % 2 == 0) {
                     aproxVal += 1; // Increment if `aproxVal` is even
                 }
             }
        }else{
             aproxVal = (4 + 1) * 2;
        }
        int value=Math.round(width/(aproxVal));
        Log.e("rvLayout","Wf: "+(value));
        rv.addItemDecoration(new LinearSpacingItemDecoration(dpToPx(value))); // Add spacing between items    dpToPx(rv.getLayoutParams().width/dataV2.size()
        rv.setAdapter(adapter);
    }

    private int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
    public void invokeFragment(String menu) {
        mactivity.chooseFragment(menu);
    }
}