package com.fhl.sistemadedistribucionfh.Tickets.view;

import com.fhl.sistemadedistribucionfh.Tickets.model.ticketsdetail.dataDetailTickets;

import java.util.List;

public interface ticketsView {
    void getTicketsDetail();

    void setTiketsDetail(List<dataDetailTickets> data);
}
