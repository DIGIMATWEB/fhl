package com.fhl.sistemadedistribucionfh.mainContainer;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.mainContainer.adapterMenu.adapterMenus;
import com.fhl.sistemadedistribucionfh.mainContainer.model.dataMenuItems;
import com.fhl.sistemadedistribucionfh.mainContainerV2.modelV2.dataMenuItemsV2;

import java.util.List;

public class mainMenu extends DialogFragment implements View.OnClickListener {
    public static final String TAG = mainMenu.class.getSimpleName();
    private adapterMenus adapter;
    private ImageView menu;
    private mainContainer mactivity;
    private RecyclerView rv;
    private List<dataMenuItems> data;
    private List<dataMenuItemsV2> dataV2;
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
        Log.e("menuv2", "a" + data+ " b "+dataV2);
        if(dataV2!=null) {
            fillAdapter( dataV2);
        } else {
            //Algo
        }

    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        mactivity.hideCover();

        //f (getActivity() instanceof BarcodeScannerActivity3) {
        //            BarcodeScannerActivity3 barcodeScannerActivity = (BarcodeScannerActivity3) getActivity();
        //            barcodeScannerActivity.restartCameraProcess();
        //            barcodeScannerActivity.resetError();
        //        }
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

    public void setDataV2(List<dataMenuItemsV2> dataV2) {
        this.dataV2 = dataV2;
    }

    private void fillAdapter(List<dataMenuItemsV2> dataV2) {
        adapter = new adapterMenus(this, dataV2,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);

    }
}
