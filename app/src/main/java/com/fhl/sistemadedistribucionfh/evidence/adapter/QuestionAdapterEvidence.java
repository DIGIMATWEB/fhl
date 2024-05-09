package com.fhl.sistemadedistribucionfh.evidence.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.fhl.sistemadedistribucionfh.checkList.Questions.view.QuestionFragment2;
import com.fhl.sistemadedistribucionfh.checkList.Questions.view.questionFragment;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.Pregunta;
import com.fhl.sistemadedistribucionfh.evidence.checklist.zQuestionCheckllist.QuestionFragment2Evidence;
import com.fhl.sistemadedistribucionfh.evidence.checklist.zQuestionCheckllist.questionEvidence;

import java.util.List;

public class QuestionAdapterEvidence extends FragmentStateAdapter {
    private List<Pregunta> questions;
    private questionEvidence mview;
    private ViewPager2 viewPager;
    public QuestionAdapterEvidence(List<Pregunta> questionList, @NonNull FragmentActivity fragmentActivity, questionEvidence mview, ViewPager2 viewPager) {
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
                    //mview.fillViewPager(questionList);
                    //createFragment(position);
                    //mview.updated();
                } else {
                    mview.showbutton();
                }
            }
        });
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Aqui manda toda la data
        return QuestionFragment2Evidence.newInstance(questions.get(position),mview,position,questions );
    }
    @Override
    public int getItemCount() {
        return questions.size();
    }
}