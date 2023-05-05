package com.companyname.mauitest.nmanifest.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.companyname.mauitest.R;
import com.companyname.mauitest.nmanifest.model.dataManifest;
import com.companyname.mauitest.nmanifest.view.mmanifest;

import java.util.ArrayList;
import java.util.List;

public class manifestAdapter extends RecyclerView.Adapter<manifestAdapter.ViewHolder>{
    private Context context;
    private  int size;
    private mmanifest mView;
    private List<dataManifest> data;
    public manifestAdapter(mmanifest mmanifest, List<dataManifest> data, int size, Context context) {
        this.mView=mmanifest;
        this.size=size;
        this.context=context;
        this.data=data;
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
        holder.numberManifest.setText(data.get(position).getIdmanifest());
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setFilter(List<dataManifest> filterList) {
        this.data = new ArrayList<>();
        this.data.addAll(filterList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout cardOrder;
        TextView numberManifest;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardOrder=itemView.findViewById(R.id.constrainCard);
            numberManifest=itemView.findViewById(R.id.numberManifest);
        }
    }
}
