package com.fhl.sistemadedistribucionfh.nmanifest.presenterV2;

import com.fhl.sistemadedistribucionfh.nmanifest.modelV2.dataManifestV2;
import com.fhl.sistemadedistribucionfh.nmanifestDetail.modelV2.dataTicketsManifestV2;

import java.util.List;

public interface presentermanifestV2 {
    void getmanifestV2();
    void setmanifestV2(List<dataManifestV2> data);

    void returnTologin();
    void showProgress();
    void hideProgress();
    void getTicketByManigest(String idmanifest);

    void setTickets(List<dataTicketsManifestV2> data);
}
