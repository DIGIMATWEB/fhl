package com.fhl.sistemadedistribucionfh.nmanifestDetail.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.Dialogs.Loader.view.loaderFH;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;
import com.fhl.sistemadedistribucionfh.Tickets.view.tickets;
import com.fhl.sistemadedistribucionfh.mlkit.BarcodeScannerActivity;
import com.fhl.sistemadedistribucionfh.nmanifest.viewV2.mmanifestV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.adapter.adapterManifestDetails;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.presenter.presenterTicketsManifestImplV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.presenter.presenterTicketsmanifestV2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class manifestDetailV2 extends Fragment implements View.OnClickListener, viewManifestDetailsV2 {
    public static final String TAG = manifestDetailV2.class.getSimpleName();
    private RecyclerView rvlistTickets;
    private adapterManifestDetails adapter;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private SearchView searchViewManifestdetail;
    private ImageView searchicodetail;
    private String folioDespachoId, vehiculoModeloId, vehiculoPlacaId, cedisId,statusManifest;
    private List<dataTicketsManifestV2> data;
    private List<Sello> dataSellos;
    private presenterTicketsmanifestV2 presenter;
    private TextView vehicleManifiesto, vehicleName, vehiclePlaca, vehicleCedis,status;
    private ImageButton recoletar;
    private loaderFH progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manifest_detailsv2, container, false);
        Bundle bundle = this.getArguments();

        if(bundle != null){
            // handle your code here.
            folioDespachoId = bundle.getString("folioDespachoId");
            vehiculoModeloId = bundle.getString("vehiculoModeloId");
            vehiculoPlacaId = bundle.getString("vehiculoPlacaId");
            cedisId = bundle.getString("cedisId");
            statusManifest= bundle.getString("statusManifest");
            Log.e("midManifest","" + folioDespachoId);


        }
        initView(view);
        return view;
    }

    private void initView(View view) {
        rvlistTickets = view.findViewById(R.id.rvlistTickets);
        searchViewManifestdetail = view.findViewById(R.id.searchViewManifestdetail);
        searchicodetail = view.findViewById(R.id.searchicodetail);
        searchicodetail.setOnClickListener(this);
        recoletar=view.findViewById(R.id.recoletar);
        recoletar.setOnClickListener(this);
        vehicleManifiesto = view.findViewById(R.id.numeroManifiesto2);
        vehicleName = view.findViewById(R.id.vehicle_namev2);
        vehiclePlaca = view.findViewById(R.id.placa_text);
        vehicleCedis = view.findViewById(R.id.textView78);
        status=view.findViewById(R.id.statusManifest);
        status.setText(statusManifest);
        if (statusManifest.equals("Confirmado")) {
            status.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
        } else if (statusManifest.equals("En proceso")) {
            status.setTextColor(ContextCompat.getColor(getContext(), R.color.yellowdark));
        } else if (statusManifest.equals("Cerrado")) {
            status.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
        } else {
            status.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        }
        vehicleManifiesto.setText(folioDespachoId);
        vehicleName.setText(vehiculoModeloId);
        vehiclePlaca.setText(vehiculoPlacaId);
        vehicleCedis.setText(cedisId);
        progress = new loaderFH();
        presenter= new presenterTicketsManifestImplV2(this,getContext());
        presenter.getTickets(folioDespachoId);
        if(statusManifest.equals("En proceso")){
            recoletar.setVisibility(View.GONE);
        }

        //setAdapter(data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchicodetail:
                if(searchViewManifestdetail.getVisibility()==View.GONE) {
                    searchViewManifestdetail.setVisibility(View.VISIBLE);
                    searchViewManifestdetail.setQueryHint("Buscar ticket");
                    Drawable background= getContext().getDrawable(R.drawable.shape_button);
                    searchViewManifestdetail.setIconified(false);
                    searchViewManifestdetail.setBackground(background);
                    searchViewManifestdetail.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            List<dataTicketsManifestV2> filterList = filter(data,newText);
                            adapter.setFilter(filterList);
                            return true;
                        }
                    });
                    searchViewManifestdetail.setOnCloseListener(new SearchView.OnCloseListener() {
                        @Override
                        public boolean onClose() {
                            searchViewManifestdetail.setVisibility(View.GONE);
                            return false;
                        }
                    });
                }else{
                    searchViewManifestdetail.setVisibility(View.GONE);
                }

                break;
            case R.id.recoletar:
               // Toast.makeText(getContext(), "recolectar", Toast.LENGTH_SHORT).show();
                List<dataTicketsManifestV2> fdata= new ArrayList<>();
                fdata.clear();
                if(data!=null) {
                    for (dataTicketsManifestV2 mdata:data){//este metodo se usa para filtrar los tickets que ya se han cerrado
                        if(mdata.getEstatusId()==2);{
                            if(mdata.getEstatus().getId()==2) {
                                fdata.add(mdata);
                            }
                        }
                    }

                    Bundle bundle = new Bundle();
                    bundle.putString("scannerType", "Recolectar");
                    bundle.putString("manifest", folioDespachoId);
                    bundle.putSerializable("tickets", (Serializable) fdata);
                    bundle.putSerializable("sellos", (Serializable) dataSellos);
                    Intent intent = new Intent(getActivity(), BarcodeScannerActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(), "No tienes tickets para recolectar", Toast.LENGTH_SHORT).show();
                }
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("tickets", (Serializable) data);
//                ticketsSalida tickets = new ticketsSalida();
//                tickets.setArguments(bundle);
//                tickets.show(getParentFragmentManager(), "ticketsSalida");

                break;
        }
    }
    private void setAdapter(List<dataTicketsManifestV2> data) {
        adapter=new adapterManifestDetails(this,data, data.size(),getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvlistTickets.setLayoutManager(layoutManager);
        rvlistTickets.setAdapter(adapter);
        presenter.getSellos(folioDespachoId);

    }

    public void gotoTickets(int position, String folioTicket, Integer statusTicket) {
        //folioDespachoId
        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        tickets ticketsf = new tickets();
        Bundle args = new Bundle();
        args.putString("folioDespachoId", folioDespachoId);
        args.putString("folioTicket", folioTicket);
        args.putString("statusManifest",statusManifest);
        args.putInt("statusTicket",statusTicket);
        ticketsf.setArguments(args);
        transaction.replace(R.id.fragments, ticketsf, tickets.TAG)
                .addToBackStack(null) // Agregar la transacción a la pila de retroceso
                .commit();
    }
    private List<dataTicketsManifestV2> filter(List<dataTicketsManifestV2> data, String text) {
        List<dataTicketsManifestV2> mfilterList= new ArrayList<>();
        text =text.toLowerCase();
        if(data!=null){
            for(dataTicketsManifestV2 manifest:data)
            {
                String manifestname=manifest.getFolioTicket().toLowerCase();
                if(manifestname.contains(text)){
                    mfilterList.add(manifest);
                }
            }
        }
        return mfilterList;
    }
    @Override
    public void setTickets(List<dataTicketsManifestV2> data) {
        this.data = data;
        setAdapter(data);
        List<dataTicketsManifestV2> fdata= new ArrayList<>();
        fdata.clear();
        if(data!=null) {
            for (dataTicketsManifestV2 mdata : data) {//este metodo se usa para filtrar los tickets que ya se han cerrado
                if (mdata.getEstatusId() == 2) ;
                {
                    fdata.add(mdata);
                }//todo pasar esto al else if
                if(mdata.getTipoEntregaId()==2){//este id es para el modulo de salida y para mostrarce dee estar en ruta y el estatus 2
                    recoletar.setVisibility(View.GONE);
                }else if(mdata.getTipoEntregaId()==1){
                    recoletar.setVisibility(View.VISIBLE);
                }else{
                    recoletar.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void setSellos(List<Sello> response) {
        this.dataSellos=response;
        Log.e("sellosdetailmanifest",""+dataSellos.size());
    }

    @Override
    public void showDialog() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("HAS_TITLE", false);
        bundle.putString("title","Cargando detalles");
        progress.setArguments(bundle);
        progress.show(getActivity().getSupportFragmentManager(), loaderFH.TAG);
    }

    @Override
    public void hideDialog() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (progress != null && getView() != null)
                    progress.dismiss();
            }
        }, 300);
    }

    private void menutransition() {
        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        mmanifestV2 checklist = new mmanifestV2();
        transaction.replace(R.id.fragments, checklist, mmanifestV2.TAG).commit();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getView() != null) {
            getView().setFocusableInTouchMode(true);
            getView().requestFocus();
            getView().setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                        if (manager != null && manager.getBackStackEntryCount() > 0) {
                            // Hay fragmentos en la pila, realiza popBackStack
                            manager.popBackStack();
                        } else {
                            // No hay fragmentos en la pila, deja que la actividad maneje el evento de retroceso
                            requireActivity().onBackPressed();
                        }
                        return true;
                    }
                    return false;
                }
            });
        }
    }


}

