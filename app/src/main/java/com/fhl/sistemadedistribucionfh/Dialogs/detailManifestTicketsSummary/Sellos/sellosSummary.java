package com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.Adapter.adapterSellosManifestDetail;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Tickets.Adapter.adapterTicketsManifestDetail;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Tickets.detailTicketsSummary;
import com.fhl.sistemadedistribucionfh.MainActivity;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Salida.Model.v2.dataSalida;
import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;
import com.fhl.sistemadedistribucionfh.evidence.evidencia;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class sellosSummary extends DialogFragment implements View.OnClickListener{
    public static final String TAG = sellosSummary.class.getSimpleName();
    private adapterSellosManifestDetail adapter;
    private String currentManifest;
    private List<dataTicketsManifestV2> data;
    private RecyclerView rv;
    private ImageButton imageButton;
    private List<Sello> sellos;
    private CardView sellosAdd;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_sellos_detail_manifest, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.customTransparent2);
        setCancelable(true);
        Bundle args = getArguments();
        initView(view);
        if (args != null) {
            currentManifest = args.getString("currentManifest");
            data= (List<dataTicketsManifestV2>) args.getSerializable("dataTcikets");
            sellos = (List<Sello>) args.getSerializable("sellos");
            if(sellos!=null){
                fillTicketsRV();
            }
        }
        if(sellos!=null){

        }else {
            Toast.makeText(getContext(), "No tienes sellos", Toast.LENGTH_SHORT).show();
        }

        //setFonts();
        return view;
    }

    private void initView(View view) {
        rv=view.findViewById(R.id.rvTickets);
        imageButton=view.findViewById(R.id. imageButton);
        imageButton.setOnClickListener(this);
        sellosAdd=view.findViewById(R.id.sellosAdd);
        sellosAdd.setOnClickListener(this);

    }

    private void fillTicketsRV() {
        if (sellos == null) {
            sellos = new ArrayList<>();
        }
        adapter = new adapterSellosManifestDetail(this,sellos,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
    }
    private void showDialog(String value) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Confirma la accion")
                .setMessage(value)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        goEvidence();
                        dialog.dismiss();
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
    public void goEvidence(){
        Toast.makeText(getContext(), "ir a evidencias", Toast.LENGTH_SHORT).show();
        //                getActivity().finish();
//                Intent intent = new Intent(getActivity(), evidencia.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("flujoId", 1);
//                bundle.putString("sentripPlusFlow","Recoleccion");
//                bundle.putString("currentManifest",currentManifest);
//                bundle.putString("folioTicket", null);
//                bundle.putSerializable("dataTcikets",(Serializable) data);
//                intent.putExtras(bundle);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
    }

    public void updateSellos(List<Sello> data) {
        this.sellos=data;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sellosAdd:
                if(sellos!=null){
                    adapter.updateSellos(new Sello("", "", Integer.valueOf(currentManifest), 0));
                }else {
                    fillTicketsRV();
                    adapter.updateSellos(new Sello("", "", Integer.valueOf(currentManifest), 0));
                }
                break;
            case R.id.imageButton:
               // Toast.makeText(getContext(), "Modificar flujo de sellos", Toast.LENGTH_SHORT).show();
                if(sellos!=null) {
                    if(sellos.isEmpty()) {
                        showDialog("Quieres continuar sin sellos");
                    }else{
                        showDialog("Guardar los siguientes sellos");
                        //presenter.mandarSellos
                    }
                }else{

                    showDialog("Guardar los siguientes sellos");
                    //presenter.mandarSellos
                }
                break;
        }
    }

}