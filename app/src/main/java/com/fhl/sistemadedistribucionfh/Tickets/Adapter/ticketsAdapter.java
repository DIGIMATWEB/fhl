package com.fhl.sistemadedistribucionfh.Tickets.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Tickets.model.ticketsdetail.dataDetailTickets;
import com.fhl.sistemadedistribucionfh.Tickets.view.tickets;

import java.util.List;

public class ticketsAdapter extends RecyclerView.Adapter<ticketsAdapter.ViewHolder>{
    private Context context;
    private  int size;
    private tickets mView;
    private List<dataDetailTickets> data;
    public ticketsAdapter(tickets mmanifest, List<dataDetailTickets> data, int size, Context context) {
        this.mView=mmanifest;
        this.size=size;
        this.context=context;
        this.data=data;
    }

    @NonNull
    @Override
    public ticketsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tickets, parent, false);//item_carrito
        return  new ticketsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ticketsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.ticketNum.setText(""+data.get(position).getFolioTicket());
        holder.cliente.setText(""+data.get(position).getCliente().getRazonSocial());
        holder.contacto.setText(""+data.get(position).getDestinatarios().getContacto().getNombre());
        holder.Productos.setText("------ "+data.get(position).getCliente().getRazonSocial());
        holder.checklist.setText(""+data.get(position).getCheckList().get(0).getValor());//considerar que pueden ser mas Valor de checklist
        holder.textAdjuntos.setText("Cantidad: "+data.get(position).getDocumentosAuxiliar().size());//todo catalogar todos los adjuntos
        holder.origen.setText(""+data.get(position).getOrigen());
        holder.estado.setText(""+data.get(position).getOrigen());
        //holder.salida.setText(""+data.get(position).getFechaSalidaEstimada());
        holder.regreso.setText(""+data.get(position).getFechaPromesaRetorno());
        holder.locationDesc.setText("");//+data.get(position).getEmpaque().get(0).getDestinatarios().get(0).getCiudad());
        holder.latlongGeo.setText(""+data.get(position).getDestinatarios().getCoordenadas());
        holder.lalongReport.setText(""+data.get(position).getFechaPromesaEntrega());
        holder.cardOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
                //mView.gotoTickets(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout cardOrder;
        TextView ticketNum,cliente,contacto,Productos,textAdjuntos,checklist,statusCierre,origen,estado,salida,regreso,locationDesc,latlongGeo,lalongReport;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardOrder=itemView.findViewById(R.id.constrainCard);

            ticketNum=itemView.findViewById(R.id.ticketNum);
            cliente=itemView.findViewById(R.id.clienteName);
            contacto=itemView.findViewById(R.id.contacto);
            Productos=itemView.findViewById(R.id.Productos);
            checklist=itemView.findViewById(R.id.textDe);
            textAdjuntos=itemView.findViewById(R.id.textAdjuntos);
            origen=itemView.findViewById(R.id.estado);
            estado=itemView.findViewById(R.id.destino);
            salida=itemView.findViewById(R.id.salida_time);
            regreso=itemView.findViewById(R.id.regreso_time);
            locationDesc=itemView.findViewById(R.id.locationDesc);
            latlongGeo=itemView.findViewById(R.id.latlong);
            lalongReport=itemView.findViewById(R.id.lalongReport);
        }
    }
}
