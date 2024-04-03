package com.fhl.sistemadedistribucionfh.evidence.presenter;

public interface requestEvidencePresenter {
    void sendEvidence(Integer secuenceRequest, String signatureBase64, String inputTextSignature, String currusel, String ffiles, Integer flujoId, String folioTicket);

    void nextRequest();

    void sendRate(Integer stars, String folioTicket);
    void showDialog();

    void hideDialog();

    void sendSentriplus();

    void tokenAvocado();

    void validateSendtrip();
}
