package com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.interactor;

import com.fhl.sistemadedistribucionfh.evidence.model.SendTriplus.dataTicketsDetailsendtrip;

import java.util.List;

public interface interactorSetValidacion {
    void setValidacionMenifest(String manifest);
    void getDrirver();
    void getVehicle(Integer claveVehicleID);

    void setDatosValidador(String manifest, String vehicleVin, String rfcUser, String jsonHabDriver, String jsonHabVehicles, String user);

    void requestTicketsByManifest(String manifest);

    void sendSentriplus(String currentManifest, List<dataTicketsDetailsendtrip> dataTicketSendtrip, String sentripPlusFlow,Integer iteration);

    void tokenAvocado();
}
