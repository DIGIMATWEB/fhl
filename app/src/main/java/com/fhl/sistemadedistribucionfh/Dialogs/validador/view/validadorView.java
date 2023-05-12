package com.fhl.sistemadedistribucionfh.Dialogs.validador.view;

import com.fhl.sistemadedistribucionfh.Dialogs.validador.model.dataValidador;

import java.util.List;

public interface validadorView {
    void setCode(List<dataValidador> data);

    void errorValidador();
}
