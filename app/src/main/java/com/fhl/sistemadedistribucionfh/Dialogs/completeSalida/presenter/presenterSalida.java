package com.fhl.sistemadedistribucionfh.Dialogs.completeSalida.presenter;

import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.dataTicketsDetailsendtrip;

import java.util.List;

public interface presenterSalida {
    void requestDetailTicketsSendtriplus(boolean b, Integer iterateidTickets, String currentManifest, String folioTicket, String ticket);
    void sendSentriplus(String currentManifest, List<dataTicketsDetailsendtrip> dataTicketSendtrip, String sentripPlusFlow);
    void changeStatusManifestTicket(String currentManifest, String changeStatusTicket, String sentripPlusFlow);
    void requestTokenAvocado();
    void nextRequest();

    void validateSendtrip();


    void setDetailTicketsentriplus(List<dataTicketsDetailsendtrip> data);

    void failDetailTicket();
}
