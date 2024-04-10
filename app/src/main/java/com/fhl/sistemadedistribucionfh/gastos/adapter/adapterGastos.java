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

    private List<dataGastosOperativos> maindata;
    private int expandedPosition = -1; // Track the currently expanded item position

    public adapterGastos(Context context, List<dataGastosOperativos> maindata) {

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
        holder.manifestGastos.setText(maindata.get(position).getFolioDespacho());

        // Check if current position is expanded or not
        final boolean isExpanded = position == expandedPosition;
        holder.detialCard.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.cardOrder.setVisibility(isExpanded ? View.GONE : View.VISIBLE);

        // Toggle visibility on click
        holder.cardOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandedPosition = isExpanded ? -1 : position;
                notifyDataSetChanged();

            }
        });
        // Fill gastosDetail RecyclerView with data for the current position
//        GastosDetailAdapter detailAdapter = new GastosDetailAdapter(data.get(position).getTipoGasto());
//        holder.gastosDetail.setAdapter(detailAdapter);
    }

    @Override
    public int getItemCount() {
        return maindata.size();
    }

    public void setFilter(List<dataGastosOperativos> filterList) {
        this.maindata = new ArrayList<>();
        this.maindata.addAll(filterList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout cardOrder,detialCard;
        TextView manifestGastos;
        RecyclerView gastosDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardOrder = itemView.findViewById(R.id.constrainCard);
            detialCard = itemView.findViewById(R.id.detialCard);
            manifestGastos=itemView.findViewById(R.id.manifestGastos);
            gastosDetail=itemView.findViewById(R.id.rvGastosLiquidados);
//            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
//            gastosDetail.setLayoutManager(layoutManager);
        }
    }
}