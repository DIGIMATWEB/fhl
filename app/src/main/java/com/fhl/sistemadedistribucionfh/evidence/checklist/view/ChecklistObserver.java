package com.fhl.sistemadedistribucionfh.evidence.checklist.view;

import com.fhl.sistemadedistribucionfh.Tickets.model.ticketsdetail.Check;

import java.util.List;

public interface ChecklistObserver {
    void onChecklistChanged(List<Check> updatedChecklist);
}
