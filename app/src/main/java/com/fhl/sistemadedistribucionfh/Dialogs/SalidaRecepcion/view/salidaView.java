package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.view;

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.cortina.dataCortina;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.responseManifestSalidaV2data;

import java.util.List;

public interface salidaView {
    void hideProgress();

    void showProgress();

    void setManifestCard(List<responseManifestSalidaV2data> data);

    void setdataCortina(dataCortina data);

    void goticketsifNull();
}
