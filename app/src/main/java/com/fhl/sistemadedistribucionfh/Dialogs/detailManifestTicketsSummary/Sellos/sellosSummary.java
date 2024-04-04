package com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.Adapter.adapterSellosManifestDetail;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Tickets.Adapter.adapterTicketsManifestDetail;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Tickets.detailTicketsSummary;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Salida.Model.v2.dataSalida;
import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;
import com.fhl.sistemadedistribucionfh.evidence.evidencia;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;

import java.io.Serializable;
import java.util.List;

public class sellosSummary extends DialogFragment implements View.OnClickListener{
    public static final String TAG = sellosSummary.class.getSimpleName();
    private adapterSellosManifestDetail adapter;
    private String currentManifest;
    private List<dataTicketsManifestV2> data;
    private RecyclerView rv;
    private ImageButton imageButton;
    private List<Sello> sellos;
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

    }

    private void fillTicketsRV() {
        adapter = new adapterSellosManifestDetail(sellos,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButton:
                //Toast.makeText(getContext(), "ir a evidences", Toast.LENGTH_SHORT).show();
                getActivity().finish();
                Intent intent = new Intent(getActivity(), evidencia.class);
                Bundle bundle = new Bundle();
                bundle.putInt("flujoId", 1);
                bundle.putString("currentManifest",currentManifest);
                bundle.putString("folioTicket", null);
                bundle.putSerializable("dataTcikets",(Serializable) data);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                break;
        }
    }
}