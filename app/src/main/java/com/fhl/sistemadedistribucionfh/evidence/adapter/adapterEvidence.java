package com.fhl.sistemadedistribucionfh.evidence.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.evidence.evidencia;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.EvidenciaLlegada;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.EvidenciaSalida;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.dataTicketsDetailsendtrip;

import java.util.List;

public class adapterEvidence extends RecyclerView.Adapter<adapterEvidence.ViewHolder> {
    private Context context;
    private Integer flowDetail;
    private List<dataTicketsDetailsendtrip> data;
    private Integer hassignature,hasReview,hasphotos,hasdocuments,hasvideos,haschecklist=0;
    private Boolean hasSignatureok,hasReviewok,hasPhotosok,hasFilesok,hasVideosok=false;
    private evidencia mview;


    public adapterEvidence(evidencia mview,Integer flowDetail, Context context, List<dataTicketsDetailsendtrip> data) {
        //region valuesColorsChecks
        this.hasSignatureok=false;
        this.hasReviewok=false;
        this.hasPhotosok=false;
        this.hasFilesok=false;
        this.hasVideosok=false;
        //endregion
        this.flowDetail=flowDetail;
        this.context = context;
        this.data=data;
        this.mview=mview;
        hassignature=1;
        hasReview=1;
        hasphotos=0;
        hasvideos=0;
        if(flowDetail==2){//TODO viene de recoleccion o salida
            if(data.get(0).getEvidenciaSalida()!=null){
                for(EvidenciaSalida evidence:data.get(0).getEvidenciaSalida()){
                    if(evidence.getTipoEvidencia()==2){
                        hasphotos=1;
                    }else if(evidence.getTipoEvidencia()==3){
                        hasdocuments=1;
                    }
//                    else if(evidence.getTipoEvidencia()==4){todo descomentar una vez mergeado a master
//                        hasvideos=1;
//                    }
                }
            }else {
                hasdocuments=0;
                hasphotos=0;
                hasvideos=0;
            }
//            if(data.get(0).getCheckList()!=null){todo descomentar una vez mergeado a master
//                haschecklist=1;
//            }
        }else{//TODO viene de entrega de ticket
            if(data.get(0).getEvidenciaLlegada()!=null){
                for(EvidenciaLlegada evidence:data.get(0).getEvidenciaLlegada()){
                    if(evidence.getTipoEvidencia()==2){
                        hasphotos=1;
                    }else if(evidence.getTipoEvidencia()==3){
                        hasdocuments=1;
                    }
//                    else if(evidence.getTipoEvidencia()==4){todo descomentar una vez mergeado a master
//                        hasvideos=1;
//                    }
                }
            }
            if(data.get(0).getCheckList()!=null){
                haschecklist=1;
            }
        }
    }
    public void updatefirma(Boolean mfirma){
        this.hasSignatureok=mfirma;
        Log.e("color de imagen","set firma "+hasSignatureok);
        notifyDataSetChanged();
    }
    public void encuesta(Boolean mrating){
        this.hasReviewok=mrating;
        notifyDataSetChanged();
    }
    public void foto(Boolean mfoto){
        this.hasPhotosok=mfoto;
        notifyDataSetChanged();
    }
    public void archivo(Boolean mfiles){
        this.hasFilesok=mfiles;
        notifyDataSetChanged();
    }
    public void video(){
        //todo hacer para este
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_evidence, parent, false);//item_carrito
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterEvidence.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        int itemCount = 0;
        if (hassignature == 1) {

            if (position == itemCount) {
                // Firma always exists at position 0
                Drawable background = ContextCompat.getDrawable(context, R.drawable.ic_signature_ico);
                holder.image.setBackground(background);
                if(hasSignatureok==true){
                    Drawable background2 = ContextCompat.getDrawable(context, R.drawable.ic_signature_ico_green);
                    holder.image.setBackground(background2);
                    Log.e("color de imagen","verde firma");
                }else{
                  //  holder.image.setColorFilter(Color.rgb(112, 112, 112), PorterDuff.Mode.SRC_ATOP);
                    Log.e("color de imagen","gris firma");
                }
                holder.description.setText("Firma");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle signature click
                        // Example: Toast.makeText(context, "Signature clicked", Toast.LENGTH_SHORT).show();
                        mview.gosignature();
                    }
                });
                return;
            }
            itemCount++;
        }

        if (hasReview == 1) {

            if (position == itemCount) {
                Drawable background4 = ContextCompat.getDrawable(context, R.drawable.ic_rankico);
                holder.image.setBackground(background4);
                if(hasReviewok){
                    Drawable background2 = ContextCompat.getDrawable(context, R.drawable.ic_rankico_green);
                    holder.image.setBackground(background2);
                }else{
                    //holder.image.setColorFilter(Color.rgb(112, 112, 112), PorterDuff.Mode.SRC_ATOP);
                }
                holder.description.setText("Encuesta");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle signature click
                        mview.goReview();
                    }
                });
                return;
            }
            itemCount++;
        }

        if (hasphotos == 1) {

            if (position == itemCount) {
                Drawable backgroundf = ContextCompat.getDrawable(context, R.drawable.ic_cameraico);
                holder.image.setBackground(backgroundf);
                if(hasPhotosok){
                    Drawable background2 = ContextCompat.getDrawable(context, R.drawable.ic_cameraico_green);
                    holder.image.setBackground(background2);
                }else{
                    //holder.image.setColorFilter(Color.rgb(112, 112, 112), PorterDuff.Mode.SRC_ATOP);
                }
                holder.description.setText("Foto");

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (flowDetail == 2) { // TODO viene de recoleccion salida
                            if (data.get(0).getEvidenciaSalida() != null) {
                                // Handle photo evidence logic
                                mview.goCarrusel(data.get(0).getEvidenciaSalida(),null);
                            }
                        } else { // TODO viene de entrega de ticket
                            if (data.get(0).getEvidenciaLlegada() != null) {
                                // Handle photo evidence logic
                                mview.goCarrusel(null,data.get(0).getEvidenciaLlegada());
                            }
                        }

                    }
                });
                return;
            }
            itemCount++;
        }

        if (hasdocuments == 1) {

            if (position == itemCount) {
                Drawable backgroundD = ContextCompat.getDrawable(context, R.drawable.ic_clipo);
                holder.image.setBackground(backgroundD);
                if(hasFilesok){
                    Drawable background2 = ContextCompat.getDrawable(context, R.drawable.ic_clipo_green);
                    holder.image.setBackground(background2);
                }else{
                    //holder.image.setColorFilter(Color.rgb(112, 112, 112), PorterDuff.Mode.SRC_ATOP);
                }
                holder.description.setText("Archivo adjunto");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (flowDetail == 2) { // TODO viene de recoleccion salida
                            if (data.get(0).getEvidenciaSalida() != null) {
                                mview.goDocuments(data.get(0).getEvidenciaSalida(),null,flowDetail);
                            }
                        } else { // TODO viene de entrega de ticket
                           if (data.get(0).getEvidenciaLlegada() != null) {
                               mview.goDocuments(null, data.get(0).getEvidenciaLlegada(),flowDetail);
                            }
                        }
                    }
                });
                return;
            }
            itemCount++;
        }

        if (hasvideos == 1) {
            if (position == itemCount) {
                Drawable backgroundV = ContextCompat.getDrawable(context, R.drawable.video);
                holder.image.setBackground(backgroundV);
                holder.description.setText("Video");
                // Handle video logic if needed
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (flowDetail == 2) { // TODO viene de recoleccion salida
                            if (data.get(0).getEvidenciaSalida() != null) {
                                mview.goVideos(data.get(0).getEvidenciaSalida(),null,flowDetail);
                            }
                        } else { // TODO viene de entrega de ticket
                            if (data.get(0).getEvidenciaLlegada() != null) {
                                mview.goVideos(null, data.get(0).getEvidenciaLlegada(),flowDetail);
                            }
                        }
                    }
                });
                return;
            }
            itemCount++;
        }
        if(haschecklist==1){
            if (position == itemCount) {
                Drawable backgroundV = ContextCompat.getDrawable(context, R.drawable.ic_menu_checklist_icon);
                holder.image.setBackground(backgroundV);
                holder.description.setText("Checklist");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mview.gochecklist(data.get(0).getCheckList());
                    }
                });
                return;
            }

        }
    }

    @Override
    public int getItemCount() {
        return hassignature
                +hasReview
                +hasphotos
                +hasdocuments
                +hasvideos
                +haschecklist;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView   description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.imageMenu3video);
            description=itemView.findViewById(R.id.menuName3video);
        }
    }
}