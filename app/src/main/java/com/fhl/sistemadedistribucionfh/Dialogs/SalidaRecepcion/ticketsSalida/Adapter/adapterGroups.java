package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.gruposTickets;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.model.ticketsScanned;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.ticketsSalida;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.Paquete;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class adapterGroups extends RecyclerView.Adapter<adapterGroups.ViewHolder> {
    private Context context;
    private List<gruposTickets> groupsTickets;
    private ticketsSalida mview;

    public adapterGroups(ticketsSalida mview, List<gruposTickets> groupsTickets, Context context, Boolean needGroupThem) {
        this.context = context;
        this.groupsTickets=groupsTickets;
        this.mview=mview;
    }

    @NonNull
    @Override
    public adapterGroups.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemp_empaques_salida_groups, parent, false);
        return new adapterGroups.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterGroups.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.setupRVtickets(mview,groupsTickets.get(position).getTickets());

    }

    @Override
    public int getItemCount() {
        return groupsTickets.size();

    }

    public void updateData(List<ticketsScanned> model, String code) {
        Log.e("dataticketsSizeE","grupos update codigo: "+code);
        List<gruposTickets> gruposTicketsn=new ArrayList<>();
        gruposTicketsn.clear();
        for (gruposTickets gT : groupsTickets) { // For each group in groupsTickets
            for (ticketsScanned ticket : gT.getTickets()) { // For each ticket in the group
                boolean allFlagsSet = true; // Initialize a flag to track if all paquetes flags are set

                for (Paquete paq : ticket.getSendtripPlus().getPaquetes()) {
                    if (paq.getNombre().equals(code)) {
                        paq.setFlag(true); // Set the flag for the matching paquete
                    }
                    // If any paquete does not have the flag set, mark the allFlagsSet as false
                    if (!paq.getFlag()) {
                        allFlagsSet = false;
                    }
                }
                // If all paquete flags are set, set the flag for the ticket
                if (allFlagsSet) {
                    ticket.setFlag(true);
                }
            }
            gruposTicketsn.add(gT); // Add the modified group to the new list
        }
//        Gson gson= new Gson();
//        String json= gson.toJson(gruposTicketsn);
//        Log.e("dataticketsSizeE","grupos "+json);
        this.groupsTickets=gruposTicketsn;
        mview.updatescanedDataGroups(groupsTickets);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView groupsTickets;
        adapterTicketsSalida adapter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            groupsTickets= itemView.findViewById(R.id.groupsTickets);
        }

        public void setupRVtickets(ticketsSalida mview, List<ticketsScanned> tickets){
            fillTciketsAdapter(mview,tickets);
            adapter.notifyDataSetChanged();
        }
        private void fillTciketsAdapter(ticketsSalida mview, List<ticketsScanned> tickets) {
            adapter = new adapterTicketsSalida(mview,tickets,context,true);//ticketsSalida mview, List<ticketsScanned> data, Context context, Boolean needGroupThem
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            groupsTickets.setLayoutManager(linearLayoutManager);
            groupsTickets.setAdapter(adapter);
        }
    }
}
