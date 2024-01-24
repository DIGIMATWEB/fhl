package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.presenter;

import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.dataManifestV2;

import java.util.List;

public interface salidaViewPresenter {
    void requestManifest(String codigoValidador);
    void requestCortinas();
    void requestTickets();

    void setmanifest(List<dataManifestV2> data);

    void hideProgress();

    void showProgress();
}
