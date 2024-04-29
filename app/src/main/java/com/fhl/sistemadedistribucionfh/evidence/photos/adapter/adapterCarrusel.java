package com.fhl.sistemadedistribucionfh.evidence.photos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.EvidenciaLlegada;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.EvidenciaSalida;

import java.util.List;

public class adapterCarrusel extends RecyclerView.Adapter<adapterCarrusel.ViewHolder> {
    private Context context;
    private List<EvidenciaSalida> evidenciaSalidaList;
    private List<EvidenciaLlegada> evidenciaLlegada;
    private Integer type=0;

    public adapterCarrusel(Context context, List<EvidenciaSalida> evidenciaSalidaList,List<EvidenciaLlegada> evidenciaLlegada,int type) {
        this.context = context;
        this.evidenciaSalidaList = evidenciaSalidaList;
        this.evidenciaLlegada=evidenciaLlegada;
        this.type=type;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_carrusel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(type==2){
            holder.description.setText(""+evidenciaLlegada.get(position).getValor());
        }else if(type==1){
            holder.description.setText(""+evidenciaSalidaList.get(position).getValor());

        }else {
            holder.description.setText("");
        }
//    Glide.with(context)
//            .load(R.drawable.camera_vectors)
//            .centerCrop()
//            .into(holder.picture);

    }

    @Override
    public int getItemCount() {
        if(type==2){
            return evidenciaLlegada.size();
        }else if(type==1){
            return evidenciaSalidaList.size();
        }else {
            return 0;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView picture;
        TextView description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            picture=itemView.findViewById(R.id.evidencecarrusel);
            description=itemView.findViewById(R.id.textView56);
        }
    }
}

