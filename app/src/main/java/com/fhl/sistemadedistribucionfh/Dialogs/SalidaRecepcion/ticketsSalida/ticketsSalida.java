package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.gruposTickets;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.Adapter.adapterGroups;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.Adapter.adapterTicketsSalida;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.model.ticketsScanned;
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.evidenciasCarga.view.evidenciasCarga;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.Paquete;
import com.fhl.sistemadedistribucionfh.mlkit.BarcodeScannerActivity;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ticketsSalida extends DialogFragment implements View.OnClickListener,onBackSalida{
    public static final String TAG = ticketsSalida.class.getSimpleName();
    private RecyclerView rvTickets, rvTicketsG;
    private adapterTicketsSalida adapter;
    private adapterGroups adapterG;
    //   private adapterTicketsSalidaEmpaques adapter2;
//    private dialogReasonsPresenter presenter;
    private ImageView closeReasons;
    private List<ticketsScanned> model = new ArrayList<>();
    private List<dataTicketsManifestV2> codigoValidador;
    private List<Sello> sellos;
    private ImageButton imageButton;
    private TextView textChekcs;
    private Integer countok = 0;
    private Integer countokEmpaques = 0;
    private String typeScanner, currentmanifest;
    private TextView recoleccion, textEmpaques;

    private Boolean sendEvidence=false;
    private List<gruposTickets> groupsTickets;
    private Boolean consolidado=true;
    private  Integer countByGropup=0;
    private Integer valAfterEvidence;
    private Boolean waitOnBack=false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);
        Log.e("listenerT", "onCreate ");
        SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(GeneralConstants.POSITIONGROUP);
        editor.apply();

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("listenerT", "onCreateView ");
        return inflater.inflate(R.layout.dialog_tickets, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("listenerT", "onViewCreated ");
        getDialog().getWindow().setBackgroundDrawableResource(R.color.alfa);
        setCancelable(true);
        // Initialize views
        recoleccion = view.findViewById(R.id.textView66);
        textEmpaques = view.findViewById(R.id.textEmpaques);
        rvTickets = view.findViewById(R.id.rvTicketsSalidaC);
        rvTicketsG = view.findViewById(R.id.rvTicketsGroups);
        imageButton = view.findViewById(R.id.imageButton);
        textChekcs = view.findViewById(R.id.textChekcs);
        recoleccion.setText("Lotes escaneados");
        imageButton.setOnClickListener(this);

        Bundle args = getArguments();
        if (args != null) {
            codigoValidador = (List<dataTicketsManifestV2>) args.getSerializable("tickets");
            sellos = (List<Sello>) args.getSerializable("sellos");
            typeScanner = args.getString("typeScanner");
            currentmanifest = args.getString("currentmanifest");
            Log.e("typeScanner", "" + typeScanner);
            Gson gson = new Gson();
            String json = gson.toJson(codigoValidador);
            Log.e("dataticketsSizeE", "" + currentmanifest);
            Log.e("dataticketsSizeE", json);
        }

        if (codigoValidador != null) {
            model.clear();
            Log.e("ticketsArray2", "adapter size" + codigoValidador.size());
            Map<String, List<ticketsScanned>> ticketGroups = new HashMap<>();
            for (int i = 0; i < codigoValidador.size(); i++) {
                ticketsScanned ticket = new ticketsScanned(
                        codigoValidador.get(i).getFolioTicket(),
                        false,
                        codigoValidador.get(i).getSendtripPlus()
                );
                model.add(ticket);


                String remitente = codigoValidador.get(i).getSendtripPlus() != null &&
                        codigoValidador.get(i).getSendtripPlus().getRemitente() != null &&
                        codigoValidador.get(i).getSendtripPlus().getRemitente().getNombre() != null ?
                        codigoValidador.get(i).getSendtripPlus().getRemitente().getNombre() : "Unknown";

                String destinatario = codigoValidador.get(i).getSendtripPlus() != null &&
                        codigoValidador.get(i).getSendtripPlus().getDestinatario() != null &&
                        codigoValidador.get(i).getSendtripPlus().getDestinatario().getNombre() != null ?
                        codigoValidador.get(i).getSendtripPlus().getDestinatario().getNombre() : "Unknown";

                String groupKey = remitente.equals(destinatario) ? remitente : remitente + "-" + destinatario;

                if (!ticketGroups.containsKey(groupKey)) {
                    ticketGroups.put(groupKey, new ArrayList<>());
                }
                ticketGroups.get(groupKey).add(ticket);

                Log.e("dataticketsSizeE", "model size: " + model.get(i).getFolio() + "  " + model.get(i).getFlag());
            }
            Gson gson = new Gson();
            String json = gson.toJson(model);
            Log.e("dataticketsSizeE", "model: " + json);
            textChekcs.setText("0/" + model.size());

            groupsTickets = new ArrayList<>();
            for (Map.Entry<String, List<ticketsScanned>> entry : ticketGroups.entrySet()) {
                groupsTickets.add(new gruposTickets(entry.getValue(), false));
            }

            if (consolidado) {
                Gson gsonG = new Gson();
                String jsonG = gsonG.toJson(groupsTickets);
                Log.e("dataticketsSizeE", "grupos: " + groupsTickets.size());
                Log.e("dataticketsSizeE", "grupos: " + jsonG);
                rvTickets.setVisibility(View.GONE);
                rvTicketsG.setVisibility(View.VISIBLE);
                fillAdapterG(groupsTickets, consolidado);
            } else {
                fillAdapter(model, getContext(), consolidado);
            }
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimationBottonSheet;
    }
    private void initDialog(View view) {
        recoleccion = view.findViewById(R.id.textView66);
        textEmpaques = view.findViewById(R.id.textEmpaques);
        rvTickets = view.findViewById(R.id.rvTicketsSalidaC);
        rvTicketsG = view.findViewById(R.id.rvTicketsGroups);
        imageButton = view.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(this);
        textChekcs = view.findViewById(R.id.textChekcs);
        recoleccion.setText("Lotes escaneados");
        //presenter= new dialogReasonsPresenterImpl(this,getContext());
        //presenter.requestMReasons();
    }

    private void fillAdapter(List<ticketsScanned> data, Context context, Boolean needGroupThem) {
        adapter = new adapterTicketsSalida(this, data, context,needGroupThem);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvTickets.setLayoutManager(linearLayoutManager);
        rvTickets.setAdapter(adapter);
        //fillAdapter2(data,context);
    }
    private void fillAdapterG(List<gruposTickets> groupsTickets, Boolean needGroupThem){
        adapterG= new adapterGroups(this,groupsTickets,getContext(),needGroupThem);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvTicketsG.setLayoutManager(linearLayoutManager);
        rvTicketsG.setAdapter(adapterG);

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


    public void sendToast(String code) {//para cada codigo recibido
        if(!consolidado) {
            if (model != null) {   //si existe ingotmacions
                Log.e("dataticketsSizeE", "comprueva el modelo " + model.size() + " comprueba el codigo " + code);
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
                if (adapter != null) {// esto actualiza todos los ticket que hay en model
                    Log.e("dataticketsSizeE", "adapterSolo");
                    adapter.updateData(model);
                    adapter.notifyDataSetChanged();
                }

            }
        }else{
                if (adapterG != null) { // esto solo actualiza los tickets que estan en model si es que vienen en el grupo
                    Log.e("dataticketsSizeE", "adapterGrupos");
                    adapterG.updateData(model,code);
                    adapterG.notifyDataSetChanged();
                }

        }
    }

    public void updatescanedData(List<ticketsScanned> data) {
        if(!consolidado) {
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
    }
    public void updatescanedDataGroups( List<gruposTickets> groupsTickets){
        List<Boolean> numTrue=new ArrayList<>();
        numTrue.clear();
        for (gruposTickets gt : groupsTickets) {
            for (ticketsScanned ts : gt.getTickets()) {
                if (ts.getFlag() == true) {
                    numTrue.add(ts.getFlag());
                    // Exit the inner loop once a flagged ticket is found in the current group
                }
            }
        }
        countByGropup=numTrue.size();
        countok=countByGropup;
        textChekcs.setText(countByGropup + "/" + model.size());
    }
    public void updatescanedDataEmpaque(List<Paquete> paquetes) {
    }
    public void goEvidenceOneItem(ticketsScanned ticketsScanned) {//esto es solo cuando hay un ticket
        List<dataTicketsManifestV2> codigoValidadorV2 =new ArrayList<>();
        codigoValidadorV2.clear();
        for (dataTicketsManifestV2 tickets: codigoValidador){
            if(ticketsScanned.getFolio()==tickets.getFolioTicket()){
                codigoValidadorV2.add(tickets);
            }
        }
        Intent intent = new Intent(getActivity(), evidenciasCarga.class);
        Bundle bundle = new Bundle();
        bundle.putInt("flujoId", 1);
        bundle.putString("sentripPlusFlow", "Recoleccion");
        bundle.putString("currentManifest", currentmanifest);
        bundle.putString("folioTicket", null);
        bundle.putSerializable("dataTcikets", (Serializable) codigoValidadorV2);
        intent.putExtras(bundle);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void goEvidenceGroups(List<ticketsScanned> ticketsScanned, int position){
        List<dataTicketsManifestV2> codigoValidadorV2 =new ArrayList<>();
        codigoValidadorV2.clear();
        for (dataTicketsManifestV2 tickets: codigoValidador){
            for (ticketsScanned tS:ticketsScanned) {
                if (tS.getFolio() == tickets.getFolioTicket()) {
                    codigoValidadorV2.add(tickets);
                }
            }
        }
        Log.e("dataticketsSizeE",""+codigoValidadorV2.size());
        waitOnBack=true;
        Intent intent = new Intent(getActivity(), evidenciasCarga.class);
        Bundle bundle = new Bundle();
        bundle.putInt("flujoId", 1);
        bundle.putString("sentripPlusFlow", "Recoleccion");
        bundle.putString("currentManifest", currentmanifest);
        bundle.putString("folioTicket", null);
        bundle.putInt("positionGroup",position);
        bundle.putSerializable("dataTcikets", (Serializable) codigoValidadorV2);
        intent.putExtras(bundle);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.e("listenerT", "onViewStateRestored ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("listenerT", "onStart ");
       if(waitOnBack){
            recoleccion = getView().findViewById(R.id.textView66);
            textEmpaques = getView().findViewById(R.id.textEmpaques);
            rvTickets = getView().findViewById(R.id.rvTicketsSalidaC);
            rvTicketsG = getView().findViewById(R.id.rvTicketsGroups);
            imageButton = getView().findViewById(R.id.imageButton);
            textChekcs = getView().findViewById(R.id.textChekcs);
            imageButton.setOnClickListener(this);
            recoleccion.setText("Lotes escaneados");

            // Retrieve arguments
            Bundle args = getArguments();
            if (args != null) {
                codigoValidador = (List<dataTicketsManifestV2>) args.getSerializable("tickets");
                sellos = (List<Sello>) args.getSerializable("sellos");
                typeScanner = args.getString("typeScanner");
                currentmanifest = args.getString("currentmanifest");
                Log.e("typeScanner", "" + typeScanner);
            }

            if (codigoValidador != null) {
                model.clear();
                Log.e("ticketsArray2", "adapter size" + codigoValidador.size());
                Map<String, List<ticketsScanned>> ticketGroups = new HashMap<>();
                for (dataTicketsManifestV2 data : codigoValidador) {
                    ticketsScanned ticket = new ticketsScanned(
                            data.getFolioTicket(),
                            false,
                            data.getSendtripPlus()
                    );
                    model.add(ticket);

                    String remitente = data.getSendtripPlus() != null &&
                            data.getSendtripPlus().getRemitente() != null &&
                            data.getSendtripPlus().getRemitente().getNombre() != null ?
                            data.getSendtripPlus().getRemitente().getNombre() : "Unknown";

                    String destinatario = data.getSendtripPlus() != null &&
                            data.getSendtripPlus().getDestinatario() != null &&
                            data.getSendtripPlus().getDestinatario().getNombre() != null ?
                            data.getSendtripPlus().getDestinatario().getNombre() : "Unknown";

                    String groupKey = remitente.equals(destinatario) ? remitente : remitente + "-" + destinatario;

                    if (!ticketGroups.containsKey(groupKey)) {
                        ticketGroups.put(groupKey, new ArrayList<>());
                    }
                    ticketGroups.get(groupKey).add(ticket);
                }

                textChekcs.setText("0/" + model.size());

                groupsTickets = new ArrayList<>();
                for (Map.Entry<String, List<ticketsScanned>> entry : ticketGroups.entrySet()) {
                    groupsTickets.add(new gruposTickets(entry.getValue(), false));
                }

                if (consolidado) {
                    rvTickets.setVisibility(View.GONE);
                    rvTicketsG.setVisibility(View.VISIBLE);
                    fillAdapterG(groupsTickets, consolidado);
                } else {
                    fillAdapter(model, getContext(), consolidado);
                }
            }
           new Handler().postDelayed(new Runnable() {
               @Override
               public void run() {
                   adapterG.updateFlag(valAfterEvidence);
               }
           }, 1000);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e("listenerT", "onAttach ");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButton:
                //closeDialog();

                if (countok == model.size()) {//todo hasta igualar los empaques model.size()
                    if(sendEvidence) {
                        // Toast.makeText(getContext(), "ir a sellostodos fueron escaneados", Toast.LENGTH_SHORT).show();
                        if (typeScanner != null) {
                            if (typeScanner.equals("Recolectar")) {
                                //Toast.makeText(getContext(), "sumarydetailtickets", Toast.LENGTH_SHORT).show();
                                BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
                                barcodeScannerActivity1.detalManifestTicketsSummary(currentmanifest, codigoValidador, sellos);
                                closeDialog();
                            } else if (typeScanner.equals("Lotes")) {
                                Toast.makeText(getContext(), "  closeDialog(); pendiente", Toast.LENGTH_SHORT).show();
                            } else {//Salida
                                BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
                                barcodeScannerActivity1.goTicketsSummary();
                                closeDialog();
                            }

                        } else {
//                        if(typeScanner.equals("Lotes")){//todo esto esta ok
//                            Toast.makeText(getContext(), "  closeDialog(); pendiente", Toast.LENGTH_SHORT).show();
//                        }else {
                            Log.e("dialogSalida", "ticketssalida null pending review recolection");
                            BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
                            barcodeScannerActivity1.goTicketsSummary();
                            // }
                        }
                    }else {
                        if (consolidado) {
                           // Toast.makeText(getContext(), "Falta enviar las evidencias", Toast.LENGTH_SHORT).show();
                            Log.e("dataticketsSizeE","Falta enviar las evidencias");
                            List<Boolean> numTrue = new ArrayList<>();
                            numTrue.clear();
                            for (gruposTickets gt : groupsTickets) {
                                for (ticketsScanned ts : gt.getTickets()) {
                                    if (ts.getFlag() == true) {
                                        numTrue.add(ts.getFlag());
                                        // Exit the inner loop once a flagged ticket is found in the current group
                                    }
                                }
                            }
                            countByGropup = numTrue.size();
                            countok = countByGropup;
                            textChekcs.setText(countByGropup + "/" + model.size());
                            if(countok == model.size()){
                                //definir si aqui se elimina
                                BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
                                barcodeScannerActivity1.goTicketsSummary();
                                closeDialog();
                            }
                        }

                        // getActivity().finish();
//                        Intent intent = new Intent(getActivity(), evidenciasCarga.class);//todo revisar ya que esto es por todos
//                        Bundle bundle = new Bundle();
//                        bundle.putInt("flujoId", 1);
//                        bundle.putString("sentripPlusFlow", "Recoleccion");
//                        bundle.putString("currentManifest", currentmanifest);
//                        bundle.putString("folioTicket", null);
//                        bundle.putSerializable("dataTcikets", (Serializable) codigoValidador);
//                        intent.putExtras(bundle);
//                        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
                    }

                } else {
                    Toast.makeText(getContext(), "Faltan empaques por escanear", Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }

    @Override
    public void sendMessageEvidence(final Integer position) {//todo cambiar flujo para  que las instancias no sean nulas
        this.valAfterEvidence=position;
//        Log.e("listenerT", "valAfterEvidence " + position);
    }


}