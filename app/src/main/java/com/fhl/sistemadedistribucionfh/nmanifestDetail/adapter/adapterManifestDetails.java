package com.fhl.sistemadedistribucionfh.nmanifestDetail.adapter;

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
        holder.cardOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
                mview.gotoTickets(position);
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
        TextView ticketFolio;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardOrder=itemView.findViewById(R.id.constrainCard);
            ticketFolio = itemView.findViewById(R.id.ticketFolio);
        }
    }
}
