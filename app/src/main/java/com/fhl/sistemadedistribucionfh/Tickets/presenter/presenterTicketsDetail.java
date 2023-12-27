package com.fhl.sistemadedistribucionfh.Tickets.presenter;

import com.fhl.sistemadedistribucionfh.Tickets.model.ticketsdetail.dataDetailTickets;

import java.util.List;

public interface presenterTicketsDetail {
    void requestDetailTickets();

    void setTikets(List<dataDetailTickets> data);
}
