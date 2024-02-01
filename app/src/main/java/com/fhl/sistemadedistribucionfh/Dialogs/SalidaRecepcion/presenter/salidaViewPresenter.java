package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.presenter;

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.cortina.dataCortina;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.responseManifestSalidaV2data;
import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;

import java.util.List;

public interface salidaViewPresenter {
    void requestManifest(String codigoValidador);
    void requestCortinas(String codigoValidador);
    void requestTickets(String currentManifest);
    void getsellos(String currentManifest);
    void setmanifest(List<responseManifestSalidaV2data> data);

    void hideProgress();

    void showProgress();

    void setCortina(dataCortina data);

    void goTickets();

    void setTickets(List<dataTicketsManifestV2> data);


    void setSellos(List<Sello> response);
}
