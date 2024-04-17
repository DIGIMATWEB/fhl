package com.fhl.sistemadedistribucionfh.Dialogs.completeSalida;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.fhl.sistemadedistribucionfh.Dialogs.Loader.view.loaderFH;
import com.fhl.sistemadedistribucionfh.Dialogs.completeSalida.presenter.presenterSalida;
import com.fhl.sistemadedistribucionfh.Dialogs.completeSalida.presenter.presenterSalidaImpl;
import com.fhl.sistemadedistribucionfh.Dialogs.completeSalida.view.dialogCompletedSalidaImp;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.dataTicketsDetailsendtrip;
import com.fhl.sistemadedistribucionfh.mainContainer.mainContainer;
import com.fhl.sistemadedistribucionfh.mlkit.BarcodeScannerActivity;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;

import java.util.List;

public class dialogCompletedSalida extends DialogFragment implements View.OnClickListener, dialogCompletedSalidaImp {
    public static final String TAG = dialogCompletedSalida.class.getSimpleName();
    private ImageButton imageButton2;
    private List<dataTicketsManifestV2> dataTickets;
    private List<Sello> dataSellos;
    private String currentManifest;
    private presenterSalida presenter;
    private Integer iteratedidTicket,secuence=0;
    private List<dataTicketsDetailsendtrip> dataticketDetail;
    private loaderFH progress;

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
        iteratedidTicket=0;
        secuence=0;
        Bundle args = getArguments();
        if (args != null) {
         this.currentManifest = args.getString("manifest");
         this.dataTickets= (List<dataTicketsManifestV2>) args.getSerializable("tickets");
         this.dataSellos = (List<Sello>) args.getSerializable("sellos");
         Log.e("salidaSentrip",""+currentManifest+" tickets "+ dataTickets+" sellos "+dataSellos);
        }
        initDialog(view);
        //setFonts();
        return view;
    }

    private void initDialog(View view) {
        imageButton2=view.findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(this);
        progress = new loaderFH();
        presenter= new presenterSalidaImpl(this,getContext());
        presenter.requestTokenAvocado();

    }
    @Override
    public void startSendtriplus() {//1 ya existe el token de avocado
        if (dataTickets != null && iteratedidTicket == 0) { // Check if dataTickets is not null and iteratedidTicket is 0
            presenter.requestDetailTicketsSendtriplus(true, iteratedidTicket, currentManifest, null, dataTickets.get(iteratedidTicket).getFolioTicket());
        } else {
            Toast.makeText(getContext(), "El manifiesto no cuenta con ningún ticket o ya se ha iniciado el proceso", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void setDetailTicketsentriplus(List<dataTicketsDetailsendtrip> data) {// 2 ya tiene la data del ticket 0
        this.dataticketDetail = data;
        if (iteratedidTicket != 0) {
                presenter.nextRequest();
        }
    }

    @Override
    public void failDetailTicket() {
       //esto no va presenter.requestDetailTicketsSendtriplus(true, iteratedidTicket, currentManifest, null, dataTickets.get(iteratedidTicket).getFolioTicket());
    }

    @Override
    public void showDialog() {
        if (progress != null && !progress.isVisible()) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("HAS_TITLE", false);
            bundle.putString("title","Cargando detalles");
            progress.setArguments(bundle);
            progress.show(getParentFragmentManager(), loaderFH.TAG);
        }
    }

    @Override
    public void hideDialog() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (progress != null && this != null)
                    progress.dismiss();
            }
        }, 300);
    }

    private void initSendFolios() {//manda al sentripplus regresa y cae en next
        Log.e("failsentrip","folio "+dataticketDetail.get(0).getFolioTicket());
        presenter.sendSentriplus(currentManifest,dataticketDetail,"Entrega");
    }
    private void changestatus(){//estatus 2
        Log.e("failsentrip","else secuence "+secuence);
        Log.e("failsentrip","else secuence "+iteratedidTicket);
        presenter.changeStatusManifestTicket(currentManifest,dataTickets.get(iteratedidTicket).getFolioTicket(),"Entrega");
    }
    @Override
    public void nextRequest() {// este metodo se usa en

        secuence = secuence + 1;
        if (secuence == 1 ) {
            initSendFolios();
        } else if (secuence == 2) {
            changestatus();
        } else if (secuence == 3) {
            iteratedidTicket = iteratedidTicket + 1;
            if (iteratedidTicket > (dataTickets.size() - 1)) {
                presenter.hideDialog();
                closeDialog();
            } else {
                secuence = 0;
                presenter.requestDetailTicketsSendtriplus(true, iteratedidTicket, currentManifest, null, dataTickets.get(iteratedidTicket).getFolioTicket());
            }
        }else {
            presenter.hideDialog();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButton2://
                presenter.nextRequest();
                presenter.showDialog();
                break;
        }
    }



    @Override
    public void closeDialog() {
        BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
        barcodeScannerActivity1.onBackPressed();
        dismiss();
        Log.e("salida", "ir a manifiestos");
    }

}