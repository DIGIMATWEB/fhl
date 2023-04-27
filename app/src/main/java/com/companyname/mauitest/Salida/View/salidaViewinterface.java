package com.companyname.mauitest.Salida.View;

import com.companyname.mauitest.Salida.Model.Ticket;

import java.util.List;

public interface salidaViewinterface {
    void setQR(String qr);

    void setTickets(List<Ticket> data);

    void setDireccion(String direccionEntrega);
}
