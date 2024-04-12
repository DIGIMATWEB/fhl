package com.fhl.sistemadedistribucionfh.Dialogs.completeSalida.view;

import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.dataTicketsDetailsendtrip;

import java.util.List;

public interface dialogCompletedSalidaImp {
    void closeDialog();
    void startSendtriplus();
    void nextRequest();
    void setDetailTicketsentriplus(List<dataTicketsDetailsendtrip> data);
}
