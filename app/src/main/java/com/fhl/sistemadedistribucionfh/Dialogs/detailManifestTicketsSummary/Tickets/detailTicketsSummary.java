package com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Tickets;

import android.os.Bundle;
import android.view.KeyEvent;
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

import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.sellosSummary;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Tickets.Adapter.adapterTicketsManifestDetail;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Salida.Model.v2.dataSalida;
import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;
import com.fhl.sistemadedistribucionfh.mlkit.BarcodeScannerActivity;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;

import java.io.Serializable;
import java.util.List;

public class detailTicketsSummary extends DialogFragment implements View.OnClickListener{
        public static final String TAG = detailTicketsSummary.class.getSimpleName();
        private adapterTicketsManifestDetail adapter;
        private String currentManifest;
        private  List<dataTicketsManifestV2> data;
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
                View view = inflater.inflate(R.layout.dialog_tickets_detaio_manifest, container, false);
                getDialog().getWindow().setBackgroundDrawableResource(R.color.customTransparent2);
                setCancelable(true);
                Bundle args = getArguments();
                initView(view);
                if (args != null) {
                currentManifest = args.getString("currentManifest");
                data= (List<dataTicketsManifestV2>) args.getSerializable("dataTcikets");
                sellos = (List<Sello>) args.getSerializable("sellos");
                if(data!=null){
                        fillTicketsRV();
                }
                }

                //setFonts();
                return view;
        }

        private void initView(View view) {
                rv=view.findViewById(R.id.rvTickets);
                imageButton=view.findViewById(R.id. imageButton);
                imageButton.setOnClickListener(this);
        }
        @Override
        public void onResume() {
                super.onResume();
                getDialog().setOnKeyListener((dialog, keyCode, event) -> {
                        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                                BarcodeScannerActivity barcodeScannerActivity = (BarcodeScannerActivity) getActivity();
                                barcodeScannerActivity.onBackPressed();
                                return true; // Change to 'false' if you want the dialog to close when back is pressed
                        }
                        return false;
                });
        }
        private void fillTicketsRV() {
                adapter = new adapterTicketsManifestDetail(this,data,getContext());
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        rv.setLayoutManager(linearLayoutManager);
                        rv.setAdapter(adapter);
        }

        @Override
        public void onClick(View v) {
                switch (v.getId()) {
                        case R.id.imageButton:
                                //Toast.makeText(getContext(), "isr a sellos sumarry", Toast.LENGTH_SHORT).show();
                                Bundle bundle = new Bundle();
                                bundle.putString("currentManifest", currentManifest);
                                bundle.putSerializable("dataTcikets",(Serializable) data);
                                bundle.putSerializable("sellos",(Serializable) sellos);
                                sellosSummary bottomSheet = new sellosSummary();
                                bottomSheet.setArguments(bundle);
                                bottomSheet.show(getParentFragmentManager(), "sellosSummary");

                                break;
                }
        }
}
