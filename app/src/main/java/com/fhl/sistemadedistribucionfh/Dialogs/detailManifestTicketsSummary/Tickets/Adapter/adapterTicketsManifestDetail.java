package com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Tickets.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.Adapter.adapterEmpaque;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.ticketsSalida;
import com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Tickets.detailTicketsSummary;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.Paquete;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;

import java.util.List;

public class adapterTicketsManifestDetail extends RecyclerView.Adapter<adapterTicketsManifestDetail.ViewHolder> {
    private Context context;
    private List<dataTicketsManifestV2> data;
    private detailTicketsSummary mview;

    public adapterTicketsManifestDetail(detailTicketsSummary mview, List<dataTicketsManifestV2> data, Context context) {
        this.context = context;
        this.data=data;
        this.mview=mview;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tickets_salida, parent, false);
        return new adapterTicketsManifestDetail.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterTicketsManifestDetail.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
      holder.razonDesc.setText(data.get(position).getFolioTicket());
      holder.check.setVisibility(View.GONE);
      holder.icticket.setBackground(ContextCompat.getDrawable(context, R.drawable.ticket_green));
            holder.icticket.setBackground(ContextCompat.getDrawable(context, R.drawable.ticket));

        holder.setupRecyclerViewPaquetes(data.get(position).getSendtripPlus().getPaquetes());
//        if(data.get(position).getFlag()==true){
//            holder.check.setChecked(true);
//            int tintColor = ContextCompat.getColor(context, R.color.yellow);
//            ColorFilter colorFilter = new PorterDuffColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
//            holder.icticket.setColorFilter(colorFilter);
//            mview.updatescanedData(data);
//
//        }else {
//            holder.check.setChecked(false);
//        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

//    public void updateData(List<ticketsScanned> model) {
//        this.data=model;
//        notifyDataSetChanged();
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView razonDesc;
        private CheckBox check;
        private ImageView icticket;
        private RecyclerView recyclerViewPaquetes;
        private adapterEmpaque2 empaqueAdapter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            razonDesc=itemView.findViewById(R.id.razonDescSalida);
            check=itemView.findViewById(R.id.checkSalida );
            icticket=itemView.findViewById(R.id.icticket);
            recyclerViewPaquetes = itemView.findViewById(R.id.recyclerViewPaquetes);


        }

        private void fillEmpaqueAdapter( List<Paquete> paquetes) {
            empaqueAdapter = new adapterEmpaque2(paquetes,context);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            recyclerViewPaquetes.setLayoutManager(linearLayoutManager);
            recyclerViewPaquetes.setAdapter(empaqueAdapter);
        }

        public void setupRecyclerViewPaquetes( List<Paquete> paquetes) {
            // You can further customize the setup if needed
            fillEmpaqueAdapter(paquetes);
            empaqueAdapter.notifyDataSetChanged();
        }
    }
}