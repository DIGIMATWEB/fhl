package com.fhl.sistemadedistribucionfh.evidence.checklist.view;

import com.fhl.sistemadedistribucionfh.Tickets.model.ticketsdetail.Check;

import java.util.ArrayList;
import java.util.List;

public class ChecklistManager {
    private static List<Check> checkList = new ArrayList<>();
    private static List<ChecklistObserver> observers = new ArrayList<>();

    public static void addObserver(ChecklistObserver observer) {
        observers.add(observer);
    }

    public static void removeObserver(ChecklistObserver observer) {
        observers.remove(observer);
    }

    public static void setChecklist(List<Check> newChecklist) {
        checkList = newChecklist;
        notifyObservers();
    }

    public static List<Check> getChecklist() {
        return checkList;
    }

    private static void notifyObservers() {
        for (ChecklistObserver observer : observers) {
            observer.onChecklistChanged(checkList);
        }
    }
}
