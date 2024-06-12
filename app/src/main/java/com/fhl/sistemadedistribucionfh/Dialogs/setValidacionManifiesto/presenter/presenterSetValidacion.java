package com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.presenter;

import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.dataTicketsDetailsendtrip;

import java.util.List;

public interface presenterSetValidacion {
    void setValidacionMenifest(String manifest);

    void setresponseValidacionMenifest(String s);

    void getdriverHabilities();
    void getVehicleHabilities(Integer claveVehicleID);

    void setDriverHailities(String habilidades);

    void setDriverVehicle(String habilidadVehiculos);

    void setDatosValidador(String manifest, String vehicleVin, String rfcUser, String jsonHabDriver, String jsonHabVehicles, String user);

    void requestTicketsByManifest(String manifest);

    void setDetailTickets(List<dataTicketsDetailsendtrip> data);
}
