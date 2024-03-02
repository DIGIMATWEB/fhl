package com.fhl.sistemadedistribucionfh.checkList.Questions.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.fhl.sistemadedistribucionfh.checkList.Questions.model.Question;
import com.fhl.sistemadedistribucionfh.checkList.Questions.view.QuestionFragment2;
import com.fhl.sistemadedistribucionfh.checkList.Questions.view.questionFragment;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.Pregunta;

import java.util.List;

public class QuestionAdapter extends FragmentStateAdapter {
    private List<Pregunta> questions;
    private questionFragment mview;
    private ViewPager2 viewPager;
    public QuestionAdapter(List<Pregunta> questionList, @NonNull FragmentActivity fragmentActivity, questionFragment mview, ViewPager2 viewPager) {
        super(fragmentActivity);
        this.mview=mview;
        this.questions = questionList;
        this.viewPager = viewPager;
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position != (questionList.size()-1)) {
                    mview.hidebutton();
                } else {
                    mview.showbutton();
                }
            }
        });
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return QuestionFragment2.newInstance(questions.get(position),mview,position,questions );
    }
    @Override
    public int getItemCount() {
        return questions.size();
    }
}