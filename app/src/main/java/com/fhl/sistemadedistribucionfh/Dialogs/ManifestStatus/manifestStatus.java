package com.fhl.sistemadedistribucionfh.Dialogs.ManifestStatus;

import android.content.Context;
import android.content.DialogInterface;
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

import com.fhl.sistemadedistribucionfh.Cancelar.view.cancelarViaje;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.adapter.adapterReasons;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.model.dataReasons;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.presenter.dialogReasonsPresenter;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.presenter.dialogReasonsPresenterImpl;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.view.dialogReasons;
import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.view.dialogReasonsView;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.nmanifest.viewV2.mmanifestV2;

import java.util.ArrayList;
import java.util.List;

public class manifestStatus extends DialogFragment implements View.OnClickListener {
    public static final String TAG = manifestStatus.class.getSimpleName();
    private RecyclerView rvReasons;
    private adapterManifestStatus adapter;
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
        View view = inflater.inflate(R.layout.dialog_manifest_status, container, false);
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

        closeReasons=view.findViewById(R.id.closeReasons);
        closeReasons.setOnClickListener(this);
        List<String> listaManifestStatus=new ArrayList<>();
        listaManifestStatus.clear();
        listaManifestStatus.add("Todo");
        listaManifestStatus.add("Confirmado");
        listaManifestStatus.add("En proceso");
        listaManifestStatus.add("Cerrado");
        fillAdapter(listaManifestStatus,getContext());

    }

    private void fillAdapter(List<String> data, Context context) {
        adapter = new adapterManifestStatus(this,data,context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvReasons.setLayoutManager(linearLayoutManager);
        rvReasons.setAdapter(adapter);
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        mmanifestV2 manifest = (mmanifestV2) getActivity().getSupportFragmentManager().findFragmentByTag(mmanifestV2.TAG);
        manifest.skipDialog();
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
    public void showToast( String checkedItem) {

        this.fcheckedItem= checkedItem;//+checkedItem.getIdReason();
        mmanifestV2 manifest = (mmanifestV2) getActivity().getSupportFragmentManager().findFragmentByTag(mmanifestV2.TAG);
        manifest.setFilterDialog(fcheckedItem);
        dismiss();
        // Implement your logic here
        // For example, you can show a toast with the checked item's details
    }
}
