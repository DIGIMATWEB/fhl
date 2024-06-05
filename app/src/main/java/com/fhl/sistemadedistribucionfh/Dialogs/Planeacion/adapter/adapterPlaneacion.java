package com.fhl.sistemadedistribucionfh.Dialogs.Planeacion.adapter;

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

import com.fhl.sistemadedistribucionfh.Dialogs.Planeacion.view.validadorPlaneacion;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.Paquete;

import java.util.List;

public class adapterPlaneacion extends RecyclerView.Adapter<adapterPlaneacion.ViewHolder> {
    private Context context;
    private List<Paquete> paquetes;
    private validadorPlaneacion mview;
    private String folioTicket;
    private Integer statusPlaneacion=0;
    public adapterPlaneacion(validadorPlaneacion mview, List<Paquete> paquetes, Context context, String folio, Integer statusPlaneacion) {
        this.context = context;
        this.paquetes=paquetes;
        this.mview=mview;
        this.folioTicket=folio;
        this.statusPlaneacion=statusPlaneacion;
    }

    @NonNull
    @Override
    public adapterPlaneacion.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemp_empaques_salida, parent, false);
        return new adapterPlaneacion.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterPlaneacion.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.razonDescSalida.setText(""+paquetes.get(position).getNombre());
        if(paquetes.get(position).getFlag()!=null) {
            if(statusPlaneacion==2){
                paquetes.get(position).setFlag(true);
            }
            if (paquetes.get(position).getFlag() == true) {
                holder.checkEmpaque.setChecked(true);
                int tintColor = ContextCompat.getColor(context, R.color.yellow);
                ColorFilter colorFilter = new PorterDuffColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
                //holder.razonDescSalida.setTextColor(R.color.green2);
                //mview.updatescanedDataEmpaque(paquetes);//esta linea sirve para notificar al contador

            } else {
                holder.checkEmpaque.setChecked(false);
            }
        }
        holder.checkEmpaque.setEnabled(false);

    }

    @Override
    public int getItemCount() {
        if(paquetes!=null) {
            return paquetes.size();
        }else{
            mview.nullempaquesCheckticket(folioTicket);
            return 0;
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView razonDescSalida;
        CheckBox checkEmpaque;
        ImageView imagealpha;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            razonDescSalida=itemView.findViewById(R.id.razonDescSalidaEmpaques);
            checkEmpaque =itemView.findViewById(R.id.checkEmpaque);
            imagealpha =itemView.findViewById(R.id.imagealpha);
        }
    }
}