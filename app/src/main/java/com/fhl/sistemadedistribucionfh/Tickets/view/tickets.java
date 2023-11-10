package com.fhl.sistemadedistribucionfh.Tickets.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Tickets.Adapter.ticketsAdapter;
import com.fhl.sistemadedistribucionfh.Tickets.model.dataDetailTickets;
import com.fhl.sistemadedistribucionfh.Tickets.presenter.presenterTicketsDetail;
import com.fhl.sistemadedistribucionfh.Tickets.presenter.presenterTicketsDetailImpl;
import com.fhl.sistemadedistribucionfh.cerrarViaje.view.cerrarViaje;
import com.fhl.sistemadedistribucionfh.evidence.evidencia;
import com.fhl.sistemadedistribucionfh.nmanifest.view.mmanifest;

import java.util.List;

public class tickets extends Fragment implements View.OnClickListener ,ticketsView{
    public static final String TAG = tickets.class.getSimpleName();
    private RecyclerView rvTickets;
    private ticketsAdapter adapter;
    private ImageView backTickets;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Button cerrarviaje, cancelar;
    private presenterTicketsDetail presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tickets, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rvTickets = view.findViewById(R.id.rvTickets);
        backTickets = view.findViewById(R.id.backTickets);
        backTickets.setOnClickListener(this);
        cerrarviaje = view.findViewById(R.id.cerrarviaje);
        cancelar = view.findViewById(R.id.cancelar);
        cerrarviaje.setOnClickListener(this);
        cancelar.setOnClickListener(this);

        presenter= new presenterTicketsDetailImpl(this,getContext());
        presenter.requestDetailTickets();

    }


    private void setAdapter(List<dataDetailTickets> data) {
        adapter = new ticketsAdapter(this,data, data.size(), getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvTickets.setLayoutManager(layoutManager);
        rvTickets.setAdapter(adapter);
    }
    private void cancelTrip() {
        Intent intent = new Intent(getActivity(), cerrarViaje.class);//evidencia
        startActivity(intent);
    }

    private void closeTrip() {
        Intent intent2 = new Intent(getActivity(), evidencia.class);//
        startActivity(intent2);

    }

    public void gotoManifest()
    {
        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        mmanifest manifest= new mmanifest();
        transaction.replace(R.id.fragments, manifest, mmanifest.TAG).commit();
    }

    @Override
    public void getTicketsDetail() {

    }

    @Override
    public void setTiketsDetail(List<dataDetailTickets> data) {
        setAdapter(data);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backTickets:
                gotoManifest();
                break;
            case R.id.cancelar:
                cancelTrip();
                break;
            case R.id.cerrarviaje:
                closeTrip();
                break;
        }
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