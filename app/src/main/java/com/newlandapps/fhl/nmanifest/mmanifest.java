package com.newlandapps.fhl.nmanifest;

import android.annotation.SuppressLint;
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
import com.newlandapps.fhl.Tickets.tickets;
import com.newlandapps.fhl.nmanifest.adapter.manifestAdapter;
import com.newlandapps.fhl.nmanifestDetail.manifestDetail;

public class mmanifest extends Fragment implements View.OnClickListener {
    public static final String TAG = mmanifest.class.getSimpleName();
    private RecyclerView rv;
    private manifestAdapter adapter;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manifest, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rv =view.findViewById(R.id.rvmanifest);
        filldata();
    }

    private void filldata() {
        setAdapter();
    }

    private void setAdapter() {
        adapter=new manifestAdapter(this,5,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

    }
    public void gotoTickets(int position)
    {
        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        //tickets ticketsf= new tickets();
        manifestDetail manifestdetail =new manifestDetail();
        transaction.replace(R.id.fragments, manifestdetail, manifestDetail.TAG).commit();
    }
}
