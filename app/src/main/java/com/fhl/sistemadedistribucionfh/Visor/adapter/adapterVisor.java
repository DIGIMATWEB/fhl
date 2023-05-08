package com.fhl.sistemadedistribucionfh.Visor.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;

public class adapterVisor extends RecyclerView.Adapter<adapterVisor.ViewHolder> {
    private Context context;

    public adapterVisor( Context context) {

        this.context = context;
    }

    @NonNull
    @Override
    public adapterVisor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_visor, parent, false);//item_carrito
        return new adapterVisor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterVisor.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
