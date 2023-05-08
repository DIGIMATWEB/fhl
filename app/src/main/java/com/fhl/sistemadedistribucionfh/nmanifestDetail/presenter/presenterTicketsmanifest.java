package com.fhl.sistemadedistribucionfh.nmanifestDetail.presenter;

import com.fhl.sistemadedistribucionfh.nmanifestDetail.model.dataTicketsManifest;

import java.util.List;

public interface presenterTicketsmanifest {
    void setTickets(List<dataTicketsManifest> data);
    void getTickets(String idmanifest);
}
