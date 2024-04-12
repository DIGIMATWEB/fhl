package com.fhl.sistemadedistribucionfh.Dialogs.completeSalida;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.fhl.sistemadedistribucionfh.Dialogs.completeSalida.presenter.presenterSalida;
import com.fhl.sistemadedistribucionfh.Dialogs.completeSalida.presenter.presenterSalidaImpl;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;
import com.fhl.sistemadedistribucionfh.mainContainer.mainContainer;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;

import java.util.List;

public class dialogCompletedSalida extends DialogFragment implements View.OnClickListener {
    public static final String TAG = dialogCompletedSalida.class.getSimpleName();
    private ImageButton imageButton2;
    private List<dataTicketsManifestV2> dataTickets;
    private List<Sello> dataSellos;
    private String currentManifest;
    private presenterSalida presenter;
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
        Bundle args = getArguments();
        if (args != null) {
         currentManifest = args.getString("manifest");
         dataTickets= (List<dataTicketsManifestV2>) args.getSerializable("tickets");
         dataSellos = (List<Sello>) args.getSerializable("sellos");
         Log.e("salida",""+currentManifest+" tickets "+ dataTickets+" sellos "+dataSellos);
        }
        initDialog(view);
        //setFonts();
        return view;
    }

    private void initDialog(View view) {
        imageButton2=view.findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(this);
        presenter= new presenterSalidaImpl(this,getContext());
        presenter.requestTokenAvocado();
        presenter.requestDetailTicketsSendtriplus(false,0,currentManifest, null,dataTickets.get(0).getFolioTicket());    //este metodo es por si venia solo como string o como array
    }

    public void closeDialog() {
        this.dismiss();

    }
    public void startSendtriplus() {
    }
    public void nextRequest() {
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButton2:
                //closeDialog();
                presenter.sendSentriplus(currentManifest,null,"Entrega");
                Intent intent = new Intent(getContext(), mainContainer.class);
                startActivity(intent);
                // Close the dialog if needed
                closeDialog();
                Log.e("salida", "ir a manifiestos");
                break;
        }
    }




}