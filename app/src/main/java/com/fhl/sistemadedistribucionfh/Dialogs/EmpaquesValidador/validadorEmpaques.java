package com.fhl.sistemadedistribucionfh.Dialogs.EmpaquesValidador;

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

import com.fhl.sistemadedistribucionfh.Dialogs.EmpaquesValidador.adapter.adapterValidadorEmpaques;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.Adapter.adapterTicketsSalida;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.model.ticketsScanned;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.Paquete;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.dataTicketsDetailsendtrip;
import com.fhl.sistemadedistribucionfh.mlkit.BarcodeScannerActivity;
import com.fhl.sistemadedistribucionfh.mlkit.BarcodeScannerActivity2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;

import java.util.ArrayList;
import java.util.List;

public class validadorEmpaques extends DialogFragment implements View.OnClickListener {
public static final String TAG = validadorEmpaques.class.getSimpleName();
private RecyclerView rvReasons, rv;
private adapterValidadorEmpaques adapter;
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
                View view = inflater.inflate(R.layout.dialog_tickets, container, false);
                getDialog().getWindow().setBackgroundDrawableResource(R.color.alfa);
                setCancelable(true);
                Bundle args = getArguments();
                if (args != null) {
                        codigoValidador = (List<dataTicketsDetailsendtrip>) args.getSerializable("tickets");
                        sellos = (List<Sello>) args.getSerializable("sellos");
                        typeScanner = args.getString("typeScanner");
                        currentmanifest = args.getString("currentmanifest");
                        fresult=(List<ticketsScanned>)args.getSerializable("lotes");
                        Log.e("typeScanner", "" + typeScanner);
                }
                initDialog(view);
                if (codigoValidador != null) {
                        if(fresult!=null){
                                model=fresult;
                        }else {
                                model.clear();
                                Log.e("ticketsArray2", "adapter size" + codigoValidador.size());
                                for (int i = 0; i < codigoValidador.size(); i++) {
                                        model.add(new ticketsScanned(codigoValidador.get(i).getFolioTicket(), false, codigoValidador.get(i).getSendtripPlus()));
                                        Log.e("ticketsArray2", "model size: " + model.get(i).getFolio() + "  " + model.get(i).getFlag());

                                }
                        }
                        textChekcs.setText("0/" + model.size());
                        fillAdapter(model, getContext());
                }
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
                adapter = new adapterValidadorEmpaques(this, data, context);
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

//    private void fillAdapter2(List<ticketsScanned> data, Context context) {
//
//        adapter2 = new adapterTicketsSalidaEmpaques(this,data,context);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        rv.setLayoutManager(linearLayoutManager);
//        rv.setAdapter(adapter2);
//    }

        public void closeDialog() {
                this.dismiss();

        }

        @Override
        public void onClick(View view) {
                switch (view.getId()) {
                        case R.id.imageButton:
                                //closeDialog();// BarcodeScannerActivity2 barcodeScannerActivity1 = (BarcodeScannerActivity2) getActivity();
                                //                                                        barcodeScannerActivity1.returnResult("1234");
                                if (countok == model.size()) {//todo hasta igualar los empaques model.size()
                                        Toast.makeText(getContext(), "Escaneaste todos los paquetes", Toast.LENGTH_SHORT).show();
                                        BarcodeScannerActivity2 barcodeScannerActivity1 = (BarcodeScannerActivity2) getActivity();
                                        barcodeScannerActivity1.returnResult(model);


                                } else {
                                        Toast.makeText(getContext(), "No escaneaste todos los paquetes", Toast.LENGTH_SHORT).show();
                                        BarcodeScannerActivity2 barcodeScannerActivity1 = (BarcodeScannerActivity2) getActivity();
                                        barcodeScannerActivity1.returnResult(model);
                                }
                                break;
                }
        }

        public void sendToast(String code) {//para cada codigo recibido
                if (model != null) {   //si existe ingotmacions
                        // Log.e("ticketsArray2", "comprueva el modelo " + model.size() + " comprueba el codigo " + code);
                        boolean codeFound = false;

                        for (ticketsScanned ticket : model) {             //se usca por ticket
                                if (ticket.getFolio() != null && ticket.getFolio().equals(code)) {   //si existe folio y es igual alcodigo
                                        codeFound = true;                                                //el codigo existe en la lista
                                        if (!ticket.getFlag()) {                                         //se no esta togglea el ticket
                                                // ticket.setFlag(true);                                        //se setea en true
                                                Log.e("empaque", "codigo escaneado correctamente pero se cambio solo para empaques ATENCION");
                                        } else {
                                                Log.e("ticketsArray2", "codigo ya escaneado");     //si no se tice que ya se escanneo
                                        }
                                        Log.e("ticketsArray2", "" + ticket.getFolio());
                                        break;
                                } else {
                                        if (ticket.getSendtripPlus().getPaquetes() != null) {
                                                for (int i = 0; i < ticket.getSendtripPlus().getPaquetes().size(); i++) {  // se busca por paquete
                                                        if (code.equals(ticket.getSendtripPlus().getPaquetes().get(i).getNombre())) {
                                                                codeFound = true;
                                                                if (!ticket.getSendtripPlus().getPaquetes().get(i).getFlag()) {
                                                                        ticket.getSendtripPlus().getPaquetes().get(i).setFlag(true);
                                                                        Log.e("empaque", "código escaneado correctamente");
                                                                } else {
                                                                        Log.e("empaque", "código ya fue escaneado");
                                                                }
                                                                Log.e("empaque", "" + ticket.getSendtripPlus().getPaquetes().get(i).getNombre());
                                                                break;
                                                        }
                                                }
                                        }
                                        // Check if all paquetes flags are true
                                        boolean allPaquetesFlagged = true;
                                        for (int i = 0; i < ticket.getSendtripPlus().getPaquetes().size(); i++) {
                                                if (!ticket.getSendtripPlus().getPaquetes().get(i).getFlag()) {
                                                        allPaquetesFlagged = false;
                                                        break;
                                                }
                                        }
                                        if (allPaquetesFlagged) {
                                                ticket.setFlag(true);
                                                codeFound = true;
                                        }
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

        public void updatescanedData(List<ticketsScanned> data) {
                List<Paquete> lotes = new ArrayList<>();
                lotes.clear();
                for (ticketsScanned b : data) {
                        if (b.getFlag()) {
                                if (countok == model.size()) {

                                } else {
                                        int checketCount = 0;
                                        for (int i = 0; i < data.size(); i++) {
                                                if (data.get(i).getFlag() == true) {
                                                        checketCount++;
                                                }
                                        }
                                        countok = checketCount;
                                }
                        }
                        //textEmpaques
                        if (b.getSendtripPlus().getPaquetes() != null) {
                                b.getSendtripPlus().getPaquetes().size();
                                if (b.getSendtripPlus().getPaquetes() != null) {
                                        for (int i = 0; i < b.getSendtripPlus().getPaquetes().size(); i++) {
                                                lotes.add(b.getSendtripPlus().getPaquetes().get(i));
                                                Log.e("Lotes", "ticket: " + b.getFolio() + " contiene " + b.getSendtripPlus().getPaquetes().size() + " empaques" + " empaque " + b.getSendtripPlus().getPaquetes().get(i).getNombre());
                                        }
                                }
                        }

                }
                Log.e("ticketsArray2", "textcount: " + countok);
                textChekcs.setText(countok + "/" + model.size());
        }



//        public void updatescanedDataEmpaque(List<Paquete> paquetes) {
//        }


}