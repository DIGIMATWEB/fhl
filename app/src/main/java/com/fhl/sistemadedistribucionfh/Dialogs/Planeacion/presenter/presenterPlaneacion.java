package com.fhl.sistemadedistribucionfh.Dialogs.Planeacion.presenter;

public interface presenterPlaneacion {
    void getTicketData(String folio);
    void setTicketValue(String answer);

    void failResponse();

    void setStatusTicket(String folio, Integer i);
}
