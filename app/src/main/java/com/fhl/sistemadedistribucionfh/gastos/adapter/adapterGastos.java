package com.fhl.sistemadedistribucionfh.gastos.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.gastos.model.gastosV2.GastosOperativo;
import com.fhl.sistemadedistribucionfh.gastos.model.gastosV2.dataGastosOperativos;

import java.util.ArrayList;
import java.util.List;

public class adapterGastos extends RecyclerView.Adapter<adapterGastos.ViewHolder> {
    private Context context;
    private List<GastosOperativo> data;
    private List<dataGastosOperativos> maindata;

    public adapterGastos(List<GastosOperativo> data, Context context, List<dataGastosOperativos> maindata) {
       this.data = data;
        this.context = context;
        this.maindata=maindata;
    }

    @NonNull
    @Override
    public adapterGastos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gastos_oerativos, parent, false);//item_carrito
        return new adapterGastos.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterGastos.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.colaboradorGastos.setText(maindata.get(position).getOperador().getNombre());
       holder.manifestGastos.setText(maindata.get(position).getFolioDespacho());
        if(maindata.get(position).getEstatus().equals("2")){
            holder.statusGastos.setText("Confirmado");
            int mcolor=context.getColor(R.color.green);
            holder.statusGastos.setTextColor(mcolor);
        }else{
            holder.statusGastos.setText("No Liquidado");
            int mcolor=context.getColor(R.color.red);
            holder.statusGastos.setTextColor(mcolor);
        }
    }

    @Override
    public int getItemCount() {
        return maindata.size();
    }

    public void setFilter(List<GastosOperativo> filterList) {
        this.data = new ArrayList<>();
        this.data.addAll(filterList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout cardOrder;
        TextView statusGastos,colaboradorGastos,manifestGastos;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardOrder = itemView.findViewById(R.id.constrainCard);
            statusGastos=itemView.findViewById(R.id.statusGastos);
            colaboradorGastos= itemView.findViewById(R.id.colaboradorGastos);
            manifestGastos=itemView.findViewById(R.id.manifestGastos);

        }
    }
}