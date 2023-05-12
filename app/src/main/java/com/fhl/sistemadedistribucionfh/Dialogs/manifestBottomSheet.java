package com.fhl.sistemadedistribucionfh.Dialogs;

import android.graphics.drawable.Drawable;
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

import com.fhl.sistemadedistribucionfh.R;

public class manifestBottomSheet extends DialogFragment implements View.OnClickListener {
    public static final String TAG = manifestBottomSheet.class.getSimpleName();
    private Button iralmenu;
    private TextView titleheader;
    private ImageView imagebackground;
    private ConstraintLayout vehicle,user,manifest;
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
        manifest.setVisibility(View.VISIBLE);
        user.setVisibility(View.GONE);
        vehicle.setVisibility(View.GONE);
        titleheader.setText("Manifiesto");
        Drawable ndrawable= getActivity().getDrawable(R.drawable.temp_botton_sheet_manifest);
        imagebackground.setBackground(ndrawable);
    }

    public void closeDialog() {
        this.dismiss();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }
}