package com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.interactor;

import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;

import java.util.List;

public interface sellosInteractor {
    void setSellos(Integer manifestId, List<Sello> sellos);

    void requestManifestdetail(String currentManifest);
    void reqSellos(String currentManifest);
}
