package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.sellosSalida;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.Dialogs.Loader.view.loaderFH;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.sellosSalida.adapter.SwipeToDeleteCallback2;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.sellosSalida.adapter.adapterSellosSalida;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.sellosSalida.model.sellosScanned;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.Adapter.SwipeToDeleteCallback;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.presenter.presenterSello;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.presenter.presenterSelloImpl;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.view.sellosSummaryView;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;
import com.fhl.sistemadedistribucionfh.evidence.evidencia;
import com.fhl.sistemadedistribucionfh.mlkit.BarcodeScannerActivity;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class sellosSalida extends DialogFragment implements View.OnClickListener, sellosSummaryView {
    public static final String TAG = sellosSalida.class.getSimpleName();
    private RecyclerView rvReasons;
    private adapterSellosSalida adapter;
    private ImageView closeReasons;
    private List<sellosScanned> model=new ArrayList<>();
    private  List<Sello> sellos;
    private ImageButton imageButton;
    private TextView textChekcs;
    private Integer countok=0;
    private String currentManifest;
    private List<dataTicketsManifestV2> data;
    private Integer flow=0;
    private presenterSello presenter;
    private Boolean control = false;
    private Integer manifestId=0;
    private CardView sellosAdd;
    private Boolean createMore=true;
    private loaderFH progress;
    private ImageView lamps;
    private boolean isFlashOn = false;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);

    }
    @Override
    public void showDialog() {
        if (progress != null && !progress.isVisible()) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("HAS_TITLE", false);
            bundle.putString("title", "Cargando detalles");
            progress.setArguments(bundle);
            progress.show(getChildFragmentManager(), loaderFH.TAG);
        }

    }
    @Override
    public void hideDialog() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (progress != null && this != null)
                    if(progress.isAdded()) {
                        progress.dismiss();
                    }
            }
        }, 2000);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_sellos, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.alfa);
        setCancelable(true);
        progress = new loaderFH();
        Bundle args = getArguments();
        if (args != null) {
            currentManifest = args.getString("currentManifest");
            data = (List<dataTicketsManifestV2>) args.getSerializable("dataTcikets");
            sellos= (List<Sello>) args.getSerializable("sellos");
            flow= Integer.valueOf( args.getString("flowSellos"));
            Log.e("bottomSellos", "currentManifest "+currentManifest );
            Log.e("bottomSellos", "flow "+flow );
            if (data != null) {
                Log.e("dataSellosE", "sellos folio " + data.get(0) + " data " + data.size());
            } else {
                Log.e("dataSellosE", "sellos folio " + " data : null");
            }
        }
        if(data!=null){
            Gson gson=new Gson();
            String json=gson.toJson(data);
            Log.e("ticketsEredadosSellos",json);
        }
        initDialog(view);
        if(sellos!=null) {
            model.clear();
            Log.e("bottomSellos", "adapter size" + sellos.size());
            for(int i=0; i< sellos.size();i++){
                model.add(new sellosScanned(sellos.get(i).getNumeroSello(),false));
                Log.e("ticketsArray2", "model size: " + model.get(i).getFolio()+"  "+model.get(i).getFlag());
            }
            textChekcs.setText("0/"+model.size());
            fillAdapter(model,getContext());
        }//setFonts();
        else {
            sellos=new ArrayList<>();
            Log.e("bottomSellos", "sellos null" );
           // dismiss();
        }
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimationBottonSheet;
    }
    private void initDialog(View view) {
        lamps =view.findViewById(R.id.lamps);
        lamps.setOnClickListener(this);
        rvReasons=view.findViewById(R.id.rvSellos);
        imageButton = view.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(this);
        textChekcs=view.findViewById(R.id.textChekcs);
        if(sellos==null){
            textChekcs.setVisibility(View.GONE); 
        }
        presenter= new presenterSelloImpl(this,getContext());
        presenter.requestManifestdetail(currentManifest);
        presenter.reqSellos(currentManifest);

    }

    private void fillAdapter(List<sellosScanned> data, Context context) {
        if (adapter == null) {
        adapter = new adapterSellosSalida(this,data,context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvReasons.setLayoutManager(linearLayoutManager);
        rvReasons.setAdapter(adapter);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback2(adapter));
            itemTouchHelper.attachToRecyclerView(rvReasons);
        } else {
            adapter.updateData(data);
        }
    }
    public void letCreateMore(Boolean createMore) {
        boolean check=true;
        for(Sello s:sellos){
            if(s.getNumeroSello()==null||s.getNumeroSello().isEmpty()){
                check=false;
            }
        }
        if(check) {
            this.createMore = createMore;
        }else {
            Toast.makeText(getContext(), "Debes guardar el sello antes de crear otro", Toast.LENGTH_SHORT).show();
        }
    }
    public void closeDialog() {
        this.dismiss();
    }
    public void updateSellosR(List<sellosScanned> data) {//todo llenar sellos con lo de model
        adapter.updateData(data);
//        model.clear();
//        for(sellosScanned s:data){
//            model.add(s);
//        }
////        for(int i=0; i< sellos.size();i++){
////            model.add(new sellosScanned(sellos.get(i).getNumeroSello(),false));
////        }
//
//        List<Sello> ns=new ArrayList<>();
//        ns.clear();
//        for(sellosScanned sf:model){
//
//            ns.add(new Sello("", sf.getFolio(), Integer.valueOf(currentManifest),1));
//        }

    }
    public void updateSellos(Sello data) {
        sellos.add(new Sello(data.getQrCodigo(), data.getNumeroSello(), Integer.valueOf(currentManifest), data.getId()));
        presenter.setSello(manifestId,sellos);
    }

    private void showDialog(String value) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Confirma la acción")
                .setMessage(value)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        if (sellos != null) {
                            if (sellos.isEmpty()) {
                                Log.e("dataSellosE", "sellos.isEmpty()");
                                goEvidence();
                            } else {
                                Log.e("dataSellosE", "sellos.NotEmpty()");
                                //presenter.mandarSellos
                                for (int i = sellos.size() - 1; i >= 0; i--) {
                                    Sello sello = sellos.get(i);
                                    if (sello.getNumeroSello() == null || sello.getNumeroSello().isEmpty()) {
                                        sellos.remove(i);
                                    }
                                }
                                model.clear();
                                Log.e("dataSellos", "adapter size" + sellos.size());
                                for(int i=0; i< sellos.size();i++){
                                    model.add(new sellosScanned(sellos.get(i).getNumeroSello(),false));
                                    Log.e("dataSellos", "model size: " + model.get(i).getFolio()+"  "+model.get(i).getFlag());
                                }
                                adapter.updateData(model);
                               // if (control) {

                                    //BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
                                   // barcodeScannerActivity1.goSellosSummary();
                                    //closeDialog();
                                    presenter.setSello(manifestId,sellos);
                             //   }
                               // goEvidence();
                            }
                        } else {
                            goEvidence();
                            Log.e("dataSellos", "go evidence)");
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.dismiss();

                    }
                });

        // Create the AlertDialog object and return it
        AlertDialog dialog = builder.create();
        dialog.show();
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
//                BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
//                barcodeScannerActivity1.errorTicket();//todo verificar el array de sellos cuando null   FATAL EXCEPTION: main
//                        Process: com.fhl.sistemadedistribucionfh, PID: 28651
//                        java.lang.NullPointerException: Attempt to invoke virtual method 'void com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.sellosSalida.adapter.adapterSellosSalida.updateSellos(com.fhl.sistemadedistribucionfh.Sellos.model.Sello)' on a null object reference
//
             //   adapter.updateSellos(new Sello("", "", Integer.valueOf(currentManifest), 0));
                if(sellos.isEmpty()){
                    sellos.add(new Sello("", code, Integer.valueOf(currentManifest), 0));
                    model.clear();
                    Log.e("bottomSellos", "adapter size" + sellos.size());
                    for(int i=0; i< sellos.size();i++){
                        model.add(new sellosScanned(sellos.get(i).getNumeroSello(),false));
                        Log.e("ticketsArray2", "model size: " + model.get(i).getFolio()+"  "+model.get(i).getFlag());
                    }
                    fillAdapter(model,getContext());
                }else {
                    sellos.add(new Sello("", code, Integer.valueOf(currentManifest), 0));
                    model.clear();
                    Log.e("bottomSellos", "adapter size" + sellos.size());
                    for(int i=0; i< sellos.size();i++){
                        model.add(new sellosScanned(sellos.get(i).getNumeroSello(),false));
                        Log.e("ticketsArray2", "model size: " + model.get(i).getFolio()+"  "+model.get(i).getFlag());
                    }
                    adapter.updateData(model);
                }

            }else{
                Toast.makeText(getContext(), "El sello ya fue escaneado", Toast.LENGTH_SHORT).show();
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

    @Override
    public void setMessageSello() {
        goEvidence();
    }
    public void goEvidence() {
        //  Toast.makeText(getContext(), "ir a evidencias", Toast.LENGTH_SHORT).show();
        if(flow==1) {
            if (data != null) {
                Log.e("dataSellosE", "folio " + data.get(0) + " data " + data.size());
            } else {
                Log.e("dataSellosE", " data : null");
            }
            getActivity().finish();
            Intent intent = new Intent(getActivity(), evidencia.class);
            Bundle bundle = new Bundle();
            bundle.putInt("flujoId", 1);
            bundle.putString("sentripPlusFlow", "Recoleccion");
            bundle.putString("currentManifest", currentManifest);
            bundle.putString("folioTicket", null);
            bundle.putSerializable("dataTcikets", (Serializable) data);
            intent.putExtras(bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else{
            Log.e("dataSellosE", "flow "+flow);
            BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
            if (barcodeScannerActivity1 != null) {
                barcodeScannerActivity1.goSellosSummary();
                closeDialog();
                //presenter.setSello(manifestId, sellos);
            } else {
                // Handle the case where the activity is null
                Log.e("YourTag", "Activity is null");
            }
            //presenter.setSello(manifestId,sellos);
        }
    }
    @Override
    public void saveManifestId(Integer id) {
        this.manifestId=id;
    }

    @Override
    public void seteginsellos(List<Sello> msellos) {
            this.sellos=msellos;
        if (sellos != null&&!sellos.isEmpty()) {
            this.sellos = sellos;
            Log.e("QR", "nSellos: " + sellos.size()); // Debug log
            this.control=true;
            model.clear();
            Log.e("bottomSellos", "adapter size" + sellos.size());
            for(int i=0; i< sellos.size();i++){
                model.add(new sellosScanned(sellos.get(i).getNumeroSello(),false));
                Log.e("ticketsArray2", "model size: " + model.get(i).getFolio()+"  "+model.get(i).getFlag());
            }
            fillAdapter(model,getContext());
        } else {
            Log.e("QR", "sellos is null");
            Toast.makeText(getContext(), "No cuentas con sellos", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //region old button add
//            case R.id.sellosAdd:
//                if(createMore) {
//                    if (sellos != null&&!sellos.isEmpty()) {
//                       // adapter.updateSellos(new Sello("", "", Integer.valueOf(currentManifest), 0));
//                    } else {
//                        model.clear();
//                        Log.e("bottomSellos", "adapter size" + sellos.size());
//                        for(int i=0; i< sellos.size();i++){
//                            model.add(new sellosScanned(sellos.get(i).getNumeroSello(),false));
//                            Log.e("ticketsArray2", "model size: " + model.get(i).getFolio()+"  "+model.get(i).getFlag());
//                        }
//                        fillAdapter(model,getContext());
//                     //   adapter.updateSellos(new Sello("", "", Integer.valueOf(currentManifest), 0));
//                    }
//                    createMore=false;
//                }else{
//                    if(sellos.size()!=0) {
//                        Toast.makeText(getContext(), "Guarda el sello para poder agregar otro", Toast.LENGTH_SHORT).show();
//                    }else{
//                        createMore=true;
//                        if (sellos != null) {
//                          //  adapter.updateSellos(new Sello("", "", Integer.valueOf(currentManifest), 0));
//                        } else {
//                            model.clear();
//                            Log.e("bottomSellos", "adapter size" + sellos.size());
//                            for(int i=0; i< sellos.size();i++){
//                                model.add(new sellosScanned(sellos.get(i).getNumeroSello(),false));
//                                Log.e("ticketsArray2", "model size: " + model.get(i).getFolio()+"  "+model.get(i).getFlag());
//                            }
//                            fillAdapter(model,getContext());
//                          //  adapter.updateSellos(new Sello("", "", Integer.valueOf(currentManifest), 0));
//                            adapter.updateData(model);
//                        }
//                    }
//                }
//                break;
            //endregion
            case R.id.imageButton:
                //closeDialog();
                if(sellos.isEmpty()){
                    showDialog("¿Quieres continuar sin sellos?");
                }else {
                    showDialog("¿Guardar los siguientes sellos?");
                }
                break;
            case R.id.lamps:
                BarcodeScannerActivity barcodeScannerActivityt = (BarcodeScannerActivity) getActivity();
                barcodeScannerActivityt.toggleFlash();
                if (isFlashOn) {
                    // Turn off flash
                    isFlashOn = false;
                    lamps.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.lamparaoff));
                } else {
                    // Turn on flash
                    isFlashOn = true;
                    lamps.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.lamparaon));
                }
                break;
        }
    }


    public void currentManifst(String currentmanifest) {
    }

    public void setSellos(List<Sello> dataSellos) {
    }

    public void setTickets(List<dataTicketsManifestV2> dataTickets) {
        this.data=dataTickets;
    }
}