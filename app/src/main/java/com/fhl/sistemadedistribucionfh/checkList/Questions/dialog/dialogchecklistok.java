package com.fhl.sistemadedistribucionfh.checkList.Questions.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.checkList.Questions.view.questionFragment;
import com.fhl.sistemadedistribucionfh.checkList.view.checkList;

public class dialogchecklistok extends DialogFragment implements View.OnClickListener {
    public static final String TAG = dialogchecklistok.class.getSimpleName();

    private ImageButton imageButtoncheckok;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private questionFragment fm;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);
    }
    public dialogchecklistok() {
        // Required empty public constructor
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_warning_checklist_ok, container, false);
        //getDialog().getWindow().setBackgroundDrawableResource(R.color.customTransparent);
        setCancelable(true);
        initDialog(view);
        //setFonts();
        return view;
    }


//    public void setOnDialogDismissListener(OnDialogDismissListener listener) {
//        this.onDialogDismissListener = listener;
//    }
    private void initDialog(View view) {
        imageButtoncheckok=view.findViewById(R.id.imageButtoncheckok);
        imageButtoncheckok.setOnClickListener(this);
    }

    public void closeDialog() {
        fm.dismisedDialog();
        this.dismiss();


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButtoncheckok:
                closeDialog();
                break;
        }
    }


    public void publicmethod(questionFragment questionFragment) {
        this.fm=questionFragment;
    }
}
