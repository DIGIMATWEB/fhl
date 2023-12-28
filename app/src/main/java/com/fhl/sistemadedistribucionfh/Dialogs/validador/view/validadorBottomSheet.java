package com.fhl.sistemadedistribucionfh.Dialogs.validador.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import com.fhl.sistemadedistribucionfh.Dialogs.dialogCompletedespacho;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.model.dataValidador;
import com.fhl.sistemadedistribucionfh.Dialogs.validador.presenter.presenterValidador;
import com.fhl.sistemadedistribucionfh.Dialogs.vehicleBottomSheet;
import com.fhl.sistemadedistribucionfh.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.List;

public class validadorBottomSheet extends DialogFragment implements View.OnClickListener ,validadorView {
    public static final String TAG = validadorBottomSheet.class.getSimpleName();
    private Button iralmenu;
    private TextView titleheader;
    private ImageView imagebackground;
    private ConstraintLayout vehicle,user,manifest;
    private String codigoValidador;
    private presenterValidador presenter;
    private ConstraintLayout bottomSheet;
    private BottomSheetBehavior bottomSheetBehavior;
    private ConstraintLayout bottomStatusVehicle,bottomStatusDriver,bottomStatusManifest;
    private ImageButton buttonCancelDriver,buttonCancelCar,buttonCancelManifest;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_boonsheet, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.customTransparent);
        Bundle args = getArguments();
        if (args != null) {
            codigoValidador= args.getString("validadorCode");
        }
        Log.e("","");
        setCancelable(true);
        initDialog(view);
        //setFonts();
        return view;
    }

    private void initDialog(View view) {
        titleheader=view.findViewById(R.id.titleheader);
        imagebackground=view.findViewById(R.id.imagebackground);
        vehicle=view.findViewById(R.id.carconstrain);
        user=view.findViewById(R.id.driverconstrain);
        manifest=view.findViewById(R.id.manifestconstrain);
        bottomSheet=view.findViewById(R.id.bottomSheetZones);
        bottomStatusVehicle =view.findViewById(R.id.bottomStatusVehicle);
        bottomStatusVehicle.setOnClickListener(this);
        bottomStatusDriver=view.findViewById(R.id.bottomStatusDriver);
        bottomStatusDriver.setOnClickListener(this);
        bottomStatusManifest=view.findViewById(R.id.bottomStatusManifest);
        bottomStatusManifest.setOnClickListener(this);
        buttonCancelDriver=view.findViewById(R.id.buttonCancelDriver);
        buttonCancelCar =view.findViewById(R.id.buttonCancelCar);
        buttonCancelManifest=view.findViewById(R.id.buttonCancelManifest);
        buttonCancelDriver.setOnClickListener(this);
        buttonCancelCar.setOnClickListener(this);
        buttonCancelManifest.setOnClickListener(this);
        Drawable ndrawable= getActivity().getDrawable(R.drawable.temp_botton_sheet);
        imagebackground.setBackground(ndrawable);
        bottomSheetSettings();

        presenter=new presenterValidador(this,getContext());
        presenter.requestDespacho(codigoValidador);
    }

    private void bottomSheetSettings() {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        bottomSheet.setVisibility(View.GONE);
                        closeDialog();
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        break;

                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    public void closeDialog() {
        this.dismiss();

    }

    @Override
    public void setCode(List<dataValidador> data) {

        if(data.get(0).getDriverId().equals(codigoValidador)){//todo conductor  d1234567
                titleheader.setText("Conductor");
                vehicle.setVisibility(View.GONE);
                user.setVisibility(View.VISIBLE);
                manifest.setVisibility(View.GONE);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
        else if(data.get(0).getManifestId().equals(codigoValidador)){//todo manifiesto  m12345678
                titleheader.setText("manifiesto");
                vehicle.setVisibility(View.GONE);
                user.setVisibility(View.GONE);
                manifest.setVisibility(View.VISIBLE);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
        else if( data.get(0).getUnitId().equals(codigoValidador)){//todo vehiculo   v123456
                titleheader.setText("vehiculo");
                vehicle.setVisibility(View.VISIBLE);
                user.setVisibility(View.GONE);
                manifest.setVisibility(View.GONE);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }else{
            Toast.makeText(getContext(), "el codigo no corresponde", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void errorValidador() {
        new errorValidador().show(getActivity().getSupportFragmentManager(),"errorValidador");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bottomStatusVehicle:
                //todo presente updateStatusVehicleindespacho
                break;
            case R.id.bottomStatusDriver:
                //todo presente updateStatusConductorindespacho  usando el id del despacho
                break;
            case R.id.bottomStatusManifest:
                //todo presente updateStatusManifiestoindespacho
                new dialogCompletedespacho().show(getActivity().getSupportFragmentManager(),"dialogCompletedespacho");
            break ;
            case R.id.buttonCancelManifest:
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                closeDialog();
                break;
            case R.id.buttonCancelCar:
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case R.id.buttonCancelDriver:
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;

        }
    }

}
