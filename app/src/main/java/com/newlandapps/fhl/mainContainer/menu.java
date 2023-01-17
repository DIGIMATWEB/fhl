package com.newlandapps.fhl.mainContainer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.newlandapps.fhl.R;
import com.newlandapps.fhl.nmanifest.mmanifest;

public class menu extends Fragment implements View.OnClickListener{
    public static final String TAG = menu.class.getSimpleName();
    private ImageView mainM,mpedido,miscompras,Mordenes;
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

        mainM.setOnClickListener(this);
        mpedido.setOnClickListener(this);
        miscompras.setOnClickListener(this);
        Mordenes.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mainM:
                manager = getActivity().getSupportFragmentManager();
                transaction = manager.beginTransaction();
                mmanifest manifest= new mmanifest();
                transaction.replace(R.id.fragments, manifest, mmanifest.TAG).commit();
                break;
            case R.id.mpedido:
                Toast.makeText(getContext(), "ir a completados", Toast.LENGTH_SHORT).show();
                break;
            case R.id.miscompras:
                Toast.makeText(getContext(), "localizador", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Mordenes:
                Toast.makeText(getContext(), "ir a Validador", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
