package com.fhl.sistemadedistribucionfh.Cancelar.presenter;

import java.util.List;

public interface cancelPresenter {
    void sendEvidence(List<String> directories, String folioTicket);

    void okSendEvidence();

    void changemStatusManifestTicket(String currentManifest, String folioTicket);

    void okChangeStatus();

    void SetTicketNoEntregado(String currentManifest, String folioTicket, Integer idReason, String name);

    void nextRequest();
}
