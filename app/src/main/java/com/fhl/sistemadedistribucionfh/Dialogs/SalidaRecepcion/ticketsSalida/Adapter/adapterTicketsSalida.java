package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.Dialogs.Reasons.model.dataReasons;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;

import java.util.List;

public class adapterTicketsSalida extends RecyclerView.Adapter<adapterTicketsSalida.ViewHolder> {
    private Context context;
    private List<dataTicketsManifestV2> data;

    public adapterTicketsSalida(List<dataTicketsManifestV2> data, Context context) {
        this.context = context;
        this.data=data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tickets_salida, parent, false);
        return new adapterTicketsSalida.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterTicketsSalida.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.razonDesc.setText(data.get(position).getFolioTicket());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView razonDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            razonDesc=itemView.findViewById(R.id.razonDesc);
        }
    }
}