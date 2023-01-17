package com.newlandapps.fhl.nmanifest.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.newlandapps.fhl.R;
import com.newlandapps.fhl.nmanifest.mmanifest;

public class manifestAdapter extends RecyclerView.Adapter<manifestAdapter.ViewHolder>{
    private Context context;
    private  int size;
    private mmanifest mView;
    public manifestAdapter(mmanifest mmanifest, int size, Context context) {
        this.mView=mmanifest;
        this.size=size;
        this.context=context;
    }

    @NonNull
    @Override
    public manifestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_manifest, parent, false);//item_carrito
        return  new manifestAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull manifestAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.cardOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
                mView.gotoTickets(position);
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
