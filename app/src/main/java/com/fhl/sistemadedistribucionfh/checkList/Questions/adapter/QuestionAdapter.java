package com.fhl.sistemadedistribucionfh.checkList.Questions.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.fhl.sistemadedistribucionfh.checkList.Questions.model.Question;
import com.fhl.sistemadedistribucionfh.checkList.Questions.view.QuestionFragment2;

import java.util.List;

public class QuestionAdapter extends FragmentStateAdapter {
    private List<Question> questions;

    public QuestionAdapter(List<Question> questionList, @NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.questions = questionList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Create a new fragment for each question
        return QuestionFragment2.newInstance(questions.get(position));
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
}