package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.view;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.adapter.adapterReasons;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.model.dataReasons;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.presenter.dialogReasonsPresenter;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.presenter.dialogReasonsPresenterImpl;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.view.dialogReasons;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.view.dialogReasonsView;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.presenter.salidaViewPresenter;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.mlkit.BarcodeScannerActivity;
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

        //setFonts();
        return view;
    }
    private void initDialog(View view) {
        // presenter= new dialogReasonsPresenterImpl(this,getContext());
        imageButton=view.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(this);
        bottomSheet=view.findViewById(R.id.bottomSheetZones);
        Toast.makeText(getContext(), "qrCode: "+codigoValidador+" status de recepcion: "+codigoValidador1, Toast.LENGTH_SHORT).show();
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
            barcodeScannerActivity.restartCameraProcess(codigoValidador1);
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimationBottonSheet;
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
        }

    }

}
