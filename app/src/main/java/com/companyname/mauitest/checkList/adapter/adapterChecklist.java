package com.companyname.mauitest.checkList.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.companyname.mauitest.R;
import com.companyname.mauitest.Salida.Adapter.adapterSellos;
import com.companyname.mauitest.Salida.Model.Sello;
import com.companyname.mauitest.Salida.View.sellos;

import java.util.List;

public class adapterChecklist extends RecyclerView.Adapter<adapterChecklist.ViewHolder> {
    private Context context;
    private int size;
//    private sellos mView;
//    private List<Sello> data;

    public adapterChecklist(Context context) {
        //this.mView = mmanifest;
       // this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public adapterChecklist.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_checklist, parent, false);//item_carrito
        return new adapterChecklist.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterChecklist.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

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
