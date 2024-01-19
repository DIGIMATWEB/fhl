package com.fhl.sistemadedistribucionfh.Dialogs;

import android.content.Intent;
import android.os.Bundle;
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

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.mainContainer.mainContainer;

public class dialogCompletedSalida extends DialogFragment implements View.OnClickListener {
    public static final String TAG = dialogCompletedSalida.class.getSimpleName();
    private ImageButton imageButton2;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_despacho_salida, container, false);
        //getDialog().getWindow().setBackgroundDrawableResource(R.color.customTransparent);
        setCancelable(true);
        initDialog(view);
        //setFonts();
        return view;
    }

    private void initDialog(View view) {
        imageButton2=view.findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(this);
    }

    public void closeDialog() {
        this.dismiss();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButton2:
                //closeDialog();
                Intent intent = new Intent(getContext(), mainContainer.class);
                startActivity(intent);
                // Close the dialog if needed
                closeDialog();
                Toast.makeText(getContext(), "ir a manifiestos", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}