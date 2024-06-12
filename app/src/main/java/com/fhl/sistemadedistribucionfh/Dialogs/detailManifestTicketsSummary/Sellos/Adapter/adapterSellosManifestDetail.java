package com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Salida.Model.v2.dataSalida;
import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;

import java.util.List;

public class adapterSellosManifestDetail extends RecyclerView.Adapter<adapterSellosManifestDetail.ViewHolder> {
    private Context context;
    private List<Sello> data;

    public adapterSellosManifestDetail(List<Sello> sellos, Context context) {
        this.context = context;
        this.data=sellos;
    }

    @NonNull
    @Override
    public adapterSellosManifestDetail.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sellos_salida, parent, false);
        return new adapterSellosManifestDetail.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterSellosManifestDetail.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(data.get(position).getNumeroSello()!=null) {
            holder.razonDesc.setText("" + data.get(position).getNumeroSello());
        }
//        if(data.get(position).getFlag()==true){
//            holder.check.setChecked(true);
//            int tintColor = ContextCompat.getColor(context, R.color.yellow);
//            ColorFilter colorFilter = new PorterDuffColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
//            holder.icticket.setColorFilter(colorFilter);
//            mview.updatescanedData(data);
//
//        }else {
//            holder.check.setChecked(false);
//        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

//    public void updateData(List<ticketsScanned> model) {
//        this.data=model;
//        notifyDataSetChanged();
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView razonDesc;
        private CheckBox check;
        private ImageView icticket;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            razonDesc=itemView.findViewById(R.id.razonDescSalida);
            check=itemView.findViewById(R.id.checkSalida );
            icticket=itemView.findViewById(R.id.icticket);
        }
    }
}