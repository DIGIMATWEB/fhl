package com.fhl.sistemadedistribucionfh.Dialogs.completeSalida;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

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
    private boolean isInitSendFoliosCalled = false;
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
        presenter= new presenterSalidaImpl(this,getContext());
        presenter.requestTokenAvocado();

    }
    @Override
    public void startSendtriplus() {//1 ya existe el token de avocado
        if(dataTickets!=null) {//se otienen los detalles del ticket por posicion
            iteratedidTicket=0;
            if(iteratedidTicket == 0) {
                Log.e("salidaSentrip", "iteratedidTicket " + iteratedidTicket);
                Log.e("salidaSentrip", "currentManifest " + currentManifest);
                Log.e("salidaSentrip", "getFolioTicket " + dataTickets.get(iteratedidTicket).getFolioTicket());
                presenter.requestDetailTicketsSendtriplus(true, iteratedidTicket, currentManifest, null, dataTickets.get(0).getFolioTicket());    //este metodo es por si venia solo como string o como array
            }
        }else {
            Toast.makeText(getContext(), "El manifiesto no cuenta con ningun ticket", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void setDetailTicketsentriplus(List<dataTicketsDetailsendtrip> data) {// 2 ya tiene la data del ticket 0
        this.dataticketDetail=data;
        if(iteratedidTicket!=0){
           //
            Log.e("failsentrip","iterador es "+iteratedidTicket+" ");//+dataticketDetail.get(iteratedidTicket).getFolioTicket());
            Log.e("failsentrip","size es "+(dataTickets.size() - 1)+" ");
         if(iteratedidTicket!=(dataTickets.size() - 1)) {
                initSendFolios();
           }
        }else{
            Log.e("salidaSentrip","iterador es 0 "+iteratedidTicket+" ");//+dataticketDetail.get(iteratedidTicket).getFolioTicket());
        }
    }

    @Override
    public void failDetailTicket() {
       //esto no va presenter.requestDetailTicketsSendtriplus(true, iteratedidTicket, currentManifest, null, dataTickets.get(iteratedidTicket).getFolioTicket());
    }
    private void initSendFolios() {//manda al sentripplus regresa y cae en next
        presenter.sendSentriplus(currentManifest,dataticketDetail,"Entrega");
        Log.e("failsentrip",dataticketDetail.get(0).getFolioTicket());
    }
    private void changestatus(){
        Log.e("salidaSentrip","else secuence "+secuence);
        presenter.changeStatusManifestTicket(currentManifest,dataTickets.get(iteratedidTicket).getFolioTicket(),"Entrega");
    }
    @Override
    public void nextRequest() {// este metodo se usa en

        secuence=secuence+1;
        if(secuence==1){
            if (!isInitSendFoliosCalled) {
                initSendFolios();
                isInitSendFoliosCalled = true; // Set the flag to true
            }
        }else if(secuence==2){
            changestatus();
        }else if(secuence==3) {
            iteratedidTicket = iteratedidTicket + 1;
            if (iteratedidTicket > (dataTickets.size() - 1)) {//si el iterador de tickets es igual el maximo de tickets termina y regresa a los manifiestos
                //Intent intent = new Intent(getContext(), mainContainer.class);
                //startActivity(intent);
                // Close the dialog if needed
                closeDialog();
            } else {
                presenter.requestDetailTicketsSendtriplus(true, iteratedidTicket, currentManifest, null, dataTickets.get(iteratedidTicket).getFolioTicket());
                secuence = 1;
            }
        }else {
            Log.e("salidaSentrip","else secuence "+secuence);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButton2://
                initSendFolios();
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