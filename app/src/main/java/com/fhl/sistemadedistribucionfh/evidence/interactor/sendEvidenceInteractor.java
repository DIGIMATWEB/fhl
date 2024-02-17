package com.fhl.sistemadedistribucionfh.evidence.interactor;

public interface sendEvidenceInteractor {
    void requestEvidence(Integer secuenceRequest, String signatureBase64, String inputTextSignature, String currusel, String ffiles);
}
