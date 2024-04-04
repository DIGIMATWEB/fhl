package com.fhl.sistemadedistribucionfh.evidence.interactor;

import com.fhl.sistemadedistribucionfh.evidence.model.dataTicketsDetailsendtrip;

import java.util.List;

public interface sendEvidenceInteractor {
    void requestEvidence(Integer secuenceRequest, String signatureBase64, String inputTextSignature, String currusel, String ffiles, Integer flujoId, String folioTicket);

    void sendRate(Integer stars, String folioTicket);

    void sendSentriplus(List<dataTicketsDetailsendtrip> dataTicketSendtrip);

    void tokenAvocado();

    void requestDetailTicketsSendtriplus(boolean isArray, Integer iterateidTickets, String currentManifest, String folioTicket, String ticket);
}
