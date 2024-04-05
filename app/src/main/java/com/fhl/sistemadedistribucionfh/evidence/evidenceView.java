package com.fhl.sistemadedistribucionfh.evidence;

import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.dataTicketsDetailsendtrip;

import java.util.List;

public interface evidenceView {
    void setMessage();
    void showDialog();

    void hideDialog();

    void validateSendtrip();

    void setDetailTicketsentriplus(List<dataTicketsDetailsendtrip> data);
}
