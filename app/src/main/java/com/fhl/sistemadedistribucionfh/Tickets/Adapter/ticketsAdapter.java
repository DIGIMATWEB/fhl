package com.fhl.sistemadedistribucionfh.Tickets.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Tickets.tickets;

public class ticketsAdapter extends RecyclerView.Adapter<ticketsAdapter.ViewHolder>{
    private Context context;
    private  int size;
    private tickets mView;
    public ticketsAdapter(tickets mmanifest, int size, Context context) {
        this.mView=mmanifest;
        this.size=size;
        this.context=context;
    }

    @NonNull
    @Override
    public ticketsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tickets, parent, false);//item_carrito
        return  new ticketsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ticketsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.cardOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
                //mView.gotoTickets(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout cardOrder;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardOrder=itemView.findViewById(R.id.constrainCard);
        }
    }
}
