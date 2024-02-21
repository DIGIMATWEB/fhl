package com.fhl.sistemadedistribucionfh.Dialogs.Reasons.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.adapter.adapterReasons;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.model.dataReasons;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.presenter.dialogReasonsPresenter;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.presenter.dialogReasonsPresenterImpl;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.cerrarViaje.view.cancelarViaje;
import com.fhl.sistemadedistribucionfh.mlkit.BarcodeScannerActivity;

import java.util.List;

public class dialogReasons extends DialogFragment implements View.OnClickListener,dialogReasonsView {
    public static final String TAG = dialogReasons.class.getSimpleName();
    private RecyclerView rvReasons;
    private adapterReasons adapter;
    private dialogReasonsPresenter presenter;
    private ImageView closeReasons;
    private String fcheckedItem;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_reasons, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.customTransparent2);
        setCancelable(true);
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
        rvReasons=view.findViewById(R.id.rvReasons);
        presenter= new dialogReasonsPresenterImpl(this,getContext());
        closeReasons=view.findViewById(R.id.closeReasons);
        closeReasons.setOnClickListener(this);
        presenter.requestMReasons();
    }

    private void fillAdapter(List<dataReasons> data, Context context) {
        adapter = new adapterReasons(this,data,context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvReasons.setLayoutManager(linearLayoutManager);
        rvReasons.setAdapter(adapter);
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
        }
    }
    public void showToast( dataReasons checkedItem) {

        this.fcheckedItem= checkedItem.getReason()+checkedItem.getIdReason();
        cancelarViaje cancelarViaje = (cancelarViaje) getActivity();
        cancelarViaje.showToast(fcheckedItem,checkedItem.getIdReason());
        dismiss();
        // Implement your logic here
        // For example, you can show a toast with the checked item's details
    }
    @Override
    public void setReasons(List<dataReasons> data) {
        fillAdapter(data,getContext());
    }
}
///dialogReasons