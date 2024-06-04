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

import com.fhl.sistemadedistribucionfh.Dialogs.EmpaquesValidador.adapter.adapterEmpaque2;
import com.fhl.sistemadedistribucionfh.Dialogs.EmpaquesValidador.adapter.adapterValidadorEmpaques;
import com.fhl.sistemadedistribucionfh.Dialogs.EmpaquesValidador.validadorEmpaques;
import com.fhl.sistemadedistribucionfh.Dialogs.Planeacion.validadorPlaneacion;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.model.ticketsScanned;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.Paquete;

import java.util.List;

public class adapterPlaneacionEmpaques  extends RecyclerView.Adapter<adapterPlaneacionEmpaques.ViewHolder> {
    private Context context;
    private List<ticketsScanned> data;
    private validadorPlaneacion mview;


    public adapterPlaneacionEmpaques(validadorPlaneacion mview, List<ticketsScanned> data, Context context) {
        this.context = context;
        this.data=data;
        this.mview=mview;
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
        private TextView razonDesc;
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


        }

        private void fillEmpaqueAdapter(validadorPlaneacion mview, List<Paquete> paquetes, String folio) {
            empaqueAdapter = new adapterPlaneacion(mview,paquetes,context,folio);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            recyclerViewPaquetes.setLayoutManager(linearLayoutManager);
            recyclerViewPaquetes.setAdapter(empaqueAdapter);
        }

        public void setupRecyclerViewPaquetes(validadorPlaneacion mview, List<Paquete> paquetes, String folio) {
            // You can further customize the setup if needed
            fillEmpaqueAdapter(mview,paquetes,folio);
            empaqueAdapter.notifyDataSetChanged();
        }
    }
}