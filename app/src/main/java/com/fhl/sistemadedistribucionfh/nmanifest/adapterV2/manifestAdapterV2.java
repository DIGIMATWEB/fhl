package com.fhl.sistemadedistribucionfh.nmanifest.adapterV2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.dataManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifest.viewV2.mmanifestV2;

import java.util.ArrayList;
import java.util.List;

public class manifestAdapterV2 extends RecyclerView.Adapter<manifestAdapterV2.ViewHolder> {
    private Context context;
    private int size;
    private mmanifestV2 mView;
    private List<dataManifestV2> data;

    public manifestAdapterV2(mmanifestV2 mmanifest, List<dataManifestV2> data, int size, Context context) {
        this.mView = mmanifest;
        this.size = size;
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public manifestAdapterV2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_manifestv2, parent, false);
        return new manifestAdapterV2.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull manifestAdapterV2.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        //holder.vehicleName.setText(data.get(position).getVehiculoTercero());
        holder.vehicleName.setText(data.get(position).getVehiculo().getMarca().getNombre());
        holder.vehiclePlaca.setText(data.get(position).getVehiculo().getPlaca());
        holder.vehicleManifiesto.setText(data.get(position).getFolioDespacho());
        holder.vehicleCedis.setText(data.get(position).getOrigen());

        holder.statusManifest.setText(data.get(position).getEstatus().getNombre());
        if (data.get(position).getRecolecionEntrega()!=null){
             if (data.get(position).getRecolecionEntrega() == false) {
                holder.validationTextInProgress.setVisibility(View.GONE);
                holder.validationText.setVisibility(View.GONE);
            } else {
                holder.validationTextInProgress.setVisibility(View.VISIBLE);
                holder.validationText.setVisibility(View.VISIBLE);
                holder.validationTextInProgress.setText(data.get(position).getValidador().getEstatus());
            }
        }else{
            data.get(position).setRecolecionEntrega(false);
            holder.validationTextInProgress.setVisibility(View.GONE);
            holder.validationText.setVisibility(View.GONE);
        }
        if (data.get(position).getEstatus().getNombre().equals("Confirmado")) {
            holder.statusManifest.setTextColor(ContextCompat.getColor(context, R.color.green));
        } else if (data.get(position).getEstatus().getNombre().equals("En proceso")) {
            holder.statusManifest.setTextColor(ContextCompat.getColor(context, R.color.yellowdark));
        } else if (data.get(position).getEstatus().getNombre().equals("Cerrado")) {
            holder.statusManifest.setTextColor(ContextCompat.getColor(context, R.color.red));
        } else {
            holder.statusManifest.setTextColor(ContextCompat.getColor(context, R.color.black));
        }
        holder.cardOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO llenar estos campos cuando se tengan
                String folioDespacho = data.get(position).getFolioDespacho();
                String custodio = "";
                String telefono = "";
                String vehiculoModelo = data.get(position).getVehiculo().getMarca().getNombre();
                String vehiculoPlaca = data.get(position).getVehiculo().getPlaca();
                String cedis = data.get(position).getOrigen();
                String supervisor = "";
                String fechaEntrada = "";
                String fechaSalida = "";
                String statusManifest= data.get(position).getEstatus().getNombre();

               // Toast.makeText(context, ""+data.get(position).getIdmanifest(), Toast.LENGTH_SHORT).show();
                if(!data.get(position).getEstatus().getNombre().equals("Cerrado")) {
                    mView.gotoTickets(position, folioDespacho, vehiculoModelo, vehiculoPlaca, cedis, statusManifest);
                }else{
                    Toast.makeText(context, "Este manifiesto ya fue cerrado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setFilterV2(List<dataManifestV2> filterList) {
        this.data = new ArrayList<>();
        this.data.addAll(filterList);
        notifyDataSetChanged();
    }
    public void updateManifest(List<dataManifestV2> updated) {
        this.data = new ArrayList<>();
        this.data.addAll(updated);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout cardOrder;
        TextView numberManifest, vehicleName, vehiclePlaca, vehicleManifiesto, vehicleCedis,validationTextInProgress,statusManifest,validationText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardOrder = itemView.findViewById(R.id.constrainCard);
            vehicleName = itemView.findViewById(R.id.vehicle_name);
            vehiclePlaca = itemView.findViewById(R.id.textView17);
            vehicleManifiesto = itemView.findViewById(R.id.numberManifest);
            vehicleCedis = itemView.findViewById(R.id.textView39);
            validationTextInProgress = itemView.findViewById(R.id.validationTextInProgress);
            validationText = itemView.findViewById(R.id.validationText);
            statusManifest= itemView.findViewById(R.id.statusManifest);
        }
    }
}
