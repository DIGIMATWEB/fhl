package com.fhl.sistemadedistribucionfh.nmanifestDetail.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.view.manifestDetailV2;

import java.util.ArrayList;
import java.util.List;

public class adapterManifestDetails extends RecyclerView.Adapter<adapterManifestDetails.ViewHolder>{
    private manifestDetailV2 mview;
    private Context context;
    private List<dataTicketsManifestV2> data;
    private int size;
    public adapterManifestDetails(manifestDetailV2 mview, List<dataTicketsManifestV2> data, int size, Context context) {
        this.mview=mview;
        this.size = size;
        this.context=context;
        this.data=data;
    }
    @NonNull
    @Override
    public adapterManifestDetails.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_singleticket, parent, false);//item_carrito
        return  new adapterManifestDetails.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull adapterManifestDetails.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.ticketFolio.setText(data.get(position).getFolioTicket());//data.get(position).getFolioTicket());
        Integer statusId= data.get(position).getEstatusId();
        if(data.get(position).getEstatusId() != null) {
            if(statusId==1){
                holder.statusTicket.setText("En cola");
                holder.statusTicket.setTextColor(ContextCompat.getColor(context, R.color.grey));
            }else if(statusId==2){
                holder.statusTicket.setText("Asignado");
                holder.statusTicket.setTextColor(ContextCompat.getColor(context, R.color.yellowdark));
            }else if(statusId==3){
                holder.statusTicket.setText("En ruta");
                holder.statusTicket.setTextColor(ContextCompat.getColor(context, R.color.purpleenruta));
            }else if(statusId==4){
                holder.statusTicket.setText("Entregado");
                holder.statusTicket.setTextColor(ContextCompat.getColor(context, R.color.green));
            }else if(statusId==5){
                holder.statusTicket.setText("No entregado");
                holder.statusTicket.setTextColor(ContextCompat.getColor(context, R.color.orangenoentregado));
            }else if(statusId==6){
                holder.statusTicket.setText("Transferido");
            }else{
                holder.statusTicket.setText("");
            }

        } else {
            holder.statusTicket.setText(""); // or any other default value you prefer
        }
        holder.cardOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
                mview.gotoTickets(position,data.get(position).getFolioTicket(),data.get(position).getEstatusId());
            }
        });
    }
    public void setFilter(List<dataTicketsManifestV2> filterList) {
        this.data = new ArrayList<>();
        this.data.addAll(filterList);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout cardOrder;
        TextView ticketFolio,statusTicket;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardOrder=itemView.findViewById(R.id.constrainCard);
            ticketFolio = itemView.findViewById(R.id.ticketFolio);
            statusTicket= itemView.findViewById(R.id.statusTicket);
        }
    }
}
