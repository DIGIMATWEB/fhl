package com.fhl.sistemadedistribucionfh.nmanifest.viewV2;

import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.dataManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;

import java.util.List;

public interface viewManifestV2 {
    void setAllmanifestV2(List<dataManifestV2> data);

    void returnTologin();

    void hideProgress();

    void showProgress();

    void checkTickets(List<dataTicketsManifestV2> data);
}
