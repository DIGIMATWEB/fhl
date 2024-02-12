package com.fhl.sistemadedistribucionfh.nmanifestDetail.view;

import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;

import java.util.List;

public interface viewManifestDetailsV2 {
    void setTickets(List<dataTicketsManifestV2> data);

    void setSellos(List<Sello> response);
}
