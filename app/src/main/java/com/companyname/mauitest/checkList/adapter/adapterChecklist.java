package com.companyname.mauitest.checkList.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.companyname.mauitest.R;
import com.companyname.mauitest.Salida.Adapter.adapterSellos;
import com.companyname.mauitest.Salida.Model.Sello;
import com.companyname.mauitest.Salida.View.sellos;
import com.companyname.mauitest.checkList.model.dataChecklist;

import java.util.ArrayList;
import java.util.List;

public class adapterChecklist extends RecyclerView.Adapter<adapterChecklist.ViewHolder> {
    private Context context;
    private List<dataChecklist> data;

    public adapterChecklist(List<dataChecklist> data, Context context) {
        this.data = data;
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
            holder.namechecklist.setText(data.get(position).getNombreCheckList());
            holder.vehiclTypeChecklist.setText(data.get(position).getVehicleType());
            holder.manifestChecklist.setText(data.get(position).getManifestAsignment());
            if(data.get(position).getStatus().equals("1")){
                holder.statusChecklist.setText("Vigente");
                int mcolor=context.getColor(R.color.green);
                holder.statusChecklist.setTextColor(mcolor);
            }else{
                holder.statusChecklist.setText("No vigente");
                int mcolor=context.getColor(R.color.red);
                holder.statusChecklist.setTextColor(mcolor);

                holder.siguienteChecklist.setVisibility(View.GONE);
                holder.moreChecklist.setVisibility(View.GONE);
            }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setFilter(List<dataChecklist> filterList) {
        this.data = new ArrayList<>();
        this.data.addAll(filterList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout cardOrder;
        TextView vehiclTypeChecklist,manifestChecklist,statusChecklist,moreChecklist,namechecklist;
        ImageView siguienteChecklist;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardOrder = itemView.findViewById(R.id.constrainCard);
            siguienteChecklist=itemView.findViewById(R.id.siguienteChecklist);
            vehiclTypeChecklist= itemView.findViewById(R.id.vehiclTypeChecklist);
            manifestChecklist= itemView.findViewById(R.id.manifestChecklist);
            statusChecklist= itemView.findViewById(R.id.statusChecklist);
            moreChecklist= itemView.findViewById(R.id.moreChecklist);
            namechecklist=itemView.findViewById(R.id.namechecklist);

        }
    }
}
