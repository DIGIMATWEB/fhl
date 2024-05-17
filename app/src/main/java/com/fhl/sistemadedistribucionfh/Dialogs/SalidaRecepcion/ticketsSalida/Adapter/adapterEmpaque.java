package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.ticketsSalida.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.Paquete;

import java.util.List;

public class adapterEmpaque extends RecyclerView.Adapter<adapterEmpaque.ViewHolder> {
    private Context context;
    private List<Paquete> paquetes;

    public adapterEmpaque(List<Paquete> paquetes, Context context) {
        this.context = context;
        this.paquetes=paquetes;
    }

    @NonNull
    @Override
    public adapterEmpaque.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemp_empaques_salida, parent, false);
        return new adapterEmpaque.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterEmpaque.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
    holder.razonDescSalida.setText(""+paquetes.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return paquetes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView razonDescSalida;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            razonDescSalida=itemView.findViewById(R.id.razonDescSalidaEmpaques);
        }
    }
}