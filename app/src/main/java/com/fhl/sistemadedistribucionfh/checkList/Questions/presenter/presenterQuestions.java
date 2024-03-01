package com.fhl.sistemadedistribucionfh.checkList.Questions.presenter;

import com.fhl.sistemadedistribucionfh.checkList.Questions.model.questions.dataQuestions;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.Pregunta;

import java.util.List;

public interface presenterQuestions {
    void requestQuestions();

    void setQuestions( List<Pregunta> mdata);
}
