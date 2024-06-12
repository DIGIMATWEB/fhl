package com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.presenter;

import com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV2.model.dataValidadorV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;

import java.util.List;

public interface presenterValidadorDetail {
    void requestManifestDetail(int idEmpleado, String currentManifest);

    void setManifestVehicleandDriver(List<dataValidadorV2> data);

    void error();

    void getTicketByManifest(String currentManifest);

    void setDetailTickets(List<dataTicketsManifestV2> data);
}
