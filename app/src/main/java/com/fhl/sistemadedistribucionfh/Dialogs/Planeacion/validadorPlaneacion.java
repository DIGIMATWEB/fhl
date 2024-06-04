package com.fhl.sistemadedistribucionfh.Dialogs.Planeacion;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fhl.sistemadedistribucionfh.Dialogs.EmpaquesValidador.validadorEmpaques;
import com.fhl.sistemadedistribucionfh.Dialogs.Planeacion.adapter.adapterPlaneacionEmpaques;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.model.ticketsScanned;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.Paquete;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.dataTicketsDetailsendtrip;
import com.fhl.sistemadedistribucionfh.mlkit.BarcodeScannerActivity3;

import java.util.ArrayList;
import java.util.List;

public class validadorPlaneacion extends DialogFragment implements View.OnClickListener {
    public static final String TAG = validadorEmpaques.class.getSimpleName();
    private RecyclerView rvReasons, rv;
    private adapterPlaneacionEmpaques adapter;
    //   private adapterTicketsSalidaEmpaques adapter2;
//    private dialogReasonsPresenter presenter;
    private ImageView closeReasons;
    private List<ticketsScanned> model = new ArrayList<>();
    private List<dataTicketsDetailsendtrip> codigoValidador;
    private List<Sello> sellos;
    private ImageButton imageButton;
    private TextView textChekcs;
    private Integer countok = 0;
    private Integer countokEmpaques = 0;
    private String typeScanner, currentmanifest;
    private TextView recoleccion, textEmpaques;
    private List<ticketsScanned> fresult;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_tickets_planeacion, container, false);
        getDialog().getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        getDialog().getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        getDialog().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.alfa);
        setCancelable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        initDialog(view);

        fillAdapter(model, getContext());

        //setFonts();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimationBottonSheet;
    }

    private void initDialog(View view) {
        recoleccion = view.findViewById(R.id.textView66);
        textEmpaques = view.findViewById(R.id.textEmpaques);
        rvReasons = view.findViewById(R.id.rvTicketsSalidaL);
        rv = view.findViewById(R.id.rvTicketsEmpaques);
        imageButton = view.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(this);
        textChekcs = view.findViewById(R.id.textChekcs);
        recoleccion.setText("Lotes escaneados");
        //presenter= new dialogReasonsPresenterImpl(this,getContext());


        //presenter.requestMReasons();
    }

    private void fillAdapter(List<ticketsScanned> data, Context context) {
        adapter = new adapterPlaneacionEmpaques(this, data, context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvReasons.setLayoutManager(linearLayoutManager);
        rvReasons.setAdapter(adapter);
        //fillAdapter2(data,context);
    }

    public void nullempaquesCheckticket(String folioTicket) {
        for (ticketsScanned ticket : model) {
            if (ticket.getFolio().equals(folioTicket)) {
                ticket.setFlag(true);
                break;
            }
        }

    }

    public void closeDialog() {
        this.dismiss();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButton:
                //closeDialog();// BarcodeScannerActivity2 barcodeScannerActivity1 = (BarcodeScannerActivity2) getActivity();
                //                                                        barcodeScannerActivity1.returnResult("1234");
                if(countok!=0) {
                    if (countok == model.size()) {//todo hasta igualar los empaques model.size()
                        Toast.makeText(getContext(), "Escaneaste todos los paquetes", Toast.LENGTH_SHORT).show();
                        BarcodeScannerActivity3 barcodeScannerActivity1 = (BarcodeScannerActivity3) getActivity();
                        barcodeScannerActivity1.returnResult(model);


                    } else {
                        Toast.makeText(getContext(), "No escaneaste todos los paquetes", Toast.LENGTH_SHORT).show();
                        BarcodeScannerActivity3 barcodeScannerActivity1 = (BarcodeScannerActivity3) getActivity();
                        barcodeScannerActivity1.returnResult(model);
                    }
                }else{
                    Toast.makeText(getContext(), "No se a escaneado ningun lote", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void sendToast(String code) {//para cada codigo recibido

    }

    public void updatescanedData(List<ticketsScanned> data) {

    }
}