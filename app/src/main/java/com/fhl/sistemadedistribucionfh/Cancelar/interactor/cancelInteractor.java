package com.fhl.sistemadedistribucionfh.Cancelar.interactor;

import java.util.List;

public interface cancelInteractor {
    void sendEvidences(List<String> directories, String folioTicket);
    void changemStatusManifestTicket(String currentManifest, String folioTicket);
}
