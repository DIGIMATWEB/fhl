package com.fhl.sistemadedistribucionfh.cerrarViaje.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.cerrarViaje.view.cerrarViaje;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class adapterNoCompletado extends RecyclerView.Adapter<adapterNoCompletado.ViewHolder> {

    private Context context;
    private boolean trashcount=false;
    private cerrarViaje mview;
    private List<String> currentSelected=new ArrayList<>();//items seleccionados

    public adapterNoCompletado(cerrarViaje mview, Context context)
    {
        this.mview=mview;
        this.context=context;
        currentSelected.clear();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view= LayoutInflater.from(context).inflate(R.layout.item_imagecollection, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        if(trashcount){/**este metodo esta pendiente en caso de actualizar el carrusel oculta el overlay seleccionado y lo oculta  */
            if(holder.overlay.getVisibility()!=View.GONE) {
                holder.overlay.setVisibility(View.GONE);
                holder.evidence.setAlpha(1f);
            }
        }
        //        if(position!=size-1){
            Glide.with(context).load(R.drawable.shape_profile).override(460,460).into( holder.evidence);
            /** este metodo maneja la selecion de coleccion de imagenes*/
            holder.evidence.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(holder.overlay.getVisibility()!=View.VISIBLE){/**este metodo ve si la foto no esta seleccionada  */
                        trashcount=false;
                        holder.evidence.setAlpha(0.5f);
                        holder.overlay.setVisibility(View.VISIBLE);

                        if(currentSelected.contains(String.valueOf(position))){/** si contiene el index de la foto no deberia hacer nada*/

                        }else {                                /** si no contiene el index de la foto deberia agregar el index*/
                            currentSelected.add(String.valueOf( position));
                        }
                    }else{                                     /**este metodo ve si la foto esta seleccionada  */
                        holder.evidence.setAlpha(1f);
                        holder.overlay.setVisibility(View.GONE);
                        if(currentSelected.contains(String.valueOf(position))){/** si contiene el index de la foto no deberia remover de la lista*/
                            currentSelected.remove(String.valueOf(position));
                        }else {                                /** si no contiene el index de la foto no deberia hacer nada*/

                        }
                    }

                    mview.sawTrash(currentSelected);

                    return false;
                }
            });
//        }
//        else
//        {
//            Glide.with(context).load(R.drawable.ic_cameraportraitico).override(460,460).into( holder.evidence);
//
//        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void updateSize(int sizeAfterErase, boolean isUpdated) {
        this.trashcount=isUpdated;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout constraintOrder;
        public ImageView evidence,overlay;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintOrder=itemView.findViewById(R.id.carrusel);
            evidence=itemView.findViewById(R.id.evidence);
            overlay= itemView.findViewById(R.id.overlay);

        }
    }
}
