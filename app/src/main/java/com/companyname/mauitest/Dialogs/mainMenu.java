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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.companyname.mauitest.R;
import com.companyname.mauitest.mainContainer.adapterMenu.adapterMenus;
import com.companyname.mauitest.mainContainer.mainContainer;
import com.companyname.mauitest.mainContainer.model.dataMenuItems;

import java.util.List;

public class mainMenu extends DialogFragment implements View.OnClickListener {
    public static final String TAG = mainMenu.class.getSimpleName();
    private adapterMenus adapter;
    private ImageView menu;
    private mainContainer mactivity;
    private RecyclerView rv;
    private List<dataMenuItems> data;
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
        initDialog(view);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.alfa);
        setCancelable(true);
        //setFonts();
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    private void initDialog(View view) {
        rv=view.findViewById(R.id.rv);
        menu=view.findViewById(R.id.menuButon);
        menu.setOnClickListener(this);
        if(data!=null) {
            fillAdapter(data);
        }

    }
    public void invokeFragment(String menu){
        mactivity.chooseFragment(menu);
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

    public void setData(List<dataMenuItems> data) {
        this.data=data;

    }

    private void fillAdapter(List<dataMenuItems> data) {
        adapter = new adapterMenus(this,data,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);

    }
}
