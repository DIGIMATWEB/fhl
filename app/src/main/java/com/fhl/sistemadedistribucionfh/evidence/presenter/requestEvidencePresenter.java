package com.fhl.sistemadedistribucionfh.evidence.presenter;

import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.dataTicketsDetailsendtrip;

import java.util.List;

public interface requestEvidencePresenter {
    void sendEvidence(Integer secuenceRequest, String signatureBase64, String inputTextSignature, String currusel, String ffiles, Integer flujoId, String folioTicket);

    void nextRequest();

    void sendRate(Integer stars, String folioTicket);
    void showDialog();

    void hideDialog();

    void sendSentriplus(String currentManifest, List<dataTicketsDetailsendtrip> dataTicketSendtrip, String sentripPlusFlow);

    void tokenAvocado();

    void validateSendtrip();

    void requestDetailTicketsSendtriplus(boolean b, Integer iterateidTickets, String currentManifest, String folioTicket, String ticket);

    void setDetailTicketsentriplus(List<dataTicketsDetailsendtrip> data);

    void changeStatusManifestTicket(String currentManifest, String changeStatusTicket, String sentripPlusFlow);
}
