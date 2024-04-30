package com.fhl.sistemadedistribucionfh.evidence.photos.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
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
import com.fhl.sistemadedistribucionfh.evidence.photos.carrusel;
import com.fhl.sistemadedistribucionfh.evidence.photos.model.bitmapArange;

import java.util.ArrayList;
import java.util.List;

public class adapterCarrusel extends RecyclerView.Adapter<adapterCarrusel.ViewHolder> {
    private Context context;
    private List<EvidenciaSalida> evidenciaSalidaList;
    private List<EvidenciaLlegada> evidenciaLlegada;
    private Integer type=0;
    private carrusel mview;
    private  ViewHolder mholder;
    private List<bitmapArange> bitmaplist;

    public adapterCarrusel(carrusel mview,Context context, List<EvidenciaSalida> evidenciaSalidaList,List<EvidenciaLlegada> evidenciaLlegada,int type,List<bitmapArange> bitmaplist) {
        this.context = context;
        this.evidenciaSalidaList = evidenciaSalidaList;
        this.evidenciaLlegada=evidenciaLlegada;
        this.type=type;
        this.mview=mview;
        this.bitmaplist=bitmaplist;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_carrusel, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        mholder=holder;
        if(type==2){
            holder.description.setText(""+evidenciaLlegada.get(position).getValor());
        }else if(type==1){
            holder.description.setText(""+evidenciaSalidaList.get(position).getValor());

        }else {
            holder.description.setText("");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mview.openCamera(position);
            }
        });
        if(bitmaplist.size()!=0) {
            if (bitmaplist.get(position).getImageBitmap() != null) {
                if (bitmaplist.get(position).getPosition() == position) {
                    Glide.with(context)
                            .load(bitmaplist.get(position).getImageBitmap())
                            .centerCrop()
                            .into(mholder.picture);
                } else {
                    Glide.with(context)
                            .load(R.drawable.ic_cameraico)
                            .centerCrop()
                            .into(mholder.picture);
                }
            } else {
                Glide.with(context)
                        .load(R.drawable.ic_cameraico)
                        .centerCrop()
                        .into(mholder.picture);

            }
        }


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

    public void updateBitmapslist(List<bitmapArange> newbitmaplist) {
        this.bitmaplist=new ArrayList<>(); // Clear the existing list
        this.bitmaplist.addAll(newbitmaplist); // Add all elements from the new list
        notifyDataSetChanged();
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

