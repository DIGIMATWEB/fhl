package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.sellosSalida;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.sellosSalida.adapter.adapterSellosSalida;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.sellosSalida.model.sellosScanned;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.Adapter.adapterTicketsSalida;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.model.ticketsScanned;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.ticketsSalida;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;
import com.fhl.sistemadedistribucionfh.mlkit.BarcodeScannerActivity;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;

import java.util.ArrayList;
import java.util.List;

public class sellosSalida extends DialogFragment implements View.OnClickListener {
    public static final String TAG = sellosSalida.class.getSimpleName();
    private RecyclerView rvReasons;
    private adapterSellosSalida adapter;
    private ImageView closeReasons;
    private List<sellosScanned> model=new ArrayList<>();
    private  List<Sello> codigoValidador;
    private ImageButton imageButton;
    private TextView textChekcs;
    private Integer countok=0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_sellos, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.alfa);
        setCancelable(true);
        Bundle args = getArguments();
        if (args != null) {
            codigoValidador= (List<Sello>) args.getSerializable("sellos");
        }
        initDialog(view);
        if(codigoValidador!=null) {
            model.clear();
            Log.e("ticketsArray2", "adapter size" + codigoValidador.size());
            for(int i=0; i< codigoValidador.size();i++){
                model.add(new sellosScanned(codigoValidador.get(i).getNumeroSello(),false));
                Log.e("ticketsArray2", "model size: " + model.get(i).getFolio()+"  "+model.get(i).getFlag());
            }
            textChekcs.setText("0/"+model.size());
            fillAdapter(model,getContext());
        }//setFonts();
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimationBottonSheet;
    }
    private void initDialog(View view) {
        rvReasons=view.findViewById(R.id.rvTickets);
        imageButton = view.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(this);
        textChekcs=view.findViewById(R.id.textChekcs);

        //presenter= new dialogReasonsPresenterImpl(this,getContext());


        //presenter.requestMReasons();
    }

    private void fillAdapter(List<sellosScanned> data, Context context) {
        adapter = new adapterSellosSalida(this,data,context);
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
            case R.id.imageButton:
                //closeDialog();
                if(countok==model.size()) {
                    // Toast.makeText(getContext(), "ir a sellostodos fueron escaneados", Toast.LENGTH_SHORT).show();
                    BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
                    barcodeScannerActivity1.goSellosSummary();
                    closeDialog();

                }else{
                    Toast.makeText(getContext(), "faltan tickets por escanear", Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }

    public void sendToast(String code) {
        if(model != null) {
            // Log.e("ticketsArray2", "comprueva el modelo " + model.size() + " comprueba el codigo " + code);
            boolean codeFound = false;

            for (sellosScanned sello : model) {
                if (sello.getFolio() != null && sello.getFolio().equals(code)) {
                    codeFound = true;
                    if (!sello.getFlag()) {
                        sello.setFlag(true);
                        Log.e("ticketsArray2", "codigo escaneado correctamente");
                    } else {
                        Log.e("ticketsArray2", "codigo ya escaneado");
                    }
                    Log.e("ticketsArray2", ""+sello.getFolio());
                    break;
                }
            }
            if (!codeFound) {
                Log.e("ticketsArray2", "codigo no pertenece a la lista");
                BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
                barcodeScannerActivity1.errorTicket();
            }
            if (adapter != null) {
                adapter.updateData(model);
                adapter.notifyDataSetChanged();
            }
        }
    }

    public void updatescanedData(List<sellosScanned> data) {

        for (sellosScanned b : data) {
            if (b.getFlag()) {
                if(countok==model.size()){

                }else {
                    countok++;
                }
            }
        }
        Log.e("ticketsArray2","textcount: "+countok);
        textChekcs.setText(countok+"/"+model.size());
    }
}