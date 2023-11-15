package com.fhl.sistemadedistribucionfh.Salida.View;

import com.fhl.sistemadedistribucionfh.Salida.Model.test.Ticket;
import com.fhl.sistemadedistribucionfh.Salida.Model.v2.ResponseSalida;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;

import java.util.List;

import retrofit2.Response;

public interface salidaViewinterface {
    void setQR(String qr);

    void setTickets(List<Ticket> data);

    void setDireccion(String direccionEntrega);

    void setSalida(Response<ResponseSalida> response);

    void setTicketsList(List<dataTicketsManifestV2> data);
}
