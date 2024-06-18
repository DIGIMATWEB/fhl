package com.fhl.sistemadedistribucionfh.Tickets.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Tickets.model.ticketsdetail.dataDetailTickets;
import com.fhl.sistemadedistribucionfh.Tickets.view.tickets;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.Item;

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
        if(data!=null && data.size()!=0) {
            holder.ticketNum.setText("" + data.get(position).getFolioTicket());
            holder.cliente.setText("" + data.get(position).getCliente().getRazonSocial());
            holder.contacto.setText(""+data.get(0).getSendtripPlus().getRemitente().getNombre());
            if(data.get(0).getSendtripPlus().getPaquetes()!=null){
                String productos="";
                if(data.get(0).getSendtripPlus().getPaquetes().get(0).getItems()!=null){
                    for(Item p:data.get(0).getSendtripPlus().getPaquetes().get(0).getItems()){
                        if(data.get(0).getSendtripPlus().getPaquetes().get(0).getItems().size()==1){
                            productos = productos + p.getDescripcion();
                        }else {
                            productos = productos + " " + p.getDescripcion() + " ,";
                        }
                    }
                    holder.Productos.setText(productos);
                }
            }


            //Comprobamos la data
            if (data.size() > position && data.get(position).getCheckList() != null && !data.get(position).getCheckList().isEmpty()) {
                // Verificar si la lista checkList no está vacía antes de acceder al primer elemento
                holder.checklist.setText("" + data.get(position).getCheckList().get(0).getValor());
            } else {
                // Manejar el caso en el que la lista está vacía o la posición es inválida
                holder.checklist.setText("--- --- ---");
            }

            //Esto estaba antes
            //holder.checklist.setText("" + data.get(position).getCheckList().get(0).getValor());//considerar que pueden ser mas Valor de checklist
            if(data.get(position).getDocumentosAuxiliar()!=null) {
                holder.textAdjuntos.setText("Cantidad: " + data.get(position).getDocumentosAuxiliar().size());//todo catalogar todos los adjuntos
            }

            holder.origen.setText("" + data.get(0).getSendtripPlus().getRemitente().getNombre());
            holder.estado.setText(" " +data.get(0).getSendtripPlus().getDestinatario().getNombreSucursal());//"" + data.get(0).getSendtripPlus().getDestinatario().getEstado()
                               // +" "+data.get(0).getSendtripPlus().getDestinatario().getMunicipio()
                              //  +" "+data.get(0).getSendtripPlus().getDestinatario().getCalle());
            //holder.salida.setText(""+data.get(position).getFechaSalidaEstimada());
            holder.regreso.setText("-- -- --");
            holder.locationDesc.setText(""+data.get(0).getSendtripPlus().getDestinatario().getEstado());//+data.get(position).getEmpaque().get(0).getDestinatarios().get(0).getCiudad());
            holder.latlongGeo.setText(""+data.get(0).getSendtripPlus().getDestinatario().getCoordenadas()); //+ data.get(position).getDestinatarios().getCoordenadas());
            holder.lalongReport.setText("" + data.get(0).getSendtripPlus().getFechaPromesaEntrega());
        }
        holder.destinoMinimapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.goToMaps(data.get(0).getSendtripPlus().getDestinatario().getCoordenadas());
            }
        });
        holder.cardOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
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
        ImageView destinoMinimapa;
        TextView ticketNum,cliente,contacto,Productos,textAdjuntos,checklist,statusCierre,origen,estado,salida,regreso,locationDesc,latlongGeo,lalongReport;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardOrder=itemView.findViewById(R.id.constrainCard);
            destinoMinimapa=itemView.findViewById(R.id.destinoMinimapa);
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
