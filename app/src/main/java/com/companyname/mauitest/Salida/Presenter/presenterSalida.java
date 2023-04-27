package com.companyname.mauitest.Salida.Presenter;

import com.companyname.mauitest.Salida.Model.Ticket;

import java.util.List;

public interface presenterSalida {
    void requestSalida(String code);

    void setDataTickets(List<Ticket> data);

    void setQRImage(String qr);

    void setDireccion(String direccionEntrega);
}
