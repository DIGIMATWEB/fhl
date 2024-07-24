package com.fhl.sistemadedistribucionfh.Dialogs.ErrorDialogs;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.mlkit.BarcodeScannerActivity3;

public class errorRecepcion extends DialogFragment implements View.OnClickListener {
    public static final String TAG = errorRecepcion.class.getSimpleName();
    private ImageButton imageButton;
    private TextView textView52;
    private String value;
    private MediaPlayer mediaPlayer;
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
        Bundle args = getArguments();
        if (args != null) {
            value = args.getString("error_value");
            // Use the value as needed
        }
        initDialog(view);
        //setFonts();
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimationBottonSheet;
    }
    private void initDialog(View view) {
        mediaPlayer=new MediaPlayer();
        mediaPlayer= MediaPlayer.create(getContext(), R.raw.bit_error);
        imageButton = view.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(this);
        textView52= view.findViewById(R.id. textView52);
        textView52.setText(""+value);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.start();
            }
        }, 1000);
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
        if (getActivity() instanceof BarcodeScannerActivity3) {
            BarcodeScannerActivity3 barcodeScannerActivity = (BarcodeScannerActivity3) getActivity();
            barcodeScannerActivity.restartCameraProcess();
            barcodeScannerActivity.resetError();
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