package com.fhl.sistemadedistribucionfh.Dialogs.validador;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.escanearCodigosSalida;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.adapterValidadorList;
import com.fhl.sistemadedistribucionfh.R;

public class escanearValidador extends DialogFragment implements View.OnClickListener {
    public static final String TAG = escanearValidador.class.getSimpleName();
    private Button iralmenu;
    private TextView titleheader;
    private ImageView imagebackground;
    private ConstraintLayout continuarbutton;
    private RecyclerView rv;
    private adapterValidadorList adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_escancodes_validador, container, false);
        //getDialog().getWindow().setBackgroundDrawableResource(R.color.customTransparent);
        setCancelable(true);
        initDialog(view);
        fillAdapter();
        //setFonts();
        return view;
    }


    private void initDialog(View view) {
        continuarbutton=view.findViewById(R.id.continuarbutton);
        continuarbutton.setOnClickListener(this);
        rv=view.findViewById(R.id. rvInstructionsValidador);
    }

    private void fillAdapter() {
            adapter = new adapterValidadorList( getContext());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                rv.setLayoutManager(linearLayoutManager);
                rv.setAdapter(adapter);
    }
    public void closeDialog() {
        this.dismiss();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.continuarbutton:
                closeDialog();
                break;
        }
    }
}
