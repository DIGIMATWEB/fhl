package com.companyname.mauitest.Salida.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.companyname.mauitest.R;
import com.companyname.mauitest.Salida.Model.Ticket;
import com.companyname.mauitest.Salida.View.salidaView;
import com.companyname.mauitest.Salida.View.sellos;
import com.companyname.mauitest.Tickets.Adapter.ticketsAdapter;
import com.companyname.mauitest.Tickets.tickets;

import java.io.Serializable;
import java.util.List;

public class adapterTicketsSalida extends RecyclerView.Adapter<adapterTicketsSalida.ViewHolder> {
    private Context context;
    private int size;
    private salidaView mView;
    private List<Ticket> data;

    public adapterTicketsSalida(salidaView mmanifest, List<Ticket> data, Context context) {
        this.mView = mmanifest;
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public adapterTicketsSalida.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_singleticket, parent, false);//item_carrito
        return new adapterTicketsSalida.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterTicketsSalida.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.ticketFolio.setText(data.get(position).getFolio());
        if(data.get(position).getSellos().isEmpty()){
            holder.masText.setVisibility(View.GONE);
            holder.siguiente.setVisibility(View.GONE);
        }else {
            holder.cardOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("sellos", (Serializable) data.get(position).getSellos());

                    Intent intent = new Intent(context, sellos.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout cardOrder;
        TextView ticketFolio,masText;
        ImageView siguiente ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardOrder = itemView.findViewById(R.id.constrainCard);
            ticketFolio=itemView.findViewById(R.id.ticketFolio);
            masText=itemView.findViewById(R.id.masText);
            siguiente=itemView.findViewById(R.id.siguiente);
        }
    }
}
