package com.fhl.sistemadedistribucionfh.nmanifestDetail.presenter;

import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;

import java.util.List;

public interface presenterTicketsmanifestV2 {
    void setTickets(List<dataTicketsManifestV2> data);
    void getTickets(String folioDespachoId);

    void getSellos(String folioDespachoId);

    void setSellos(List<Sello> response);

    void showDialog();

    void hideDialog();
}
