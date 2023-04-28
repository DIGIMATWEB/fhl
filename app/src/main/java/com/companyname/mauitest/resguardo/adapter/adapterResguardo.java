package com.companyname.mauitest.resguardo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.companyname.mauitest.R;
import com.companyname.mauitest.Salida.Model.Sello;
import com.companyname.mauitest.Salida.View.sellos;
import com.companyname.mauitest.checkList.adapter.adapterChecklist;

import java.util.List;

public class adapterResguardo extends RecyclerView.Adapter<adapterResguardo.ViewHolder> {
    private Context context;
    private int size;
//    private sellos mView;
//    private List<Sello> data;

    public adapterResguardo( Context context) {
//        this.mView = mmanifest;
//        this.data = data;
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

    }

    @Override
    public int getItemCount() {
        return 9;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout cardOrder;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardOrder = itemView.findViewById(R.id.constrainCard);
        }
    }
}