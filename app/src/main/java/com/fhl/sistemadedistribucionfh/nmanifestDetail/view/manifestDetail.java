package com.fhl.sistemadedistribucionfh.nmanifestDetail.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
import com.fhl.sistemadedistribucionfh.nmanifestDetail.presenter.presenterTicketsManifestImpl;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.presenter.presenterTicketsmanifest;

import java.util.ArrayList;
import java.util.List;

public class manifestDetail  extends Fragment implements View.OnClickListener,viewManifestDetails{
    public static final String TAG = manifestDetail.class.getSimpleName();
    private RecyclerView rvlistTickets;
    private adapterManifestDetails adapter;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private SearchView searchViewManifestdetail;
    private ImageView searchicodetail;
    private String manifestId;
    private List<dataTicketsManifest> data;
    private presenterTicketsmanifest presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manifest_details, container, false);
        Bundle bundle = this.getArguments();

        if(bundle != null){
            // handle your code here.
            manifestId=bundle.getString("manifestId");
            Log.e("midManifest",""+manifestId);
        }
        initView(view);
        return view;
    }

    private void initView(View view) {
        rvlistTickets=view.findViewById(R.id.rvlistTickets);
        searchViewManifestdetail=view.findViewById(R.id.searchViewManifestdetail);
        searchicodetail=view.findViewById(R.id.searchicodetail);

        searchicodetail.setOnClickListener(this);
        presenter= new presenterTicketsManifestImpl(this,getContext());
        presenter.getTickets(manifestId);

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
                            List<dataTicketsManifest> filterList =filter(data,newText);
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
    private void setAdapter(List<dataTicketsManifest> data) {
        adapter=new adapterManifestDetails(this,data,getContext());
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
    private List<dataTicketsManifest> filter(List<dataTicketsManifest> data, String text) {
        List<dataTicketsManifest> mfilterList= new ArrayList<>();
        text =text.toLowerCase();
        if(data!=null){
            for(dataTicketsManifest manifest:data)
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
    public void setTickets(List<dataTicketsManifest> data) {
        this.data=data;
        setAdapter(data);
    }
}
