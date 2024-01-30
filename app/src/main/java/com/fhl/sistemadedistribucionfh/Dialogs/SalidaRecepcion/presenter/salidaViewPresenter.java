package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.presenter;

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.cortina.dataCortina;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.responseManifestSalidaV2data;

import java.util.List;

public interface salidaViewPresenter {
    void requestManifest(String codigoValidador);
    void requestCortinas(String codigoValidador);
    void requestTickets();

    void setmanifest(List<responseManifestSalidaV2data> data);

    void hideProgress();

    void showProgress();

    void setCortina(dataCortina data);

    void goTickets();
}
