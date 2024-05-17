package com.fhl.sistemadedistribucionfh.evidence.checklist.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Tickets.model.ticketsdetail.Check;
import com.fhl.sistemadedistribucionfh.evidence.checklist.view.checklistEvidence;

import java.util.List;

public class adapterChecklistEvidence extends RecyclerView.Adapter<adapterChecklistEvidence.ViewHolder> {
    private Context context;
    private List<Check> data;
    private checklistEvidence mview;
    private String vigencia="";
    private String periodicidad="";


    public adapterChecklistEvidence(checklistEvidence mview, List<Check> data, Context context) {
        this.data = data;
        this.context = context;
        this.mview= mview;

    }

    @NonNull
    @Override
    public adapterChecklistEvidence.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_checklist_evidences, parent, false);//item_carrito
        return new adapterChecklistEvidence.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterChecklistEvidence.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.namechecklist.setText("" + data.get(position).getValor());

        // Para el aplicado y no aplicado
        if (data.get(position).getAplicado() != null) {
            // Tiene datos
            if (data.get(position).getAplicado() == true) {
                // Es true
                holder.statusChecklist.setText("Contestado");
                holder.statusChecklist.setTextColor(ContextCompat.getColor(context, R.color.green2));
            } else {
                // Es false
                holder.statusChecklist.setText("No contestado");
                holder.statusChecklist.setTextColor(ContextCompat.getColor(context, R.color.red));
            }
        } else {
            // No tiene datos
            holder.statusChecklist.setText("No contestado");
            holder.statusChecklist.setTextColor(ContextCompat.getColor(context, R.color.red));
        }


        holder.cardOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data.get(position).getAplicado()!=null) {
                    if (!data.get(position).getAplicado()) {
                        mview.goQuestions(data, position);
                    } else {
                        Toast.makeText(context, "Este checklist ya fue contestado", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    mview.goQuestions(data, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout cardOrder;
        TextView vehiclTypeChecklist,manifestChecklist,statusChecklist,moreChecklist,namechecklist;
        ImageView siguienteChecklist;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardOrder = itemView.findViewById(R.id.constrainCard);
            siguienteChecklist=itemView.findViewById(R.id.siguienteChecklist);
            vehiclTypeChecklist= itemView.findViewById(R.id.vehiclTypeChecklist);
            manifestChecklist= itemView.findViewById(R.id.manifestChecklist);
            statusChecklist= itemView.findViewById(R.id.statusChecklist);
            moreChecklist= itemView.findViewById(R.id.moreChecklist);
            namechecklist=itemView.findViewById(R.id.namechecklist);

        }
    }
}