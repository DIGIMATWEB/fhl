package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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

import java.util.List;

public class adapterGroups extends RecyclerView.Adapter<adapterGroups.ViewHolder> {
    private Context context;
    private List<gruposTickets> groupsTickets;
    private ticketsSalida mview;

    public adapterGroups(ticketsSalida mview, List<gruposTickets> groupsTickets, Context context) {
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
