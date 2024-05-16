package com.fhl.sistemadedistribucionfh.Dialogs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.fhl.sistemadedistribucionfh.Dialogs.habilities.presenter.presenterHabilidades;
import com.fhl.sistemadedistribucionfh.Dialogs.habilities.view.dialogCompleteValidadorView;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.presenter.presenterSetValidacion;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.presenter.presenterSetValidacionImpl;
import com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.view.viewSetValidacion;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.mainContainer.mainContainer;

public class dialogCompleteValidador extends DialogFragment implements View.OnClickListener, viewSetValidacion  {
public static final String TAG = dialogCompleteValidador.class.getSimpleName();
private ImageButton imageButton2;
private presenterSetValidacion presentador;
private String manifest;
@Override
public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);
        }

@Nullable
@Override
public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_despacho_validador, container, false);
        //getDialog().getWindow().setBackgroundDrawableResource(R.color.customTransparent);
        setCancelable(true);
        Bundle args = getArguments();
        if (args != null) {
                manifest = args.getString("currentManifest");
        }
        initDialog(view);

        //setFonts();
        return view;
        }

private void initDialog(View view) {
        imageButton2=view.findViewById(R.id.imageButtonValidador);
        imageButton2.setOnClickListener(this);
        presentador= new presenterSetValidacionImpl(this,getContext());
        presentador.getdriverHabilities();
        presentador.getVehicleHabilities();
        }

public void closeDialog() {
        this.dismiss();

        }

        @Override
        public void statusValidacion(String code) {
                if(code.equals("105")){
                        Intent intent = new Intent(getContext(), mainContainer.class);
                        startActivity(intent);
                        // Close the dialog if needed
                        closeDialog();
                        Log.e("Validador", "ir a manifiestos");
                }else{
                        Toast.makeText(getContext(), ""+code, Toast.LENGTH_SHORT).show();
                }

        }

        @Override
        public void setDriverHailities(String habilidades) {
                Log.e("habilidades","Operador "+habilidades);
        }

        @Override
        public void setVehicleHailities(String habilidadVehiculos) {
                Log.e("habilidades","Driver "+habilidadVehiculos);
        }


        @Override
public void onClick(View view) {
        switch (view.getId()) {
        case R.id.imageButtonValidador:
        //closeDialog();
                Toast.makeText(getContext(), "Validador pendiente hailidades", Toast.LENGTH_SHORT).show();
              //presentador.setValidacionMenifest(manifest);

        break;
        }
        }

}