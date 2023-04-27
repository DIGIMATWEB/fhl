package com.companyname.mauitest.Salida.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.companyname.mauitest.Salida.Model.Sello;
import com.companyname.mauitest.Salida.Model.Ticket;
import com.companyname.mauitest.Salida.View.salidaView;
import com.companyname.mauitest.Salida.View.sellos;

import java.util.List;

public class adapterSellos extends RecyclerView.Adapter<adapterSellos.ViewHolder> {
    private Context context;
    private int size;
    private sellos mView;
    private List<Sello> data;

    public adapterSellos(sellos mmanifest, List<Sello> data, Context context) {
        this.mView = mmanifest;
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public adapterSellos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_single_sello, parent, false);//item_carrito
        return new adapterSellos.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterSellos.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.selloFolio.setText(data.get(position).getNombreSello());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout cardOrder;
        TextView selloFolio;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardOrder = itemView.findViewById(R.id.constrainCard);
            selloFolio=itemView.findViewById(R.id.selloFolio);
        }
    }
}
