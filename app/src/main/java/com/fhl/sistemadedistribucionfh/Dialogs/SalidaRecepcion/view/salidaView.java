package com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.view;

import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.cortina.dataCortina;
import com.fhl.sistemadedistribucionfh.Dialogs.SalidaRecepcion.model.responseManifestSalidaV2data;
import com.fhl.sistemadedistribucionfh.Salida.Model.v2.ResponseSalida;
import com.fhl.sistemadedistribucionfh.Salida.Model.v2.dataSalida;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;

import java.util.List;

import retrofit2.Response;

public interface salidaView {
    void hideProgress();

    void showProgress();

    void setManifestCard(List<responseManifestSalidaV2data> data);

    void setdataCortina(dataCortina data);

    void goticketsifNull();

    void setTickets(List<dataTicketsManifestV2> data);

    void setSellos(List<dataSalida> response);
}
