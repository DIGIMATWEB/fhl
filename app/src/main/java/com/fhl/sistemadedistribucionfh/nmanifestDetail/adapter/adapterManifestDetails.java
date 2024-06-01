package com.fhl.sistemadedistribucionfh.nmanifestDetail.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
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
    private List<String> selectedItems=new ArrayList<>();
    private int size;
    public adapterManifestDetails(manifestDetailV2 mview, List<dataTicketsManifestV2> data, int size, Context context) {
        selectedItems.clear();
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
                holder.switchSelector.setVisibility(View.VISIBLE);
                holder.switchSelector.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            if(!selectedItems.contains(data.get(position).getFolioTicket())) {
                                selectedItems.add(data.get(position).getFolioTicket());
                            }//else do nothing
                        }else{
                            if(selectedItems.contains(data.get(position).getFolioTicket())) {
                                selectedItems.remove(data.get(position).getFolioTicket());
                            }
                        }
                        mview.checkFoliosSelected(selectedItems);
                    }
                });
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
            }else if(statusId==8){
            holder.statusTicket.setText("Con devolucion");
                holder.freametouch.setVisibility(View.GONE);
                holder.masText.setVisibility(View.GONE);
                holder.siguiente.setVisibility(View.GONE);
            }else{
                holder.statusTicket.setText("");
            }

        } else {
            holder.statusTicket.setText(""); // or any other default value you prefer
        }
        holder.freametouch.setOnClickListener(new View.OnClickListener() {
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
        TextView ticketFolio,statusTicket,masText;
        ImageView siguiente;
        Switch switchSelector;
        FrameLayout freametouch;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            masText=itemView.findViewById(R.id.masText);
            siguiente=itemView.findViewById(R.id. siguiente);
            freametouch=itemView.findViewById(R.id.freametouch);
            cardOrder=itemView.findViewById(R.id.constrainCard);
            ticketFolio = itemView.findViewById(R.id.ticketFolio);
            statusTicket= itemView.findViewById(R.id.statusTicket);
            switchSelector = itemView.findViewById(R.id.switchSelector);
        }
    }
}
