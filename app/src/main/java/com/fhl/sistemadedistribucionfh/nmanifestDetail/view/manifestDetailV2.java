package com.fhl.sistemadedistribucionfh.nmanifestDetail.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Tickets.view.tickets;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.adapter.adapterManifestDetails;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.model.dataTicketsManifest;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.presenter.presenterTicketsManifestImplV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.presenter.presenterTicketsmanifestV2;

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
    private String folioDespachoId, vehiculoModeloId, vehiculoPlacaId, cedisId;
    private List<dataTicketsManifestV2> data;
    private presenterTicketsmanifestV2 presenter;
    private TextView vehicleManifiesto, vehicleName, vehiclePlaca, vehicleCedis;

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

        vehicleManifiesto = view.findViewById(R.id.numeroManifiesto2);
        vehicleName = view.findViewById(R.id.vehicle_namev2);
        vehiclePlaca = view.findViewById(R.id.placa_text);
        vehicleCedis = view.findViewById(R.id.textView78);

        vehicleManifiesto.setText(folioDespachoId);
        vehicleName.setText(vehiculoModeloId);
        vehiclePlaca.setText(vehiculoPlacaId);
        vehicleCedis.setText(cedisId);

        presenter= new presenterTicketsManifestImplV2(this,getContext());
        presenter.getTickets(folioDespachoId);
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
        }
    }
    private void setAdapter(List<dataTicketsManifestV2> data) {
        adapter=new adapterManifestDetails(this,data, data.size(),getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvlistTickets.setLayoutManager(layoutManager);
        rvlistTickets.setAdapter(adapter);
    }

    public void gotoTickets(int position) {
        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        tickets ticketsf= new tickets();
        transaction.replace(R.id.fragments, ticketsf, tickets.TAG).commit();
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
    }

}
