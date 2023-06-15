package com.fhl.sistemadedistribucionfh.cerrarViaje.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.cerrarViaje.view.cerrarViaje;

import java.util.ArrayList;
import java.util.List;

public class adapterNoCompletado extends RecyclerView.Adapter<adapterNoCompletado.ViewHolder> {

    private Context context;
    private boolean trashcount=false;
    private cerrarViaje mview;
    private List<String> currentSelected=new ArrayList<>();//items seleccionados
    private List<String> imageCollections;

    public adapterNoCompletado(cerrarViaje mview, List<String> imageCollections, Context context)
    {
        this.mview=mview;
        this.context=context;
        this.imageCollections=imageCollections;
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
      if(trashcount){///este metodo esta pendiente en caso de actualizar el carrusel oculta el overlay seleccionado y lo oculta  //
            if(holder.overlay.getVisibility()!=View.GONE) {
                holder.overlay.setVisibility(View.GONE);
                holder.evidence.setAlpha(1f);
            }
        }
        //        if(position!=size-1){
        byte[] imageBytes = Base64.decode(imageCollections.get(position), Base64.DEFAULT);

// Load and display the image using Glide
        Glide.with(context)
                .asBitmap()
                .load(imageBytes)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.NONE))
                .override(460, 460)  // Set the desired width and height for the image
                .into(holder.evidence);
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
                            mview.updateEraselist(currentSelected);
                        }
                    }else{                                     /**este metodo ve si la foto esta seleccionada  */
                        holder.evidence.setAlpha(1f);
                        holder.overlay.setVisibility(View.GONE);
                        if(currentSelected.contains(String.valueOf(position))){/** si contiene el index de la foto no deberia remover de la lista*/
                            currentSelected.remove(String.valueOf(position));
                            mview.updateEraselist(currentSelected);
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
        return imageCollections.size();
    }

    public void updateSize(List<String> sizeAfterErase, boolean isUpdated) {
        this.imageCollections=new ArrayList<>();
        this.imageCollections.addAll(sizeAfterErase);
        this.trashcount=isUpdated;
        currentSelected.clear();
        notifyDataSetChanged();
    }

    public void UpdateArray(List<String> imageCollections) {
        this.imageCollections=new ArrayList<>();
        this.imageCollections.addAll(imageCollections);
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
