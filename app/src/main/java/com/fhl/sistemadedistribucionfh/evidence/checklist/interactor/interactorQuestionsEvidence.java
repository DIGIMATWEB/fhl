package com.fhl.sistemadedistribucionfh.evidence.checklist.interactor;

public interface interactorQuestionsEvidence {
    void sendDataChecklist(String jsonRespuestas, String usuario, String folioTicket, Integer vehiculoId, Integer checklistId);
}
