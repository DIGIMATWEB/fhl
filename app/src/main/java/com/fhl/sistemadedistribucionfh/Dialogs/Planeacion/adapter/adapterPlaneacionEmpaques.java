package com.fhl.sistemadedistribucionfh.Dialogs.Planeacion.adapter;

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

import com.fhl.sistemadedistribucionfh.Dialogs.Planeacion.view.validadorPlaneacion;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.model.ticketsScanned;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.Paquete;

import java.util.ArrayList;
import java.util.List;

public class adapterPlaneacionEmpaques  extends RecyclerView.Adapter<adapterPlaneacionEmpaques.ViewHolder> {
    private Context context;
    private List<ticketsScanned> data;
    private validadorPlaneacion mview;
    private Integer statusPlaneacion;


    public adapterPlaneacionEmpaques(validadorPlaneacion mview, List<ticketsScanned> data, Context context, Integer statusPlaneacion) {
        this.context = context;
        this.data=data;
        this.mview=mview;
        this.statusPlaneacion=statusPlaneacion;
    }

    @NonNull
    @Override
    public adapterPlaneacionEmpaques.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tickets_salida, parent, false);
        return new adapterPlaneacionEmpaques.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterPlaneacionEmpaques.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.razonDesc.setText(data.get(position).getFolio());

        if( data.get(position).getSendtripPlus().getPaquetes()!=null) {
            holder.setupRecyclerViewPaquetes(mview, data.get(position).getSendtripPlus().getPaquetes(), data.get(position).getFolio(),statusPlaneacion);
            List<Paquete> fPacks = new ArrayList<>();
            fPacks = data.get(position).getSendtripPlus().getPaquetes();
            Integer countTrue = 0;
            for (Paquete paq : fPacks) {
                if (paq.getFlag()) {
                    countTrue++;
                }
            }
            holder.lotesCount.setText(countTrue+"/" + data.get(position).getSendtripPlus().getPaquetes().size());
        }else{
            data.get(position).setFlag(true);
        }
        if(statusPlaneacion==2){
            data.get(position).setFlag(true);
        }
        if(data.get(position).getFlag()==true){
            holder.check.setChecked(true);
            int tintColor = ContextCompat.getColor(context, R.color.yellow);
            ColorFilter colorFilter = new PorterDuffColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
            holder.icticket.setBackground(ContextCompat.getDrawable(context, R.drawable.ticket_green));
            mview.updatescanedData(data);
            mview.setVisibleConfirm();

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
        private TextView razonDesc,lotesCount;
        private CheckBox check;
        private ImageView icticket;
        private RecyclerView recyclerViewPaquetes;
        private adapterPlaneacion empaqueAdapter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            razonDesc=itemView.findViewById(R.id.razonDescSalida);
            check=itemView.findViewById(R.id.checkSalida );
            icticket=itemView.findViewById(R.id.icticket);
            recyclerViewPaquetes = itemView.findViewById(R.id.recyclerViewPaquetes);
            lotesCount= itemView.findViewById(R.id. lotesCount);

        }

        private void fillEmpaqueAdapter(validadorPlaneacion mview, List<Paquete> paquetes, String folio, Integer statusPlaneacion) {
            empaqueAdapter = new adapterPlaneacion(mview,paquetes,context,folio,statusPlaneacion);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            recyclerViewPaquetes.setLayoutManager(linearLayoutManager);
            recyclerViewPaquetes.setAdapter(empaqueAdapter);
        }

        public void setupRecyclerViewPaquetes(validadorPlaneacion mview, List<Paquete> paquetes, String folio, Integer statusPlaneacion) {
            // You can further customize the setup if needed
            fillEmpaqueAdapter(mview,paquetes,folio,statusPlaneacion);
            empaqueAdapter.notifyDataSetChanged();
        }
    }
}