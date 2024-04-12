package com.fhl.sistemadedistribucionfh.Dialogs.completeSalida.interactor;

import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.dataTicketsDetailsendtrip;

import java.util.List;

public interface interactorSalida {
    void tokenAvocado();
    void sendSentriplus(String currentManifest, List<dataTicketsDetailsendtrip> dataTicketSendtrip, String sentripPlusFlow);
    void changeStatusManifestTicket(String currentManifest, String changeStatusTicket, String sentripPlusFlow);

    void requestDetailTicketsSendtriplus(boolean isArray, Integer iterateidTickets, String currentManifest, String folioTicket, String ticket);
}
