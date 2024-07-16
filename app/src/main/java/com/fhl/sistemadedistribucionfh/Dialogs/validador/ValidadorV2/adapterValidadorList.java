package com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fhl.sistemadedistribucionfh.Dialogs.Planeacion.adapter.adapterPlaneacion;
import com.fhl.sistemadedistribucionfh.Dialogs.Planeacion.view.validadorPlaneacion;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.Paquete;

import java.util.List;

public class adapterValidadorList extends RecyclerView.Adapter<adapterValidadorList.ViewHolder> {
    private Context context;
    public adapterValidadorList(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public adapterValidadorList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_escancode_validador_item, parent, false);
        return new adapterValidadorList.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterValidadorList.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (position==0){
            holder.step.setText("Paso 1");
            holder.stepdescription.setText("Escanea el código del manifiesto");
            Glide.with(context).load(R.drawable.ic_scan_document)
                    .error(R.drawable.ic_menu_checklist_icon_gray)
                    .into(holder.imageItem);
            Glide.with(context).load(R.drawable.ic_upload_icon)
                    .into(holder.imageicon);
        }else if (position==1){
            holder.step.setText("Paso 2");
            holder.stepdescription.setText("Escanea el código del vehículo (VIN)");
            Glide.with(context).load(R.drawable.ic_can_truck_gray)
                    .error(R.drawable.ic_menu_checklist_icon_gray)
                    .into(holder.imageItem);
            Glide.with(context).load(R.drawable.ic_upload_icon)
                    .into(holder.imageicon);
        }else if (position==2){
            holder.step.setText("Paso 3");
            holder.stepdescription.setText("Escanea el código de la identificación (RFC)");
            Glide.with(context).load(R.drawable.ic_menu_validador_icon_gray)
                    .error(R.drawable.ic_menu_checklist_icon_gray)
                    .into(holder.imageItem);
            Glide.with(context).load(R.drawable.ic_upload_icon)
                    .into(holder.imageicon);
        }else if (position==3){
            holder.step.setText("Paso 4");
            holder.stepdescription.setText("Verifica las habilidades del operador");
            Glide.with(context).load(R.drawable.ic_scan_document)
                    .error(R.drawable.ic_menu_checklist_icon_gray)
                    .into(holder.imageItem);
            Glide.with(context).load(R.drawable.ic_upload_icon)
                    .into(holder.imageicon);
        }else if (position==4){
            holder.step.setText("Paso 5");
            holder.stepdescription.setText("Verifica las habilidades del vehículo");
            Glide.with(context).load(R.drawable.ic_scan_document)
                    .error(R.drawable.ic_menu_checklist_icon_gray)
                    .into(holder.imageItem);
            Glide.with(context).load(R.drawable.ic_upload_icon)
                    .into(holder.imageicon);

        }
    }

    @Override
    public int getItemCount() {
            return 5;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageItem, imageicon;
        TextView step,stepdescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageItem=itemView.findViewById(R.id.imageItem);
            imageicon=itemView.findViewById(R.id.imageicon);
            step=itemView.findViewById(R.id.step);
            stepdescription=itemView.findViewById(R.id.stepdescription);
        }
    }
}