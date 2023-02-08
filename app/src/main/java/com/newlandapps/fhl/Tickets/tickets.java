package com.newlandapps.fhl.Tickets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.newlandapps.fhl.R;
import com.newlandapps.fhl.Tickets.Adapter.ticketsAdapter;
import com.newlandapps.fhl.nmanifest.adapter.manifestAdapter;
import com.newlandapps.fhl.nmanifest.mmanifest;

public class tickets extends Fragment implements View.OnClickListener {
    public static final String TAG = tickets.class.getSimpleName();
    private RecyclerView rvTickets;
    private ticketsAdapter adapter;
    private ImageView backTickets;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tickets, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rvTickets=view.findViewById(R.id.rvTickets);
        backTickets=view.findViewById(R.id.backTickets);
        backTickets.setOnClickListener(this);
        filldata();
    }
    private void filldata() {
        setAdapter();
    }

    private void setAdapter() {
        adapter=new ticketsAdapter(this,5,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvTickets.setLayoutManager(layoutManager);
        rvTickets.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backTickets:
                gotoManifest();
                break;
        }
    }
    public void gotoManifest()
    {
        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        mmanifest manifest= new mmanifest();
        transaction.replace(R.id.fragments, manifest, mmanifest.TAG).commit();
    }
}
// private void showFragmentNavigationButtons() {
//        manager = getSupportFragmentManager();
//        transaction = manager.beginTransaction();
//        menu menu = new menu();
//        transaction.replace(R.id.menu, menu, menu.TAG).commit();
//    }
//}
///// manager = getActivity().getSupportFragmentManager();
////        transaction = manager.beginTransaction();
////        Perfile perfile = new Perfile();
////        transaction.replace(R.id.ordenarViewImpl, perfile, Perfile.TAG).commit();
////        illuminateprofile();