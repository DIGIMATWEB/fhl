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
import androidx.fragment.app.DialogFragment;
import com.fhl.sistemadedistribucionfh.R;
public class vehicleBottomSheet extends DialogFragment implements View.OnClickListener {
    public static final String TAG = vehicleBottomSheet.class.getSimpleName();
    private Button iralmenu;
    private TextView titleheader;
    private ImageView imagebackground;
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
        titleheader.setText("Vehículo");
        Drawable ndrawable= getActivity().getDrawable(R.drawable.temp_botton_sheet);
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
