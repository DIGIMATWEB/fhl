package com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.presenter;

import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;

import java.util.List;

public interface presenterSello {
    void requestManifestdetail(String currentManifest);
    void reqSellos(String currentManifest);
    void setSello(Integer manifestId, List<Sello> sellos);
    void getMessageSello();


    void saveManifestId(Integer id);

    void setSellos(List<Sello> sellos);
    void showDialog();
    void hideDialog();

}
