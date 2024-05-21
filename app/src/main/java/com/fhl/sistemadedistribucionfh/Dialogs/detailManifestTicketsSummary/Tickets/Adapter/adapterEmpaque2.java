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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.Adapter.adapterEmpaque;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.ticketsSalida;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.Paquete;

import java.util.List;

public class adapterEmpaque2 extends RecyclerView.Adapter<adapterEmpaque2.ViewHolder> {
    private Context context;
    private List<Paquete> paquetes;

    public adapterEmpaque2(List<Paquete> paquetes, Context context) {
        this.context = context;
        this.paquetes=paquetes;

    }

    @NonNull
    @Override
    public adapterEmpaque2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemp_empaques_salida, parent, false);
        return new adapterEmpaque2.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterEmpaque2.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.razonDescSalida.setText(""+paquetes.get(position).getNombre());
        holder.checkEmpaque.setVisibility(View.GONE);
        if(paquetes.get(position).getFlag()!=null) {
            if (paquetes.get(position).getFlag() == true) {
                holder.checkEmpaque.setChecked(true);
                int tintColor = ContextCompat.getColor(context, R.color.yellow);
                ColorFilter colorFilter = new PorterDuffColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
                //holder.razonDescSalida.setTextColor(R.color.green2);
                //esta linea sirve para notificar al contador

            } else {
                holder.checkEmpaque.setChecked(false);
            }
        }
    }

    @Override
    public int getItemCount() {
        return paquetes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView razonDescSalida;
        CheckBox checkEmpaque;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            razonDescSalida=itemView.findViewById(R.id.razonDescSalidaEmpaques);
            checkEmpaque =itemView.findViewById(R.id.checkEmpaque);
        }
    }
}