package com.fhl.sistemadedistribucionfh.Tickets.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Tickets.Adapter.ticketsAdapter;
import com.fhl.sistemadedistribucionfh.Tickets.model.ticketsdetail.dataDetailTickets;
import com.fhl.sistemadedistribucionfh.Tickets.presenter.presenterTicketsDetail;
import com.fhl.sistemadedistribucionfh.Tickets.presenter.presenterTicketsDetailImpl;
import com.fhl.sistemadedistribucionfh.cerrarViaje.view.cancelarViaje;
import com.fhl.sistemadedistribucionfh.evidence.evidencia;
import com.fhl.sistemadedistribucionfh.nmanifest.viewV2.mmanifestV2;

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
    private String folioDespachoId,folioTicket="";
    private TextView txtManifiesto;

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
        txtManifiesto = view.findViewById(R.id.txtManifiestoTicket);
        cerrarviaje.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        Bundle args = getArguments();
        if (args != null) {
          folioDespachoId = args.getString("folioDespachoId");
          folioTicket = args.getString("folioTicket");

          txtManifiesto.setText(folioDespachoId);
            // Now you have the values and can use them as needed
            // Example: Log.d(TAG, "folioDespachoId: " + folioDespachoId + ", folioTicket: " + folioTicket);
        }
        presenter= new presenterTicketsDetailImpl(this,getContext());
        presenter.requestDetailTickets(folioDespachoId,folioTicket);

    }


    private void setAdapter(List<dataDetailTickets> data) {
        adapter = new ticketsAdapter(this,data, data.size(), getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvTickets.setLayoutManager(layoutManager);
        rvTickets.setAdapter(adapter);
    }
    private void cancelTrip() {
        Intent intent = new Intent(getActivity(), cancelarViaje.class);//evidencia
        Bundle bundle = new Bundle();
        bundle.putString("folioTicket",folioTicket);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void closeTrip() {
        Intent intent2 = new Intent(getActivity(), evidencia.class);
        Bundle bundle = new Bundle();
        bundle.putInt("flujoId", 2);// Putting integer value with key "key_integer"
        bundle.putString("currentManifest",folioDespachoId);
        bundle.putString("folioTicket",folioTicket);
        intent2.putExtras(bundle); // Attaching the bundle to the intent
        startActivity(intent2);
    }

    public void gotoManifest()
    {
        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        mmanifestV2 manifest = new mmanifestV2();
        transaction.replace(R.id.fragments, manifest, mmanifestV2.TAG);
        transaction.addToBackStack(null); // Add to back stack
        transaction.commit();
    }

    @Override
    public void getTicketsDetail() {

    }

    @Override
    public void setTiketsDetail(List<dataDetailTickets> data) {
        setAdapter(data);
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
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){

                    FragmentManager manager = getParentFragmentManager();
                    manager.popBackStack();

                    return true;
                }
                return false;
            }
        });
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