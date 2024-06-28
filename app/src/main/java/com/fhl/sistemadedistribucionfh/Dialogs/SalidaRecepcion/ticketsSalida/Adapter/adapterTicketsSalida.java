package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.Adapter;

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

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.model.ticketsScanned;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.ticketsSalida;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.Paquete;

import java.util.List;

public class adapterTicketsSalida extends RecyclerView.Adapter<adapterTicketsSalida.ViewHolder> {
    private Context context;
    private List<ticketsScanned> data;
    private ticketsSalida mview;
    private Boolean needGroupThem;


    public adapterTicketsSalida(ticketsSalida mview, List<ticketsScanned> data, Context context, Boolean needGroupThem) {
        this.context = context;
        this.data=data;
        this.mview=mview;
        this.needGroupThem=needGroupThem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tickets_salida, parent, false);
        return new adapterTicketsSalida.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterTicketsSalida.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.razonDesc.setText(data.get(position).getFolio());
        if(!needGroupThem){//esto es por que solo h ay un ticket
            holder.evidence.setVisibility(View.VISIBLE);
            holder.siguiente.setVisibility(View.VISIBLE);
            holder.evidence.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mview.goEvidenceOneItem(data.get(position));
                }
            });
        }
        if( data.get(position).getSendtripPlus().getPaquetes()!=null) {
            holder.setupRecyclerViewPaquetes(mview, data.get(position).getSendtripPlus().getPaquetes(), data.get(position).getFolio());
        }else{
            data.get(position).setFlag(true);
        }
        if(data.get(position).getFlag()==true){
            holder.check.setChecked(true);
            int tintColor = ContextCompat.getColor(context, R.color.yellow);
            ColorFilter colorFilter = new PorterDuffColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
            holder.icticket.setBackground(ContextCompat.getDrawable(context, R.drawable.ticket_green));
            mview.updatescanedData(data);

        }else {
            holder.check.setChecked(false);
            holder.icticket.setBackground(ContextCompat.getDrawable(context, R.drawable.ticket));
        }
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateData(List<ticketsScanned> model) {
        this.data=model;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView razonDesc,evidence;
        private CheckBox check;
        private ImageView icticket,siguiente;
        private RecyclerView recyclerViewPaquetes;
        private adapterEmpaque empaqueAdapter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            razonDesc=itemView.findViewById(R.id.razonDescSalida);
            check=itemView.findViewById(R.id.checkSalida );
            icticket=itemView.findViewById(R.id.icticket);
            recyclerViewPaquetes = itemView.findViewById(R.id.recyclerViewPaquetes);
            evidence=itemView.findViewById(R.id.evidence);
            siguiente =itemView.findViewById(R.id.siguiente);
        }

        private void fillEmpaqueAdapter(ticketsSalida mview, List<Paquete> paquetes, String folio) {
            empaqueAdapter = new adapterEmpaque(mview,paquetes,context,folio);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            recyclerViewPaquetes.setLayoutManager(linearLayoutManager);
            recyclerViewPaquetes.setAdapter(empaqueAdapter);
        }

        public void setupRecyclerViewPaquetes(ticketsSalida mview, List<Paquete> paquetes, String folio) {
            // You can further customize the setup if needed
            fillEmpaqueAdapter(mview,paquetes,folio);
            empaqueAdapter.notifyDataSetChanged();
        }
    }
}