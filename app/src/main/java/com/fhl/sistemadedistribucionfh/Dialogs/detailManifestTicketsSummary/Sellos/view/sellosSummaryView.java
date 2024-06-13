package com.fhl.sistemadedistribucionfh.Dialogs.detailManifestTicketsSummary.Sellos.view;

import com.fhl.sistemadedistribucionfh.Sellos.model.Sello;

import java.util.List;

public interface sellosSummaryView {
    void setMessageSello();

    void saveManifestId(Integer id);

    void seteginsellos(List<Sello> sellos);
}
