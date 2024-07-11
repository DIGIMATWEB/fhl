package com.fhl.sistemadedistribucionfh.Dialogs.setValidacionManifiesto.model.habilitiesManifest;

import com.google.gson.annotations.SerializedName;

public class dataHabilitiesManifest {
    @SerializedName("ValidacionApp")

    private ValidacionApp validacionApp;

    public dataHabilitiesManifest(ValidacionApp validacionApp) {
        super();
        this.validacionApp = validacionApp;
    }

    public ValidacionApp getValidacionApp() {
        return validacionApp;
    }

    public void setValidacionApp(ValidacionApp validacionApp) {
        this.validacionApp = validacionApp;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 022d7369abaace0e42f9d2af41b8704a78674c15
