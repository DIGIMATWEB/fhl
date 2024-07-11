package com.fhl.sistemadedistribucionfh.nmanifest.adapterV2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.dataManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifest.viewV2.mmanifestV2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class manifestAdapterV2 extends RecyclerView.Adapter<manifestAdapterV2.ViewHolder> {
    private Context context;
    private int size;
    private mmanifestV2 mView;
    private List<dataManifestV2> data;

    public manifestAdapterV2(mmanifestV2 mmanifest, List<dataManifestV2> data, int size, Context context) {
        this.mView = mmanifest;
        this.size = size;
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public manifestAdapterV2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_manifestv2, parent, false);
        return new manifestAdapterV2.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull manifestAdapterV2.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        //holder.vehicleName.setText(data.get(position).getVehiculoTercero());
        //holder.vehicleName.setText(data.get(position).getVehiculo().getMarca().getNombre());
        holder.vehicleName.setText(data.get(position).getVehiculo().getEconomico());
        holder.vehiclePlaca.setText(data.get(position).getVehiculo().getPlaca());
        holder.vehicleManifiesto.setText(data.get(position).getFolioDespacho());
        holder.vehicleCedis.setText(data.get(position).getOrigen());
        holder.textView70.setText(convertDate2(data.get(position).getFechaCreacion()));

        // Sendtrip Datos
        // Estado
        if (data.get(position).getTicketMasLejano().getSendtripPlus().getDestinatario() != null) {
            if (data.get(position).getTicketMasLejano().getSendtripPlus().getDestinatario().getEstado() != null) {
                if (data.get(position).getTicketMasLejano().getSendtripPlus().getDestinatario().getEstado().isEmpty()) {
                    // Ponemos un dato por Default
                    holder.textViewEstado.setText("-- --");
                } else {
                    // Ponemos el dato que tenga
                    holder.textViewEstado.setText(data.get(position).getTicketMasLejano().getSendtripPlus().getDestinatario().getEstado());
                }
            } else {
                // Ponemos un dato por Default
                holder.textViewEstado.setText("-- --");
            }
        } else {
            // Ponemos un dato por Default
            holder.textViewEstado.setText("-- --");
        }

        // Ciudad
        if (data.get(position).getTicketMasLejano().getSendtripPlus().getDestinatario() != null) {
            if (data.get(position).getTicketMasLejano().getSendtripPlus().getDestinatario().getMunicipio() != null) {
                if (data.get(position).getTicketMasLejano().getSendtripPlus().getDestinatario().getMunicipio().isEmpty()) {
                    // Dato por default
                    holder.textViewCiudad.setText("-- --");
                } else {
                    // Dato que contiene
                    holder.textViewCiudad.setText(data.get(position).getTicketMasLejano().getSendtripPlus().getDestinatario().getMunicipio());
                }
            } else {
                // Dato por default
                holder.textViewCiudad.setText("-- --");
            }
        } else {
            // Dato por default
            holder.textViewCiudad.setText("-- --");
        }

        holder.statusManifest.setText(data.get(position).getEstatus().getNombre());
        if (data.get(position).getRecolecionEntrega()!=null){
             if (data.get(position).getRecolecionEntrega() == false) {
                holder.validationTextInProgress.setVisibility(View.GONE);
                holder.validationText.setVisibility(View.GONE);
            } else {
                holder.validationTextInProgress.setVisibility(View.VISIBLE);
                holder.validationText.setVisibility(View.VISIBLE);
                holder.validationTextInProgress.setText(data.get(position).getValidador().getEstatus());
            }
        } else {
            data.get(position).setRecolecionEntrega(false);
            holder.validationTextInProgress.setVisibility(View.GONE);
            holder.validationText.setVisibility(View.GONE);
        }

        if (data.get(position).getEstatus().getNombre().equals("Confirmado")) {
            holder.statusManifest.setTextColor(ContextCompat.getColor(context, R.color.green));
        } else if (data.get(position).getEstatus().getNombre().equals("En proceso")) {
            holder.statusManifest.setTextColor(ContextCompat.getColor(context, R.color.yellowdark));
        } else if (data.get(position).getEstatus().getNombre().equals("Cerrado")) {
            holder.statusManifest.setTextColor(ContextCompat.getColor(context, R.color.red));
        } else {
            holder.statusManifest.setTextColor(ContextCompat.getColor(context, R.color.black));
        }

        holder.cardOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO llenar estos campos cuando se tengan
                String folioDespacho = data.get(position).getFolioDespacho();
                String custodio = "";
                String telefono = "";
                //String vehiculoModelo = data.get(position).getVehiculo().getMarca().getNombre();
                String vehiculoModelo = data.get(position).getVehiculo().getEconomico();
                String vehiculoPlaca = data.get(position).getVehiculo().getPlaca();
                String cedis = data.get(position).getOrigen();
                String supervisor = "";
                String fechaEntrada = "";
                //String fechaSalida = ""+convertDate(data.get(position).getFechaCreacion());
                String fechaSalida = ""+convertDate2(data.get(position).getFechaCreacion());
                String statusManifest= data.get(position).getEstatus().getNombre();
                String estado = "";
                String ciudad = "";
                String operador = data.get(position).getOperador().getNombre();
                String datoManiobras = "";
                String validacionApp = data.get(position).getValidacionApp();

                // Sendtrip Datos
                // Estado
                if (data.get(position).getTicketMasLejano().getSendtripPlus().getDestinatario() != null) {
                    if (data.get(position).getTicketMasLejano().getSendtripPlus().getDestinatario().getEstado() != null) {
                        if (data.get(position).getTicketMasLejano().getSendtripPlus().getDestinatario().getEstado().isEmpty()) {
                            // Ponemos un dato por Default
                            estado = "-- --";
                        } else {
                            // Ponemos el dato que tenga
                            estado = data.get(position).getTicketMasLejano().getSendtripPlus().getDestinatario().getEstado();
                        }
                    } else {
                        // Ponemos un dato por Default
                        estado = "-- --";
                    }
                } else {
                    // Ponemos un dato por Default
                    estado = "-- --";
                }

                // Ciudad
                if (data.get(position).getTicketMasLejano().getSendtripPlus().getDestinatario() != null) {
                    if (data.get(position).getTicketMasLejano().getSendtripPlus().getDestinatario().getMunicipio() != null) {
                        if (data.get(position).getTicketMasLejano().getSendtripPlus().getDestinatario().getMunicipio().isEmpty()) {
                            // Dato por default
                            ciudad = "-- --";
                        } else {
                            // Dato que contiene
                            ciudad = data.get(position).getTicketMasLejano().getSendtripPlus().getDestinatario().getMunicipio();
                        }
                    } else {
                        // Dato por default
                        ciudad = "-- --";
                    }
                } else {
                    // Dato por default
                    ciudad = "-- --";
                }

                // Auxiliares / Maniobras
                if(data.get(position).getManiobraCustodia() == null || data.get(position).getManiobraCustodia().isEmpty()) {
                    // Esta vacio el arreglo
                    datoManiobras = "-- --";
                } else {
                    if(data.get(position).getManiobraCustodia().get(0).getCantidad() != null) {
                        datoManiobras = String.valueOf(data.get(position).getManiobraCustodia().get(0).getCantidad());
                    } else {
                        // Esta vacio el arreglo
                        datoManiobras = "-- --";
                    }
                }

               // Toast.makeText(context, ""+data.get(position).getIdmanifest(), Toast.LENGTH_SHORT).show();
                if(!data.get(position).getEstatus().getNombre().equals("Cerrado")) {
                    mView.gotoTickets(position, folioDespacho, vehiculoModelo, vehiculoPlaca, cedis, statusManifest,fechaSalida, ciudad, estado, operador, datoManiobras, validacionApp);
                }else{
                    Toast.makeText(context, "Este manifiesto ya fue cerrado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public static String convertDate(String inputDate) {
        // Step 1: Parse the input date string
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS", Locale.ENGLISH);
        Date date = null;
        try {
            date = inputFormat.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        // Step 2: Format the date to the desired output format
        SimpleDateFormat outputFormat = new SimpleDateFormat("EEE | dd/MM/yy | HH:mm", new Locale("es", "ES"));
        String formattedDate = outputFormat.format(date);

        // Step 3: Capitalize the first letter of the formatted date string
        if (formattedDate != null && !formattedDate.isEmpty()) {
            formattedDate = formattedDate.substring(0, 1).toUpperCase() + formattedDate.substring(1);
        }
        return formattedDate;
    }

    public static String convertDate2(String inputDate) {
        // Step 1: Parse the input date string
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS", Locale.ENGLISH);
        Date date = null;
        try {
            date = inputFormat.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        // Step 2: Format the date to the desired output format without the year
        SimpleDateFormat outputFormat = new SimpleDateFormat("EEE | dd/MM | HH:mm", new Locale("es", "ES"));
        String formattedDate = outputFormat.format(date);

        // Step 3: Capitalize the first letter of the formatted date string
        if (formattedDate != null && !formattedDate.isEmpty()) {
            formattedDate = formattedDate.substring(0, 1).toUpperCase() + formattedDate.substring(1);
        }
        return formattedDate;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setFilterV2(List<dataManifestV2> filterList) {
        this.data = new ArrayList<>();
        this.data.addAll(filterList);
        notifyDataSetChanged();
    }
    public void updateManifest(List<dataManifestV2> updated) {
        this.data = new ArrayList<>();
        this.data.addAll(updated);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout cardOrder;
        TextView numberManifest, vehicleName, vehiclePlaca, vehicleManifiesto, vehicleCedis,validationTextInProgress,statusManifest,validationText,textView70, textViewCiudad, textViewEstado;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView70= itemView.findViewById(R.id. textView70);
            cardOrder = itemView.findViewById(R.id.constrainCard);
            vehicleName = itemView.findViewById(R.id.vehicle_name);
            vehiclePlaca = itemView.findViewById(R.id.textView17);
            vehicleManifiesto = itemView.findViewById(R.id.numberManifest);
            vehicleCedis = itemView.findViewById(R.id.textView39);
            validationTextInProgress = itemView.findViewById(R.id.validationTextInProgress);
            validationText = itemView.findViewById(R.id.validationText);
            statusManifest= itemView.findViewById(R.id.statusManifest);

            // Nuevos
            textViewCiudad = itemView.findViewById(R.id.textViewCiudad);
            textViewEstado = itemView.findViewById(R.id.textViewEstado);
        }
    }
}
