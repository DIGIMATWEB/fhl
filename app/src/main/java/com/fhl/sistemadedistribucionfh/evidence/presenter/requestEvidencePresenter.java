package com.fhl.sistemadedistribucionfh.evidence.presenter;

public interface requestEvidencePresenter {
    void sendEvidence(Integer secuenceRequest, String signatureBase64, String inputTextSignature, String currusel, String ffiles);

    void nextRequest();

    void sendRate(Integer stars);
}
