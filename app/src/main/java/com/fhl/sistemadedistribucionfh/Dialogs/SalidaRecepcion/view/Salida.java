package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.view;

import android.content.DialogInterface;
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
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.presenter.salidaViewPresenter;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.presenter.salidaViewPresenterImplements;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.mlkit.BarcodeScannerActivity;
import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.dataManifestV2;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.List;

public class Salida extends DialogFragment implements View.OnClickListener, salidaView {
    //todo nota esto debe verificarse
    public static final String TAG = Salida.class.getSimpleName();
    private salidaViewPresenter presenter;
    private BottomSheetBehavior bottomSheetBehavior;
    private ConstraintLayout bottomSheet;
    private String codigoValidador,codigoValidador1;
    private ImageButton imageButton;
    private Button clear;
    private ImageView imageView24;
    private TextView textView23,textView29;
    private CardView gonext;
    private List<dataManifestV2> data;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_salida_manifest, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.customTransparent2);
        setCancelable(true);
        Bundle args = getArguments();
        if (args != null) {
            codigoValidador= args.getString("qrCode");
            codigoValidador1= args.getString("statusRecepcion");
        }
        initDialog(view);
        setUpDialog(codigoValidador1);
        //setFonts();
        return view;
    }

    private void setUpDialog(String codigoValidador1) {
        switch (codigoValidador1) {
            case "1":
                textView23.setText("siguiente paso");
                textView29.setText("escanear codigo de la cortina");
                presenter.requestManifest(codigoValidador);
                break;
            case "2":
                textView23.setText("siguiente paso");
                textView29.setText("escanea el codigo de los tickets");
                break;
            case "3":
                textView23.setText("siguiente paso");
                textView29.setText("escanea el codigo de los sellos");
                break;
            case "4":
                textView23.setVisibility(View.GONE);
                textView29.setText("Resumen");
                break;
        }
    }

    private void initDialog(View view) {
        // presenter= new dialogReasonsPresenterImpl(this,getContext());
        imageButton=view.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(this);
        clear=view.findViewById(R.id.clear);
        clear.setOnClickListener(this);
        imageView24 =view.findViewById(R.id.imageView24);
        textView23=view.findViewById(R.id.textView23);
        textView29=view.findViewById(R.id.textView29);

        gonext=view.findViewById(R.id.gonext);
        gonext.setOnClickListener(this);

        bottomSheet=view.findViewById(R.id.bottomSheetZones);
        Toast.makeText(getContext(), "qrCode: "+codigoValidador+" status de recepcion: "+codigoValidador1, Toast.LENGTH_SHORT).show();
        presenter= new salidaViewPresenterImplements(this,getContext());
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
    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);

        // Handle the dismissal of the Salida dialog here
        // You can perform any actions or checks you need

        // For example, you can restart the camera process in BarcodeScannerActivity
        if (getActivity() instanceof BarcodeScannerActivity) {
            BarcodeScannerActivity barcodeScannerActivity = (BarcodeScannerActivity) getActivity();
            barcodeScannerActivity.restartCameraProcess();
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimationBottonSheet;
    }


    @Override
    public void hideProgress() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void setManifestCard(List<dataManifestV2> data) {
        this.data=data;
        Log.e("datademanifiesto",""+data);
    }

    public void closeDialog() {
        this.dismiss();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.closeReasons:
                dismiss();
                break;
            case R.id.imageButton:
                closeDialog();
                break;
            case R.id.clear:
                BarcodeScannerActivity barcodeScannerActivity = (BarcodeScannerActivity) getActivity();
                barcodeScannerActivity.resetShared();
                Toast.makeText(getContext(), "reset ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.gonext:
                if(codigoValidador1.equals("4")) {
                BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
                barcodeScannerActivity1.godialogCheck();
                }
        }

    }


}
