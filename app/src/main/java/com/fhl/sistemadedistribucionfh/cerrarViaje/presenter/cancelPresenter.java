package com.fhl.sistemadedistribucionfh.cerrarViaje.presenter;

import java.util.List;

public interface cancelPresenter {
    void sendEvidence(List<String> directories, String folioTicket);

    void okSendEvidence();
}
