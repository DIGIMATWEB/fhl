package com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.presenter;

public interface presenterSetValidacion {
    void setValidacionMenifest(String manifest);

    void setresponseValidacionMenifest(String s);

    void getdriverHabilities();
    void getVehicleHabilities(Integer claveVehicleID);

    void setDriverHailities(String habilidades);

    void setDriverVehicle(String habilidadVehiculos);

    void setDatosValidador(String manifest, String vehicleVin, String rfcUser, String jsonHabDriver, String jsonHabVehicles, String user);
}
