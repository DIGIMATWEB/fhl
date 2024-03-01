package com.fhl.sistemadedistribucionfh.checkList.Questions.presenter;

import android.content.Context;

import com.fhl.sistemadedistribucionfh.checkList.Questions.interactor.interactorQuestionsImpl;
import com.fhl.sistemadedistribucionfh.checkList.Questions.view.questionsView;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.Pregunta;

import java.util.List;

public class presenterQuestionsImpl  implements  presenterQuestions{
    private questionsView view;
    private Context context;
    private interactorQuestionsImpl interactor;
    public presenterQuestionsImpl(questionsView view,Context context){
        this.view=view;
        this.context=context;
        interactor= new interactorQuestionsImpl(this,context);

    }
    @Override
    public void requestQuestions() {
        if(view!=null){
            interactor.getQeustions();
        }
    }

    @Override
    public void setQuestions( List<Pregunta>  mdata) {
        if(view!=null){
            view.setQuestiomns(mdata);
        }
    }
}
