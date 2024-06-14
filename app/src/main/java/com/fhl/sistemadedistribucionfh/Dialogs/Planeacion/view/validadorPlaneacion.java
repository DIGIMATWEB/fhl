package com.fhl.sistemadedistribucionfh.Dialogs.Planeacion.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fhl.sistemadedistribucionfh.Dialogs.EmpaquesValidador.validadorEmpaques;
import com.fhl.sistemadedistribucionfh.Dialogs.Planeacion.adapter.adapterPlaneacionEmpaques;
import com.fhl.sistemadedistribucionfh.Dialogs.Planeacion.presenter.presenterPlaneacion;
import com.fhl.sistemadedistribucionfh.Dialogs.Planeacion.presenter.presenterPlaneacionImpl;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.model.ticketsScanned;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.Paquete;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.SendtripPlus;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.dataTicketsDetailsendtrip;
import com.fhl.sistemadedistribucionfh.mlkit.BarcodeScannerActivity3;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class validadorPlaneacion extends DialogFragment implements View.OnClickListener ,validadorPlaneacionView{
    public static final String TAG = validadorEmpaques.class.getSimpleName();
    private RecyclerView rvReasons, rv;
    private adapterPlaneacionEmpaques adapter;
    //   private adapterTicketsSalidaEmpaques adapter2;
//    private dialogReasonsPresenter presenter;
    private ImageView closeReasons;

    private List<dataTicketsDetailsendtrip> codigoValidador;
    private List<Sello> sellos;
    private ImageButton imageButton;
    private TextView textChekcs;
    private Integer countok = 0;
    private Integer countokEmpaques = 0;
    private String typeScanner, currentmanifest;
    private TextView recoleccion, textEmpaques;
    private List<ticketsScanned> fresult;
    private presenterPlaneacion presenter;
    private List<Paquete> lotes;
    private List<ticketsScanned> ticketsLocal;
    private List<ticketsScanned> model = new ArrayList<>();
    private SendtripPlus sendtripPlusLocal;
    private Boolean afterScan=false;
    private Integer statusPlaneacion=0;
    private ConstraintLayout constraintLayout5,constraintLayout6,inputkeyscode;
    private EditText escribircodigo;
    private ImageView inputcamara,inputmanual,imageView27;
    private Button captureCode;
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

        getDialog().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.alfa);
        setCancelable(true);

        view.setFocusableInTouchMode(true);
        view.requestFocus();

        // This line is necessary to show the keyboard
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        initDialog(view);
        fillAdapter(ticketsLocal, getContext(), statusPlaneacion);

        //setFonts();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimationBottonSheet;
    }

    private void initDialog(View view) {
        ticketsLocal=new ArrayList<>();
        constraintLayout5 = view.findViewById(R.id.constraintLayout5);
        constraintLayout6 = view.findViewById(R.id.constraintLayout6);
        constraintLayout5.setOnClickListener(this);
        constraintLayout6.setOnClickListener(this);
        imageView27= view.findViewById(R.id. imageView27);
        imageView27.setOnClickListener(this);
        captureCode = view.findViewById(R.id. captureCode);
        captureCode.setOnClickListener(this);
        escribircodigo = view.findViewById(R.id.escribircodigo);
        inputkeyscode= view.findViewById(R.id. inputkeyscode);
        inputcamara= view.findViewById(R.id.inputcamara);
        inputmanual= view.findViewById(R.id.inputmanual);
        recoleccion = view.findViewById(R.id.textView66);
        textEmpaques = view.findViewById(R.id.textEmpaques);
        rvReasons = view.findViewById(R.id.rvTicketsSalidaL);
        rv = view.findViewById(R.id.rvTicketsEmpaques);
        imageButton = view.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(this);
        textChekcs = view.findViewById(R.id.textChekcs);
        recoleccion.setText("Tickets");
        escribircodigo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing before the text changes
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Respond to text changes if needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Validate the input or perform actions after the text has changed
                if (!s.toString().isEmpty()) {
                    // Perform actions when there is text in the EditText
                    // For example, enable a button or change the UI
                } else {
                    // Perform actions when the EditText is empty
                    // For example, disable a button or change the UI
                }
            }
        });
       // imageButton.setVisibility(View.GONE);
        textChekcs.setText("Escanea un ticket");
        presenter= new presenterPlaneacionImpl(this,getContext());
    }

    private void fillAdapter(List<ticketsScanned> data, Context context, Integer statusPlaneacion) {
        adapter = new adapterPlaneacionEmpaques(this, data, context,statusPlaneacion);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvReasons.setLayoutManager(linearLayoutManager);
        rvReasons.setAdapter(adapter);
        //fillAdapter2(data,context);
    }

    public void nullempaquesCheckticket(String folioTicket) {
        for (ticketsScanned ticket : ticketsLocal) {
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
        if(!afterScan){
            Log.e("mCodeScan", "" + code);
            ticketsLocal.add(new ticketsScanned(code, false, new SendtripPlus("", "", "", "", "", 0, null, 0, null, 0, null)));
            presenter.getTicketData(code);
            textChekcs.setVisibility(View.VISIBLE);
        }else{   //si existe ingotmacions
                // Log.e("ticketsArray2", "comprueva el modelo " + model.size() + " comprueba el codigo " + code);
                boolean codeFound = false;

                for (ticketsScanned ticket : ticketsLocal) {             //se usca por ticket
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
                    BarcodeScannerActivity3 barcodeScannerActivity1 = (BarcodeScannerActivity3) getActivity();
                    barcodeScannerActivity1.errorTicket();
                }
                if (adapter != null) {
                    adapter.updateData(ticketsLocal);
                    adapter.notifyDataSetChanged();
                }

            }


    }

    public void updatescanedData(List<ticketsScanned> data) {
        textChekcs.setVisibility(View.VISIBLE);
        List<Paquete> lotes = new ArrayList<>();
        lotes.clear();
        for (ticketsScanned b : data) {
            if (b.getFlag()) {
                if (countok == ticketsLocal.size()) {

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
       // textChekcs.setText(countok + "/" + ticketsLocal.size());
    }

    @Override
    public void setValidadorResponse(String answer, Integer value) {
        this.statusPlaneacion=value;
        try {
            // Parse the original JSON array
            JSONArray originalArray = new JSONArray(answer);

            // Create a new JSON object to hold the final structure
            JSONObject result = new JSONObject();
            JSONArray paquetesArray = new JSONArray();

            // Loop through the original array and build the new structure
            for (int i = 0; i < originalArray.length(); i++) {
                JSONObject originalObject = originalArray.getJSONObject(i);
                originalObject.remove("Destinatarios");
                originalObject.remove("Remitente");
                paquetesArray.put(originalObject);
            }

            // Add the paquetes array to the result object
            result.put("Paquetes", paquetesArray);

            // Log the final JSON string
            Gson gson = new Gson();
            List<Paquete> lotes = gson.fromJson(result.getJSONArray("Paquetes").toString(), new TypeToken<List<Paquete>>() {}.getType());
            if(lotes!=null) {
                this.lotes=lotes;
                ticketsLocal.get(0).getSendtripPlus().setPaquetes(lotes);

                if(statusPlaneacion==2){
                    countok=ticketsLocal.size();
                    textChekcs.setText(ticketsLocal.size() + "/" + ticketsLocal.size());
                }else{
                    textChekcs.setText(0 + "/" + ticketsLocal.size());
                }
                fillAdapter(ticketsLocal, getContext(), statusPlaneacion);
            }
            // Log the list to verify
            for (Paquete paquete : lotes) {
                Log.e("mCodeScan", paquete.getNombre());
            }
            afterScan=true;

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("mCodeScan", "JSON parsing error: " + e.getMessage());
        }
    }

    @Override
    public void failResponse() {
        BarcodeScannerActivity3 barcodeScannerActivity1 = (BarcodeScannerActivity3) getActivity();
        barcodeScannerActivity1.errorTicket();
        afterScan=false;
        ticketsLocal.clear();
        adapter.updateData(ticketsLocal);
        adapter.notifyDataSetChanged();
    }

    public void setVisibleConfirm() {
        imageButton.setVisibility(View.VISIBLE);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView27:
                inputkeyscode.setVisibility(View.GONE);
                inputcamara.setBackgroundResource(R.drawable.icscannercamblack);
                inputmanual.setBackgroundResource(R.drawable.ic_keys_black);
                break;
            case R.id.constraintLayout6://camera
                inputkeyscode.setVisibility(View.GONE);
                inputcamara.setBackgroundResource(R.drawable.icscannercamblack);
                inputmanual.setBackgroundResource(R.drawable.ic_keys_black);
                //binding.headerText.setTextColor(Color.WHITE);
                break;

            case R.id.constraintLayout5://manual
                //Toast.makeText(this, "Input manual", Toast.LENGTH_SHORT).show();
                inputkeyscode.setVisibility(View.VISIBLE);
                inputcamara.setBackgroundResource(R.drawable.icscannercamblack);
                inputmanual.setBackgroundResource(R.drawable.ic_keys_black);
                // binding.headerText.setTextColor(Color.BLACK);
                break;
            case R.id.captureCode:
                if(!escribircodigo.getText().toString().equals("")) {
                    BarcodeScannerActivity3 barcodeScannerActivity1 = (BarcodeScannerActivity3) getActivity();
                    barcodeScannerActivity1.sendScannedCode(escribircodigo.getText().toString());
                    escribircodigo.setText("");
                    inputkeyscode.setVisibility(View.GONE);
                }else{
                    Toast.makeText(getContext(), "Debes resgistrar datos", Toast.LENGTH_SHORT).show();
                    inputkeyscode.setVisibility(View.GONE);
                }
                break;
            case R.id.imageButton:
                //closeDialog();// BarcodeScannerActivity2 barcodeScannerActivity1 = (BarcodeScannerActivity2) getActivity();
                //                                                        barcodeScannerActivity1.returnResult("1234");
                if(countok!=0) {
                    if (countok == ticketsLocal.size()) {//todo hasta igualar los empaques model.size()
                        //Toast.makeText(getContext(), "Escaneaste todos los paquetes", Toast.LENGTH_SHORT).show();
                        BarcodeScannerActivity3 barcodeScannerActivity1 = (BarcodeScannerActivity3) getActivity();
                        //barcodeScannerActivity1.returnResult("Escaneaste todos los paquetes");
                        if(statusPlaneacion!=2) {
                            presenter.setStatusTicket(ticketsLocal.get(0).getFolio(), 2);
                            barcodeScannerActivity1.returnResult("Escaneaste todos los paquetes");
                        }else{
                            barcodeScannerActivity1.returnResult("Este ticket ya habia sido escaneado");
                        }
                        //TODO se reinicia el ciclo
                        countok=0;
                        afterScan=false;
                        statusPlaneacion=0;
                        //oton confirmar
                        textChekcs.setText("Escanea un ticket");
                        ticketsLocal.clear();
                        adapter.updateData(ticketsLocal);
                        adapter.notifyDataSetChanged();

                    } else {
                        if(ticketsLocal.isEmpty()){
                            //Toast.makeText(getContext(), "No hay ningun ticket por escanear", Toast.LENGTH_SHORT).show();
                            BarcodeScannerActivity3 barcodeScannerActivity1 = (BarcodeScannerActivity3) getActivity();
                            barcodeScannerActivity1.returnResult("No hay ningun ticket por escanear");
                        }else{
                            // Toast.makeText(getContext(), "No haz escaneado todos los lotes", Toast.LENGTH_SHORT).show();
                            BarcodeScannerActivity3 barcodeScannerActivity1 = (BarcodeScannerActivity3) getActivity();
                            barcodeScannerActivity1.returnResult("No escaneaste todos los paquetes");
                        }

                    }
                }else{
                    if(ticketsLocal.isEmpty()){
                        Toast.makeText(getContext(), "No hay ningun ticket por escanear", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "No haz escaneado todos los lotes", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
        }
    }
}