package com.companyname.mauitest.Dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.companyname.mauitest.R;
import com.companyname.mauitest.mainContainer.mainContainer;

public class mainMenu extends DialogFragment implements View.OnClickListener {
    public static final String TAG = mainMenu.class.getSimpleName();

    private ImageView menu;
    private mainContainer mactivity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mactivity= (mainContainer) getActivity();
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_mainmenu, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.alfa);
        setCancelable(true);
        initDialog(view);
        //setFonts();
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    private void initDialog(View view) {
        menu=view.findViewById(R.id.menuButon);
        menu.setOnClickListener(this);
    }

    public void closeDialog() {
        this.dismiss();
        mactivity.hideCover();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case    R.id.menuButon:
                closeDialog();
                break;
        }

    }
}
