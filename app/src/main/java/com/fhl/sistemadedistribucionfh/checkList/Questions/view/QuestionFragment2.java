package com.fhl.sistemadedistribucionfh.checkList.Questions.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.Question;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.Pregunta;

import java.util.List;

public class QuestionFragment2 extends Fragment {
    private Pregunta question;
    private int pos;
    private  questionFragment mview;
    private ConstraintLayout switchanswer,optionanswer,openanswer;
    private List<Pregunta> questions;
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

        if(question.getTipoCampo()==1){
            openanswer.setVisibility(View.VISIBLE);
            switchanswer.setVisibility(View.GONE);
            optionanswer.setVisibility(View.GONE);
        }else if(question.getTipoCampo()==2){
            openanswer.setVisibility(View.GONE);
            switchanswer.setVisibility(View.VISIBLE);
            optionanswer.setVisibility(View.GONE);

        }else if(question.getTipoCampo()==3){
            openanswer.setVisibility(View.GONE);
            switchanswer.setVisibility(View.GONE);
            optionanswer.setVisibility(View.VISIBLE);
            Spinner spinner = view.findViewById(R.id.spinnerquestionary);
            String[] items = {"Item 1", "Item 2", "Item 3"}; // Replace with your desired data items
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, items);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setPopupBackgroundResource(android.R.color.transparent); // Make dropdown background transparent
            spinner.setDropDownVerticalOffset(spinner.getHeight()); // Set vertical offset to position the dialog

//            spinner.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    spinner.performClick(); // Show the Spinner dialog when clicked
//                }
//            });

// Apply the custom style to the Spinner dialog
//            TypedArray a = getContext().obtainStyledAttributes(R.style.SpinnerDialogStyle, new int[]{android.R.attr.dialogTheme});
//            int dialogTheme = a.getResourceId(0, 0);
//            a.recycle();
//            spinner.setPopupTheme(dialogTheme);

        }
        if(pos== questions.size()-1){
            mview.showbutton();
        }
        else {
            mview.hidebutton();
        }
    }

    public QuestionFragment2(Pregunta question, questionFragment mview, int position, List<Pregunta> questions) {
        this.question = question;
        this.mview=mview;
        this.pos=position;
        this.questions=questions;

    }

    // Implement the fragment's view creation and UI logic
    // ...

    public static QuestionFragment2 newInstance(Pregunta question, questionFragment mview, int position, List<Pregunta> questions) {

        return new QuestionFragment2(question,mview,position,questions);
    }
}