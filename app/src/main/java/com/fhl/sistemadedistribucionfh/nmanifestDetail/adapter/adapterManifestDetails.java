package com.fhl.sistemadedistribucionfh.nmanifestDetail.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.view.manifestDetailV2;

import java.util.ArrayList;
import java.util.List;

public class adapterManifestDetails extends RecyclerView.Adapter<adapterManifestDetails.ViewHolder>{
    private manifestDetailV2 mview;
    private Context context;
    private List<dataTicketsManifestV2> data;
    private List<String> selectedItems=new ArrayList<>();
    private int size;
    public adapterManifestDetails(manifestDetailV2 mview, List<dataTicketsManifestV2> data, int size, Context context) {
        selectedItems.clear();
        this.mview=mview;
        this.size = size;
        this.context=context;
        this.data=data;
    }
    @NonNull
    @Override
    public adapterManifestDetails.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_singleticket, parent, false);//item_carrito
        return  new adapterManifestDetails.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull adapterManifestDetails.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.ticketFolio.setText(data.get(position).getFolioTicket());//data.get(position).getFolioTicket());
        Integer statusId= data.get(position).getEstatusId();
        if(data.get(position).getEstatusId() != null) {
            // Tipo entrega
            if(data.get(position).getTipoEntregaId()==2) {
                holder.textView99.setText("E");
            }else {
                holder.textView99.setText("R");
            }

            // Estatus
            if(statusId==1){
                holder.statusTicket.setText("En cola");
                holder.statusTicket.setTextColor(ContextCompat.getColor(context, R.color.grey));
            }else if(statusId==2){
                holder.statusTicket.setText("Asignado");
                holder.statusTicket.setTextColor(ContextCompat.getColor(context, R.color.yellowdark));

                if(data.get(position).getTipoEntregaId()==2) {
                    holder.switchSelector.setVisibility(View.GONE);
                }else{
                    holder.switchSelector.setVisibility(View.VISIBLE);
                    holder.switchSelector.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                if (!selectedItems.contains(data.get(position).getFolioTicket())) {
                                    selectedItems.add(data.get(position).getFolioTicket());
                                }//else do nothing
                            } else {
                                if (selectedItems.contains(data.get(position).getFolioTicket())) {
                                    selectedItems.remove(data.get(position).getFolioTicket());
                                }
                            }
                            mview.checkFoliosSelected(selectedItems);
                        }
                    });
                }
            }else if(statusId==3){
                holder.statusTicket.setText("En ruta");
                holder.switchSelector.setVisibility(View.GONE);
                holder.statusTicket.setTextColor(ContextCompat.getColor(context, R.color.purpleenruta));
            }else if(statusId==4){
                holder.statusTicket.setText("Entregado");
                holder.switchSelector.setVisibility(View.GONE);
                holder.statusTicket.setTextColor(ContextCompat.getColor(context, R.color.green));
            }else if(statusId==5){
                holder.statusTicket.setText("No entregado");
                holder.switchSelector.setVisibility(View.GONE);
                holder.statusTicket.setTextColor(ContextCompat.getColor(context, R.color.orangenoentregado));
            }else if(statusId==6){
                holder.statusTicket.setText("Transferido");
                holder.switchSelector.setVisibility(View.GONE);
            }else if(statusId==8){
            holder.statusTicket.setText("Con devolucion");
                holder.switchSelector.setVisibility(View.GONE);
                holder.freametouch.setVisibility(View.GONE);
                holder.masText.setVisibility(View.GONE);
                holder.siguiente.setVisibility(View.GONE);
            }else{
                holder.statusTicket.setText("");
            }

        } else {
            holder.statusTicket.setText(""); // or any other default value you prefer
        }

        // Custodia
        Integer custodiaId = 0;
        String custodiosString = "";
        if(data.get(position).getTipoCustodiaId() != null) {
            if(data.get(position).getTipoCustodiaId().equals(0)){
                holder.custodioStatus.setText("-- --");
            } else {
                custodiaId = data.get(position).getTipoCustodiaId();

                // Dependiendo el resultado
                switch (custodiaId){
                    case 1:
                        holder.custodioStatus.setText("Armada");
                        custodiosString = "Armada";
                        break;
                    case 2:
                        holder.custodioStatus.setText("Sencilla");
                        custodiosString = "Sencilla";
                        break;
                    case 3:
                        holder.custodioStatus.setText("Sin custodia");
                        custodiosString = "Sin custodia";
                        break;
                    case 4:
                        holder.custodioStatus.setText("Hasta caseta");
                        custodiosString = "Hasta caseta";
                        break;
                    case 5:
                        holder.custodioStatus.setText("ArmadaInsertar");
                        custodiosString = "ArmadaInsertar";
                        break;
                    case 0:
                        holder.custodioStatus.setText("-- --");
                        custodiosString = "-- --";
                        break;
                }
            }
        } else {
            holder.custodioStatus.setText("-- --");
        }

        // Aviso de Peligro
        if(data.get(position).getPeligroso() == null || data.get(position).getPeligroso().isEmpty()) {
            holder.warningStatus.setVisibility(View.GONE);
        } else {
            holder.warningStatus.setVisibility(View.VISIBLE);
        }

        // Touch
        String finalCustodiosString = custodiosString;
        holder.freametouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
                mview.gotoTickets(position,data.get(position).getFolioTicket(),data.get(position).getEstatusId(), finalCustodiosString);
            }
        });
    }
    public void setFilter(List<dataTicketsManifestV2> filterList) {
        this.data = new ArrayList<>();
        this.data.addAll(filterList);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout cardOrder;
        TextView ticketFolio,statusTicket,masText,textView99, custodioStatus, warningStatus;
        ImageView siguiente;
        Switch switchSelector;
        FrameLayout freametouch;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            masText=itemView.findViewById(R.id.masText);
            siguiente=itemView.findViewById(R.id. siguiente);
            freametouch=itemView.findViewById(R.id.freametouch);
            cardOrder=itemView.findViewById(R.id.constrainCard);
            ticketFolio = itemView.findViewById(R.id.ticketFolio);
            statusTicket= itemView.findViewById(R.id.statusTicket);
            switchSelector = itemView.findViewById(R.id.switchSelector);
            textView99 =itemView.findViewById(R.id.textView99);
            custodioStatus = itemView.findViewById(R.id.textCustodiaSencillaCount);
            warningStatus = itemView.findViewById(R.id.warningStatus);
        }
    }
}
