package com.fhl.sistemadedistribucionfh.mainContainer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fhl.sistemadedistribucionfh.Profile.view.viewProfile;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.checkList.view.checkList;
import com.fhl.sistemadedistribucionfh.locator.view.locator;
import com.fhl.sistemadedistribucionfh.mlkit.BarcodeScannerActivity;
import com.fhl.sistemadedistribucionfh.nmanifest.viewV2.mmanifestV2;

public class menu extends Fragment implements View.OnClickListener{
    public static final String TAG = menu.class.getSimpleName();
    private ImageView mainM,mpedido,miscompras,Mordenes,mprofile;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mainM=view.findViewById(R.id.mainM);
        mpedido=view.findViewById(R.id.mpedido);
        miscompras=view.findViewById(R.id.miscompras);
        Mordenes=view.findViewById(R.id.Mordenes);
        mprofile=view.findViewById(R.id.mprofile);
        mainM.setOnClickListener(this);
        mpedido.setOnClickListener(this);
        miscompras.setOnClickListener(this);
        Mordenes.setOnClickListener(this);
        mprofile.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mainM:
                manager = getActivity().getSupportFragmentManager();
                transaction = manager.beginTransaction();
                /*mmanifest manifest= new mmanifest();
                transaction.replace(R.id.fragments, manifest, mmanifest.TAG).commit();*/
                mmanifestV2 manifestV2= new mmanifestV2();
                transaction.replace(R.id.fragments, manifestV2, mmanifestV2.TAG).commit();
                break;
            case R.id.mpedido:
               // Toast.makeText(getContext(), "ir a completados", Toast.LENGTH_SHORT).show();
//                Intent intent2 = new Intent(getActivity(), signature.class);//evidencia
//                startActivity(intent2);
                manager = getActivity().getSupportFragmentManager();
                transaction = manager.beginTransaction();
                checkList check=new checkList();
                transaction.replace(R.id.fragments, check,checkList.TAG).commit();
                break;
            case R.id.miscompras:
                /*Toast.makeText(getContext(), "localizador", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(getActivity(), locator.class);
                startActivity(intent3);*/
                manager = getActivity().getSupportFragmentManager();
                transaction = manager.beginTransaction();
                locator loc = new locator();
                transaction.replace(R.id.fragments, loc, locator.TAG).commit();
                break;
            case R.id.Mordenes:
                SharedPreferences preferences =getContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString(GeneralConstants.STATUS_SALIDA,String.valueOf(1));
                editor.commit();
                Bundle bundle = new Bundle();
                bundle.putString("scannerType", "Validador");
                Intent intent = new Intent(getActivity(), BarcodeScannerActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

                break;
            case R.id.mprofile:
                manager = getActivity().getSupportFragmentManager();
                transaction = manager.beginTransaction();
                viewProfile profile= new viewProfile();
                transaction.replace(R.id.fragments, profile, viewProfile.TAG).commit();
                break;
        }
    }
}
