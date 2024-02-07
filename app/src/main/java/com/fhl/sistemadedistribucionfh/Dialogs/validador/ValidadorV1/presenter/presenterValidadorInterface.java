package com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV1.presenter;

import com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV1.model.dataValidador;

import java.util.List;

public interface presenterValidadorInterface {
    void requestDespacho(String codigo);
    void setDespachotoView(List<dataValidador> data);

    void errorValidador();
}
