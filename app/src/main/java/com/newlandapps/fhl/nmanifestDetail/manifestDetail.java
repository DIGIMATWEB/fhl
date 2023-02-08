package com.newlandapps.fhl.nmanifestDetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.newlandapps.fhl.R;
import com.newlandapps.fhl.Tickets.Adapter.ticketsAdapter;
import com.newlandapps.fhl.Tickets.tickets;
import com.newlandapps.fhl.nmanifest.adapter.manifestAdapter;

public class manifestDetail  extends Fragment implements View.OnClickListener{
    public static final String TAG = manifestDetail.class.getSimpleName();
    private RecyclerView rvlistTickets;
    private adapterManifestDetails adapter;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manifest_details, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rvlistTickets=view.findViewById(R.id.rvlistTickets);
        setAdapter();
    }

    @Override
    public void onClick(View v) {

    }
    private void setAdapter() {
        adapter=new adapterManifestDetails(this,5,getContext());
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
}
