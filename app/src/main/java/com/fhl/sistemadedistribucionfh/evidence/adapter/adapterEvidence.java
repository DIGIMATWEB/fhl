package com.fhl.sistemadedistribucionfh.evidence.adapter;

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
import com.fhl.sistemadedistribucionfh.Salida.Adapter.adapterSellos;
import com.fhl.sistemadedistribucionfh.Salida.Model.test.Sello;
import com.fhl.sistemadedistribucionfh.Salida.View.sellos;

import java.util.ArrayList;
import java.util.List;

public class adapterEvidence extends RecyclerView.Adapter<adapterEvidence.ViewHolder> {
    private Context context;


    public adapterEvidence( Context context) {

        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_evidence, parent, false);//item_carrito
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterEvidence.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {



    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
