package com.fhl.sistemadedistribucionfh.Salida.Presenter;

import com.fhl.sistemadedistribucionfh.Salida.Model.test.Ticket;
import com.fhl.sistemadedistribucionfh.Salida.Model.v2.ResponseSalida;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;

import java.util.List;

import retrofit2.Response;

public interface presenterSalida {
    void requestSalida(String code);

    void setDataTickets(List<Ticket> data);

    void setQRImage(String qr);

    void setDireccion(String direccionEntrega);

    void setSalida(Response<ResponseSalida> response);

    void getTickets();

    void setTickets(List<dataTicketsManifestV2> data);
}
