package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.sellosSalida;

import android.content.Context;
import android.content.Intent;
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
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.sellosSalida.adapter.adapterSellosSalida;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.sellosSalida.model.sellosScanned;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.presenter.presenterSello;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.presenter.presenterSelloImpl;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.view.sellosSummaryView;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;
import com.fhl.sistemadedistribucionfh.evidence.evidencia;
import com.fhl.sistemadedistribucionfh.mlkit.BarcodeScannerActivity;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;

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
            currentManifest = args.getString("currentManifest");
            data = (List<dataTicketsManifestV2>) args.getSerializable("dataTcikets");
            sellos= (List<Sello>) args.getSerializable("sellos");
            flow= Integer.valueOf( args.getString("flowSellos"));
            Log.e("bottomSellos", "currentManifest "+currentManifest );
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
        rvReasons=view.findViewById(R.id.rvSellos);
        imageButton = view.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(this);
        textChekcs=view.findViewById(R.id.textChekcs);
        if(sellos==null){
            textChekcs.setVisibility(View.GONE); 
        }
        sellosAdd = view.findViewById(R.id.sellosAdd);
        sellosAdd.setOnClickListener(this);
        presenter= new presenterSelloImpl(this,getContext());
        presenter.requestManifestdetail(currentManifest);
        presenter.reqSellos(currentManifest);
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
    public void updateSellos(Sello data) {
        sellos.add(new Sello(data.getQrCodigo(), data.getNumeroSello(), Integer.valueOf(currentManifest), data.getId()));
        presenter.setSello(manifestId,sellos);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sellosAdd:
                if(createMore) {
                    if (sellos != null&&!sellos.isEmpty()) {
                       // adapter.updateSellos(new Sello("", "", Integer.valueOf(currentManifest), 0));
                    } else {
                        model.clear();
                        Log.e("bottomSellos", "adapter size" + sellos.size());
                        for(int i=0; i< sellos.size();i++){
                            model.add(new sellosScanned(sellos.get(i).getNumeroSello(),false));
                            Log.e("ticketsArray2", "model size: " + model.get(i).getFolio()+"  "+model.get(i).getFlag());
                        }
                        fillAdapter(model,getContext());
                     //   adapter.updateSellos(new Sello("", "", Integer.valueOf(currentManifest), 0));
                    }
                    createMore=false;
                }else{
                    if(sellos.size()!=0) {
                        Toast.makeText(getContext(), "Guarda el sello para poder agregar otro", Toast.LENGTH_SHORT).show();
                    }else{
                        createMore=true;
                        if (sellos != null) {
                          //  adapter.updateSellos(new Sello("", "", Integer.valueOf(currentManifest), 0));
                        } else {
                            model.clear();
                            Log.e("bottomSellos", "adapter size" + sellos.size());
                            for(int i=0; i< sellos.size();i++){
                                model.add(new sellosScanned(sellos.get(i).getNumeroSello(),false));
                                Log.e("ticketsArray2", "model size: " + model.get(i).getFolio()+"  "+model.get(i).getFlag());
                            }
                            fillAdapter(model,getContext());
                          //  adapter.updateSellos(new Sello("", "", Integer.valueOf(currentManifest), 0));
                            adapter.updateData(model);
                        }
                    }
                }
                break;
            case R.id.imageButton:
                //closeDialog();
                if(countok==model.size()) {
                    // Toast.makeText(getContext(), "ir a sellostodos fueron escaneados", Toast.LENGTH_SHORT).show();
                    BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
                    barcodeScannerActivity1.goSellosSummary();
                    closeDialog();

                }else{
                    Toast.makeText(getContext(), "faltan sellos por escanear", Toast.LENGTH_SHORT).show();

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
//                BarcodeScannerActivity barcodeScannerActivity1 = (BarcodeScannerActivity) getActivity();
//                barcodeScannerActivity1.errorTicket();
                adapter.updateSellos(new Sello("", "", Integer.valueOf(currentManifest), 0));

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
            dismiss();
        }
    }
    @Override
    public void saveManifestId(Integer id) {
        this.manifestId=id;
    }

    @Override
    public void seteginsellos(List<Sello> sellos) {
        if (sellos != null) {
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
        }
    }
}