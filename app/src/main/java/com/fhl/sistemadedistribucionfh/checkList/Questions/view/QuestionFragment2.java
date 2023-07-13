package com.fhl.sistemadedistribucionfh.checkList.Questions.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.Datum;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.Question;

public class QuestionFragment2 extends Fragment {
    private Question question;

    private ConstraintLayout switchanswer,optionanswer,openanswer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_question, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        switchanswer=view.findViewById(R.id.switchanswer);
        optionanswer=view.findViewById(R.id.optionanswer);
        openanswer=view.findViewById(R.id.openanswer);
    }

    public QuestionFragment2(Question question) {
        this.question = question;
        if(question.getAnswers().get(0).getObjectType()==1){
            openanswer.setVisibility(View.VISIBLE);
            switchanswer.setVisibility(View.GONE);
            optionanswer.setVisibility(View.GONE);
        }else if(question.getAnswers().get(0).getObjectType()==2){
            openanswer.setVisibility(View.GONE);
            switchanswer.setVisibility(View.VISIBLE);
            optionanswer.setVisibility(View.GONE);

        }else if(question.getAnswers().get(0).getObjectType()==3){
            openanswer.setVisibility(View.GONE);
            switchanswer.setVisibility(View.GONE);
            optionanswer.setVisibility(View.VISIBLE);

        }
    }

    // Implement the fragment's view creation and UI logic
    // ...

    public static QuestionFragment2 newInstance(Question question) {
        return new QuestionFragment2(question);
    }
}