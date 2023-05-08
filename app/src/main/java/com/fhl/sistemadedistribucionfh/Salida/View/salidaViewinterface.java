package com.fhl.sistemadedistribucionfh.Salida.View;

import com.fhl.sistemadedistribucionfh.Salida.Model.Ticket;

import java.util.List;

public interface salidaViewinterface {
    void setQR(String qr);

    void setTickets(List<Ticket> data);

    void setDireccion(String direccionEntrega);
}
