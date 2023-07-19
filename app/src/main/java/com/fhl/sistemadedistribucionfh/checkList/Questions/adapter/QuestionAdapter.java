package com.fhl.sistemadedistribucionfh.checkList.Questions.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.fhl.sistemadedistribucionfh.checkList.Questions.model.Question;
import com.fhl.sistemadedistribucionfh.checkList.Questions.view.QuestionFragment2;
import com.fhl.sistemadedistribucionfh.checkList.Questions.view.questionFragment;

import java.util.List;

public class QuestionAdapter extends FragmentStateAdapter {
    private List<Question> questions;
    private questionFragment mview;
    public QuestionAdapter(List<Question> questionList, @NonNull FragmentActivity fragmentActivity, questionFragment mview) {
        super(fragmentActivity);
        this.mview=mview;
        this.questions = questionList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Create a new fragment for each question

        return QuestionFragment2.newInstance(questions.get(position),mview,position,questions );
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
}