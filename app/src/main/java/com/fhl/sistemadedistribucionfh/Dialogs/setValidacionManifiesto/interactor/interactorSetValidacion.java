package com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.interactor;

public interface interactorSetValidacion {
    void setValidacionMenifest(String manifest);
    void getDrirver();
    void getVehicle(Integer claveVehicleID);

    void setDatosValidador(String manifest, String vehicleVin, String rfcUser, String jsonHabDriver, String jsonHabVehicles, String user);

    void requestTicketsByManifest(String manifest);
}
