package com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV1.view;

import com.fhl.sistemadedistribucionfh.Dialogs.validador.ValidadorV1.model.dataValidador;

import java.util.List;

public interface validadorView {
    void setCode(List<dataValidador> data);

    void errorValidador();
}
