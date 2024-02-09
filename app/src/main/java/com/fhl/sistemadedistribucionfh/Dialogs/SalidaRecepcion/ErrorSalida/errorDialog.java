package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ErrorSalida;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.mlkit.BarcodeScannerActivity;

public class errorDialog  extends DialogFragment implements View.OnClickListener {
    public static final String TAG = errorDialog.class.getSimpleName();
    private ImageButton imageButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_error_salida, container, false);
        //getDialog().getWindow().setBackgroundDrawableResource(R.color.customTransparent);
        setCancelable(true);
        initDialog(view);
        //setFonts();
        return view;
    }

    private void initDialog(View view) {
        imageButton = view.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(this);
    }

    public void closeDialog() {
        this.dismiss();

    }
    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);

        // Handle the dismissal of the Salida dialog here
        // You can perform any actions or checks you need

        // For example, you can restart the camera process in BarcodeScannerActivity
        if (getActivity() instanceof BarcodeScannerActivity) {
            BarcodeScannerActivity barcodeScannerActivity = (BarcodeScannerActivity) getActivity();
            barcodeScannerActivity.restartCameraProcessfromerror();
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButton:
                closeDialog();
                break;
        }
    }
}
