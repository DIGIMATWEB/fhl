package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.view;

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.responseManifestSalidaV2data;
import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.dataManifestV2;

import java.util.List;

public interface salidaView {
    void hideProgress();

    void showProgress();

    void setManifestCard(List<responseManifestSalidaV2data> data);
}
