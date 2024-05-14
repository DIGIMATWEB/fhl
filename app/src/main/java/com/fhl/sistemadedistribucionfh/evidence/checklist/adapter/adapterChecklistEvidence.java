package com.fhl.sistemadedistribucionfh.evidence.checklist.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_checklist, parent, false);//item_carrito
        return new adapterChecklistEvidence.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterChecklistEvidence.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.namechecklist.setText("" + data.get(position).getValor());

        holder.cardOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mview.goQuestions("","","","");
            }
        });



        //region other module checklist
//        if(data.getVehiculoVsChecklist()!=null) {
//            if(data.getVehiculoVsChecklist().get(position).getChecklist()!=null) {
//                if(data.getVehiculoVsChecklist().get(position).getChecklist().getNombre()!=null) {
//                    holder.namechecklist.setText(data.getVehiculoVsChecklist().get(position).getChecklist().getNombre());
//                    periodicity(holder,position);
//                }
//            }
//        }
//        holder.vehiclTypeChecklist.setText(data.getVehiculo().getPlaca());
//
//
//
//        if (data.getVehiculoVsChecklist().get(position).getPeriodicidad() instanceof String) {
//            periodicidad=data.getVehiculoVsChecklist().get(position).getPeriodicidad().toString();
//            holder.manifestChecklist.setText("Por " +periodicidad);
//        } else if (data.getVehiculoVsChecklist().get(position).getPeriodicidad() instanceof Map) {
////            List<String> dias = (List<String>) ((Map<String, Object>) periodicidad).get("dias");
////            System.out.println("Periodicidad is an array of days: " + dias);
//            holder.manifestChecklist.setText("Por " );
//        } else {
//            holder.manifestChecklist.setText(": " );
//        }
//
//
//
//        holder.siguienteChecklist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String nombreTemp = "NombreTemp";
//                    /*if (data.getVehiculoVsChecklist().get(position).getChecklist().getNombre()== null) {
//                        nombreTemp = "NombreTemp";
//                    } else {
//                        nombreTemp = data.getVehiculoVsChecklist().get(position).getChecklist().getNombre();
//                    }*/
//
//                mview.goQuestions(nombreTemp,data.getVehiculo().getPlaca(),vigencia,periodicidad);
//                //mview.goQuestions(data.getVehiculoVsChecklist().get(position).getChecklist().getNombre(),data.getVehiculo().getPlaca(),vigencia,periodicidad);
//            }
//        });
        //endregion
    }
    /*public void periodicity(adapterChecklistEvidence.ViewHolder holder, int position) {
        String dateString = data.getVehiculoVsChecklist().get(position).getChecklist().getFechaVencimiento();

        try {
            LocalDate givenDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
            // Get the current date
            LocalDate currentDate = LocalDate.now();

            if (currentDate.isBefore(givenDate)) {
                vigencia = "Vigente";
                holder.statusChecklist.setText("Vigente");
                int mcolor = context.getColor(R.color.green);
                holder.statusChecklist.setTextColor(mcolor);
                // Your code for the future date scenario goes here
            } else if (givenDate.isEqual(currentDate)) {
                vigencia = "No vigente";
                holder.statusChecklist.setText("No vigente");
                int mcolor = context.getColor(R.color.red);
                holder.statusChecklist.setTextColor(mcolor);
                holder.siguienteChecklist.setVisibility(View.GONE);
                holder.moreChecklist.setVisibility(View.GONE);
            } else {
                vigencia = "No vigente";
                holder.statusChecklist.setText("No vigente");
                int mcolor = context.getColor(R.color.red);
                holder.statusChecklist.setTextColor(mcolor);
                holder.siguienteChecklist.setVisibility(View.GONE);
                holder.moreChecklist.setVisibility(View.GONE);
            }
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            Log.e("jsonChecklist",""+e.getMessage());

            // Handle the case where the date string cannot be parsed
        }
    }*/
    @Override
    public int getItemCount() {
        return data.size();
    }

//    public void setFilter(List<VehiculoVsCheck> filterList) {
//        this.data.getVehiculoVsChecklist() = new ArrayList<>();
//        this.data.addAll(filterList);
//        notifyDataSetChanged();
//    }

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