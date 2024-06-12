package com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.view;

import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.dataTicketsDetailsendtrip;

import java.util.List;

public interface viewSetValidacion {
    void statusValidacion(String code);

    void setDriverHailities(String habilidades);

    void setVehicleHailities(String habilidadVehiculos);

    void setDetailTickets(List<dataTicketsDetailsendtrip> data);

    void gomanifest();
}
