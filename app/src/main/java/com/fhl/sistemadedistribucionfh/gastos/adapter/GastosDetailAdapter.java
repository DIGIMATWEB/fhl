package com.fhl.sistemadedistribucionfh.gastos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.gastos.model.gastosV2.GastosOperativo;

import java.util.List;

public class GastosDetailAdapter extends RecyclerView.Adapter<GastosDetailAdapter.ViewHolder> {
    private List<GastosOperativo> data;// private List<GastosOperativo> data;
    private String status;

    public GastosDetailAdapter(List<GastosOperativo> data, String nombre) {
        this.data = data;
        this.status=nombre;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gastos_operativos_lista, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Bind data to views in the item layout
        // For example:
        GastosOperativo item = data.get(position);
        holder.tipoGasto.setText(item.getTipoGasto().getNombre());
        holder.Monto.setText(""+item.getDispersion().getMonto());
        holder.estatusGasto.setText(status);
        // Bind other views as needed
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tipoGasto,Monto,estatusGasto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tipoGasto = itemView.findViewById(R.id.tipoGasto);
            Monto= itemView.findViewById(R.id.Monto);
            estatusGasto= itemView.findViewById(R.id. estatusGasto);
            // Initialize other views here if needed
        }
    }
}
