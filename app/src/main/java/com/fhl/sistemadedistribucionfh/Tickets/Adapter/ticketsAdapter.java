package com.fhl.sistemadedistribucionfh.Tickets.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.Tickets.model.ticketsdetail.ResponseHabilidades;
import com.fhl.sistemadedistribucionfh.Tickets.model.ticketsdetail.dataDetailTickets;
import com.fhl.sistemadedistribucionfh.Tickets.view.tickets;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.Destinatario;
import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.Item;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ticketsAdapter extends RecyclerView.Adapter<ticketsAdapter.ViewHolder>{
    private Context context;
    private  int size;
    private tickets mView;
    private List<dataDetailTickets> data;
    private String custodiosStatus, validacionApp;
    private String coordenadasRemitenteTotal, coordenadasDestinoTotal;
    public ticketsAdapter(tickets mmanifest, List<dataDetailTickets> data, int size, Context context, String custodiosStatus, String validacionApp) {
        this.mView=mmanifest;
        this.size=size;
        this.context=context;
        this.data=data;
        this.custodiosStatus = custodiosStatus;
        this.validacionApp = validacionApp;
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
            // Numero de ticket
            if(data.get(position).getFolioTicket() != null) {
                if(data.get(position).getFolioTicket().isEmpty()) {
                    holder.ticketNum.setText("-- --");
                } else {
                    holder.ticketNum.setText(data.get(position).getFolioTicket());
                }
            } else {
                holder.ticketNum.setText("-- --");
            }

            // Custodia
            if(custodiosStatus.isEmpty()) {
                holder.custodioStatus.setText("-- --");
            } else {
                holder.custodioStatus.setText(custodiosStatus);
            }

            // Advertencia
            if(data.get(position).getPeligroso() != null) {
                if(data.get(position).getPeligroso().isEmpty()) {
                    holder.advertencia.setText("-- --");
                } else {
                    holder.advertencia.setText("Es peligroso");
                }
            } else {
                holder.advertencia.setText("-- --");
            }

            // Remitente
            if(data.get(0).getSendtripPlus().getRemitente() != null) {
                if (data.get(0).getSendtripPlus().getRemitente().getNombre() != null) {
                    if(data.get(0).getSendtripPlus().getRemitente().getNombre().isEmpty()) {
                        holder.cliente.setText("-- --");
                    } else {
                        holder.cliente.setText(data.get(0).getSendtripPlus().getRemitente().getNombre());
                    }
                } else {
                    holder.cliente.setText("-- --");
                }
            } else {
                holder.cliente.setText("-- --");
            }

            // Destinatario
            if(data.get(0).getSendtripPlus().getDestinatario() != null) {
                if(data.get(0).getSendtripPlus().getDestinatario().getNombre() != null) {
                    if(data.get(0).getSendtripPlus().getDestinatario().getNombre().isEmpty()) {
                        holder.destinatario.setText("-- --");
                    } else {
                        holder.destinatario.setText(data.get(0).getSendtripPlus().getDestinatario().getNombre());
                    }
                } else {
                    holder.destinatario.setText("-- --");
                }
            } else {
                holder.destinatario.setText("-- --");
            }


            // Estado del ticket
            if(data.get(position).getEstatus() != null) {
                if(data.get(position).getEstatus().getNombre() != null) {
                    if(data.get(position).getEstatus().getNombre().isEmpty()) {
                        holder.origen.setText("-- --");
                    } else {
                        holder.origen.setText(data.get(position).getEstatus().getNombre());
                    }
                } else {
                    holder.origen.setText("-- --");
                }
            } else {
                holder.origen.setText("-- --");
            }

            // Remitente
            if(data.get(0).getSendtripPlus().getRemitente() != null) {
                // Calle + NE + NI + Colinia + Ciudad + Estado + Pais + CP
                String calle, nE, nI, colonia, ciudad, estado, pais, cP, georeferenciaRemitenteTotal;

                // Calle
                if(data.get(0).getSendtripPlus().getRemitente().getCalle() != null) {
                    if(data.get(0).getSendtripPlus().getRemitente().getCalle().isEmpty()) {
                        calle = "Sin calle";
                    } else {
                        calle = data.get(0).getSendtripPlus().getRemitente().getCalle();
                    }
                } else {
                    calle = "Sin calle";
                }

                // Numero Exterior
                if(data.get(0).getSendtripPlus().getRemitente().getNumeroExterior() != null){
                    if(data.get(0).getSendtripPlus().getRemitente().getNumeroExterior().isEmpty()) {
                        nE = "Sin NE";
                    } else {
                        nE = data.get(0).getSendtripPlus().getRemitente().getNumeroExterior();
                    }
                } else {
                    nE = "Sin NE";
                }

                // Numero interior
                if(data.get(0).getSendtripPlus().getRemitente().getNumeroInterior() != null) {
                    if(data.get(0).getSendtripPlus().getRemitente().getNumeroInterior().isEmpty()) {
                        nI = "Sin NI";
                    } else {
                        nI = data.get(0).getSendtripPlus().getRemitente().getNumeroInterior();
                    }
                } else {
                    nI = "Sin NI";
                }

                // Colonia
                if(data.get(0).getSendtripPlus().getRemitente().getColonia() != null) {
                    if(data.get(0).getSendtripPlus().getRemitente().getColonia().isEmpty()) {
                        colonia = "Sin colonia";
                    } else {
                        colonia = data.get(0).getSendtripPlus().getRemitente().getColonia();
                    }
                } else {
                    colonia = "Sin colonia";
                }

                // Ciudad (Municipio)
                if(data.get(0).getSendtripPlus().getRemitente().getMunicipio() != null) {
                    if(data.get(0).getSendtripPlus().getRemitente().getMunicipio().isEmpty()) {
                        ciudad = "Sin ciudad";
                    } else {
                        ciudad = data.get(0).getSendtripPlus().getRemitente().getMunicipio();
                    }
                } else {
                    ciudad = "Sin ciudad";
                }

                // Estado
                if(data.get(0).getSendtripPlus().getRemitente().getEstado() != null) {
                    if(data.get(0).getSendtripPlus().getRemitente().getEstado().isEmpty()) {
                        estado = "Sin estado";
                    } else {
                        estado = data.get(0).getSendtripPlus().getRemitente().getEstado();
                    }
                } else {
                    estado = "Sin estado";
                }

                // Pais
                if(data.get(0).getSendtripPlus().getRemitente().getPais() != null) {
                    if(data.get(0).getSendtripPlus().getRemitente().getPais().isEmpty()) {
                        pais = "Sin país";
                    } else {
                        pais = data.get(0).getSendtripPlus().getRemitente().getPais();
                    }
                } else {
                    pais = "Sin país";
                }

                // Codigo Postal
                if(data.get(0).getSendtripPlus().getRemitente().getCodigoPostal() != null) {
                    cP = String.valueOf(data.get(0).getSendtripPlus().getRemitente().getCodigoPostal());
                } else {
                    cP = "Sin CP";
                }

                // Juntamos los datos
                georeferenciaRemitenteTotal = calle + ", " + nE + ", " + nI + ", " + colonia + ", " + ciudad + ", " + estado + ", " + pais + ", " + cP;
                holder.georeferenciaRemitente.setText(georeferenciaRemitenteTotal);
            } else {
                holder.georeferenciaRemitente.setText("-- --");
            }

            // Destinatario
            if(data.get(0).getSendtripPlus().getDestinatario() != null) {
                // Calle + NE + NI + Colinia + Ciudad + Estado + Pais + CP
                String calle, nE, nI, colonia, ciudad, estado, pais, cP, georeferenciaDestinoTotal;

                // Calle
                if(data.get(0).getSendtripPlus().getDestinatario().getCalle() != null) {
                    if(data.get(0).getSendtripPlus().getDestinatario().getCalle().isEmpty()) {
                        calle = "Sin calle";
                    } else {
                        calle = data.get(0).getSendtripPlus().getDestinatario().getCalle();
                    }
                } else {
                    calle = "Sin calle";
                }

                // Numero Exterior
                if(data.get(0).getSendtripPlus().getDestinatario().getNumeroExterior() != null) {
                    if(data.get(0).getSendtripPlus().getDestinatario().getNumeroExterior().isEmpty()) {
                        nE = "Sin NE";
                    } else {
                        nE = data.get(0).getSendtripPlus().getDestinatario().getNumeroExterior();
                    }
                } else {
                    nE = "Sin NE";
                }

                // Numero interior
                if(data.get(0).getSendtripPlus().getDestinatario().getNumeroInterior() != null) {
                    if(data.get(0).getSendtripPlus().getDestinatario().getNumeroInterior().isEmpty()) {
                        nI = "Sin NI";
                    } else {
                        nI = data.get(0).getSendtripPlus().getDestinatario().getNumeroInterior();
                    }
                } else {
                    nI = "Sin NI";
                }

                // Colonia
                if(data.get(0).getSendtripPlus().getDestinatario().getColonia() != null) {
                    if(data.get(0).getSendtripPlus().getDestinatario().getColonia().isEmpty()) {
                        colonia = "Sin colonia";
                    } else {
                        colonia = data.get(0).getSendtripPlus().getDestinatario().getColonia();
                    }
                } else {
                    colonia = "Sin colonia";
                }

                // Ciudad (Municipio)
                if(data.get(0).getSendtripPlus().getDestinatario().getMunicipio() != null) {
                    if(data.get(0).getSendtripPlus().getDestinatario().getMunicipio().isEmpty()) {
                        ciudad = "Sin ciudad";
                    } else {
                        ciudad = data.get(0).getSendtripPlus().getDestinatario().getMunicipio();
                    }
                } else {
                    ciudad = "Sin ciudad";
                }

                // Estado
                if(data.get(0).getSendtripPlus().getDestinatario().getEstado() != null) {
                    if(data.get(0).getSendtripPlus().getDestinatario().getEstado().isEmpty()) {
                        estado = "Sin estado";
                    } else {
                        estado = data.get(0).getSendtripPlus().getDestinatario().getEstado();
                    }
                } else {
                    estado = "Sin estado";
                }

                // Pais
                if(data.get(0).getSendtripPlus().getDestinatario().getPais() != null) {
                    if(data.get(0).getSendtripPlus().getDestinatario().getPais().isEmpty()) {
                        pais = "Sin país";
                    } else {
                        pais = data.get(0).getSendtripPlus().getDestinatario().getPais();
                    }
                } else {
                    pais = "Sin país";
                }

                // Codigo Postal
                if(data.get(0).getSendtripPlus().getDestinatario().getCodigoPostal() != null) {
                    cP = String.valueOf(data.get(0).getSendtripPlus().getDestinatario().getCodigoPostal());
                } else {
                    cP = "Sin CP";
                }

                // Juntamos los datos
                georeferenciaDestinoTotal = calle + ", " + nE + ", " + nI + ", " + colonia + ", " + ciudad + ", " + estado + ", " + pais + ", " + cP;
                holder.georeferenciaDestino.setText(georeferenciaDestinoTotal);
            } else {
                holder.georeferenciaDestino.setText("-- --");
            }

            // Hora llegada remitente
            if(data.get(0).getSendtripPlus().getFechaPromesaCarga() != null) {
                if(data.get(0).getSendtripPlus().getFechaPromesaCarga().isEmpty()) {
                    holder.horaLlegadaRemitente.setText("-- --");
                } else {
                    String formattedDateString = convertDateFormat(data.get(0).getSendtripPlus().getFechaPromesaCarga());
                    holder.horaLlegadaRemitente.setText(formattedDateString);
                }
            } else {
                holder.horaLlegadaRemitente.setText("-- --");
            }

            // Hora llegada destino
            if(data.get(0).getSendtripPlus().getFechaPromesaEntrega() != null) {
                if(data.get(0).getSendtripPlus().getFechaPromesaEntrega().isEmpty()) {
                    holder.horaLlegadaDestinatario.setText("-- --");
                } else {
                    String formattedDateString = convertDateFormat(data.get(0).getSendtripPlus().getFechaPromesaEntrega());
                    holder.horaLlegadaDestinatario.setText(formattedDateString);
                }
            } else {
                holder.horaLlegadaDestinatario.setText("-- --");
            }

            // Coordenadas Remitente
            if (data.get(0).getSendtripPlus().getRemitente() != null) {
                if(data.get(0).getSendtripPlus().getRemitente().getCoordenadas() != null) {
                    if(data.get(0).getSendtripPlus().getRemitente().getCoordenadas().isEmpty()) {
                        coordenadasRemitenteTotal = "";
                        holder.destinoMinimapa.setVisibility(View.GONE);
                    } else {
                        coordenadasRemitenteTotal = data.get(0).getSendtripPlus().getRemitente().getCoordenadas();
                        holder.destinoMinimapa.setVisibility(View.VISIBLE);
                    }
                } else {
                    coordenadasRemitenteTotal = "";
                    holder.destinoMinimapa.setVisibility(View.GONE);
                }
            } else {
                coordenadasRemitenteTotal = "";
                holder.destinoMinimapa.setVisibility(View.GONE);
            }

            // Coordenadas Destino
            if(data.get(0).getSendtripPlus().getDestinatario() != null) {
                if(data.get(0).getSendtripPlus().getDestinatario().getCoordenadas() != null) {
                    if(data.get(0).getSendtripPlus().getDestinatario().getCoordenadas().isEmpty()) {
                        coordenadasDestinoTotal = "";
                        holder.destinoMinimapaDestino.setVisibility(View.GONE);
                    } else {
                        coordenadasDestinoTotal = data.get(0).getSendtripPlus().getDestinatario().getCoordenadas();
                        holder.destinoMinimapaDestino.setVisibility(View.VISIBLE);
                    }
                } else {
                    coordenadasDestinoTotal = "";
                    holder.destinoMinimapaDestino.setVisibility(View.GONE);
                }
            } else {
                coordenadasDestinoTotal = "";
                holder.destinoMinimapaDestino.setVisibility(View.GONE);
            }
        }

        // Listener de Remitente
        holder.destinoMinimapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mView.goToMaps(data.get(0).getSendtripPlus().getDestinatario().getCoordenadas());
                mView.goToMaps(coordenadasRemitenteTotal);
            }
        });

        // Para la lista de Habilidades
        // String jsonString = "{\"HabilidadesVehiculos\":[],\"DocumentosVencimientosVehiculos\":[],\"HabilidadesOperadores\":[],\"DocumentosOperadoresVencimientos\":[],\"HabilidadesAuxiliares\":[],\"DocumentosAuxiliaresVencimientos\":[],\"EvidenciasCargas\":[{\"Llave\":6,\"Valor\":\"Fotografia de factura con firma\"}],\"EvidenciasLlegadas\":[],\"ChecklistRequeridos\":[]}";
        String jsonString = validacionApp;
        String listHabilidadesVehiculo = "", listHabilidadesOperador = "", listHabilidadesAuxiliares = "";

        Gson gson = new Gson();
        ResponseHabilidades responseData = gson.fromJson(jsonString, ResponseHabilidades.class);

        // Procesar HabilidadesVehiculos
        if (responseData.habilidadesVehiculos == null || responseData.habilidadesVehiculos.isEmpty()) {
            //System.out.println("HabilidadesVehiculos está vacío");
            listHabilidadesVehiculo = "Sin habilidades";
        } else {
            listHabilidadesVehiculo = responseData.habilidadesVehiculos.stream()
                    .map(item -> item.valor)
                    .collect(Collectors.joining(", "));
            //System.out.println("ListHabilidadesVehiculo: " + listHabilidadesVehiculo);
        }

        // Procesar HabilidadesOperadores
        if (responseData.habilidadesOperadores == null || responseData.habilidadesOperadores.isEmpty()) {
            //System.out.println("HabilidadesOperadores está vacío");
            listHabilidadesOperador = "Sin habilidades";
        } else {
            listHabilidadesOperador = responseData.habilidadesOperadores.stream()
                    .map(item -> item.valor)
                    .collect(Collectors.joining(", "));
            //System.out.println("ListHabilidadesOperador: " + listHabilidadesOperador);
        }

        // Procesar HabilidadesAuxiliares
        if (responseData.habilidadesAuxiliares == null || responseData.habilidadesAuxiliares.isEmpty()) {
            //System.out.println("HabilidadesAuxiliares está vacío");
            listHabilidadesAuxiliares = "Sin habilidades";
        } else {
            listHabilidadesAuxiliares = responseData.habilidadesAuxiliares.stream()
                    .map(item -> item.valor)
                    .collect(Collectors.joining(", "));
            //System.out.println("ListHabilidadesAuxiliares: " + listHabilidadesAuxiliares);
        }

        String habilidadesTotal = "Vehículo: " + listHabilidadesVehiculo + ", Operador: " + listHabilidadesOperador + ", Auxiliares: " + listHabilidadesAuxiliares;
        holder.habilidadesList.setText(habilidadesTotal);

        //Listener de Destino
        holder.destinoMinimapaDestino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mView.goToMaps(coordenadasDestinoTotal);
            }
        });

        // Listener del Card
        holder.cardOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
                //mView.gotoTickets(position);
            }
        });
    }
    private String convertDateFormat(String originalDateString) {
        // Define el formato original
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS", Locale.getDefault());

        // Define el formato deseado
        SimpleDateFormat desiredFormat = new SimpleDateFormat("dd MMM HH:mm 'hrs'", Locale.getDefault());

        try {
            // Parsear la fecha original
            Date date = originalFormat.parse(originalDateString);

            // Formatear la fecha al formato deseado
            return desiredFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout cardOrder;
        ImageView destinoMinimapa, destinoMinimapaDestino;
        TextView ticketNum,cliente,checklist,origen, destinatario, advertencia, custodioStatus, georeferenciaRemitente, georeferenciaDestino, horaLlegadaRemitente, horaLlegadaDestinatario, habilidadesList;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardOrder=itemView.findViewById(R.id.constrainCard);
            destinoMinimapa=itemView.findViewById(R.id.destinoMinimapa);
            destinoMinimapaDestino=itemView.findViewById(R.id.destinoMinimapaDestino);
            ticketNum=itemView.findViewById(R.id.ticketNum);
            cliente=itemView.findViewById(R.id.clienteName);
            checklist=itemView.findViewById(R.id.textDe);
            origen=itemView.findViewById(R.id.estado);
            destinatario = itemView.findViewById(R.id.destinatarioText);
            advertencia = itemView.findViewById(R.id.warningStatusTicket);
            custodioStatus = itemView.findViewById(R.id.custodioTicketText);
            georeferenciaRemitente = itemView.findViewById(R.id.remitenteDireccionTxt);
            georeferenciaDestino = itemView.findViewById(R.id.destinatarioSTTxt);
            horaLlegadaRemitente = itemView.findViewById(R.id.horaLlegadaRemitente);
            horaLlegadaDestinatario = itemView.findViewById(R.id.horaLlegadaDestinatario);
            habilidadesList = itemView.findViewById(R.id.habilidadesList);
        }
    }
}
