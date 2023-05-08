package com.fhl.sistemadedistribucionfh.resguardo.adapter;

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
import com.fhl.sistemadedistribucionfh.resguardo.model.dataResguardo;

import java.util.ArrayList;
import java.util.List;

public class adapterResguardo extends RecyclerView.Adapter<adapterResguardo.ViewHolder> {
    private Context context;
    private List<dataResguardo> data;

    public adapterResguardo(List<dataResguardo> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public adapterResguardo.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_resguardo, parent, false);//item_carrito
        return new adapterResguardo.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterResguardo.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.registra.setText( data.get(position).getRegistra());
        holder.articulom.setText(data.get(position).getArticulo()+" IMEI "+data.get(position).getImei());
        holder.entrada.setText(data.get(position).getEntrada());
        holder.salida.setText(data.get(position).getSalida());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setFilter(List<dataResguardo> filterList) {
        this.data = new ArrayList<>();
        this.data.addAll(filterList);
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout cardOrder;
        TextView registra,articulom,entrada,salida;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardOrder = itemView.findViewById(R.id.constrainCard);
            registra= itemView.findViewById(R.id.textView46);
            articulom= itemView.findViewById(R.id.article);
            entrada= itemView.findViewById(R.id.entrada);
            salida= itemView.findViewById(R.id.salida);
        }
    }
}