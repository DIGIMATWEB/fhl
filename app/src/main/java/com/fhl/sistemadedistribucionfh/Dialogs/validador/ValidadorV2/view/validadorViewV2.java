package com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.view;

import com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.model.dataValidadorV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;

import java.util.List;

public interface validadorViewV2 {
    void setManifestVehicleandDriver(List<dataValidadorV2> data);

    void errorCode();

    void setDetailTickets(List<dataTicketsManifestV2> data);
}
