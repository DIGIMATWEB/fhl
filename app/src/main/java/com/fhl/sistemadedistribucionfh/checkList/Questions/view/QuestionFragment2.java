package com.fhl.sistemadedistribucionfh.checkList.Questions.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.Answer;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.Question;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.Pregunta;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.Respuesta;

import java.util.ArrayList;
import java.util.List;

public class QuestionFragment2 extends Fragment {
    private Pregunta question;
    private int pos;
    private  questionFragment mview;
    private ConstraintLayout switchanswer,optionanswer,openanswer;
    private List<Pregunta> mquestions;
    private TextView textopenquestion,textopenquestion1,textbooleanonly,textPosition1,textPosition2,textPosition3;
    private ImageButton buttonTrue,buttonFalse;

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
        textopenquestion =view.findViewById(R.id.textopenquestion);
        textopenquestion1=view.findViewById(R.id.textopenquestion1);
        textbooleanonly=view.findViewById(R.id.textbooleanonly);
        textPosition1=view.findViewById(R.id.textPosition);
        textPosition2=view.findViewById(R.id. textPosition2);
        textPosition3=view.findViewById(R.id.textPosition3);

        buttonTrue= view.findViewById(R.id.trueButton);
        buttonFalse = view.findViewById(R.id.falseButton);

        // Set textPosition after initialization


        if(question.getTipoCampo()==1){//todo TIPO SWITCH
            textPosition1.setText((pos + 1) + "/" + mquestions.size());
            textPosition1=view.findViewById(R.id.textPosition);
            switchanswer.setVisibility(View.VISIBLE);
            openanswer.setVisibility(View.GONE);
            optionanswer.setVisibility(View.GONE);
            sendAnswer(3,"true",null);
        } else if(question.getTipoCampo()==2){ //todo TIPO OPEN
            textPosition2.setText((pos + 1) + "/" + mquestions.size());
            openanswer.setVisibility(View.VISIBLE);
            switchanswer.setVisibility(View.GONE);
            optionanswer.setVisibility(View.GONE);
            sendAnswer(3,"text",null);
        } else if(question.getTipoCampo()==3){//todo TIPOS MULTIPLE
            textPosition3.setText((pos + 1) + "/" + mquestions.size());
            openanswer.setVisibility(View.GONE);
            switchanswer.setVisibility(View.GONE);
            optionanswer.setVisibility(View.VISIBLE);

            Spinner spinner = view.findViewById(R.id.spinnerquestionary);

            List<String> items = new ArrayList<>(); // Replace with your desired data items
            items.clear();
            for(Respuesta r:mquestions.get(pos).getRespuestas()){
              items.add(r.getNombre());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, items);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setPopupBackgroundResource(android.R.color.transparent); // Make dropdown background transparent
            spinner.setDropDownVerticalOffset(spinner.getHeight()); // Set vertical offset to position the dialog
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Integer idAnswer=0;
                    for(Respuesta r:mquestions.get(pos).getRespuestas()){
                        if(r.getNombre()==parent.getSelectedItem()){
                          idAnswer=  r.getPreguntaId();
                        }
                    }
                    sendAnswer(3,parent.getSelectedItem().toString(),idAnswer);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }

        if (textopenquestion != null) {
            textopenquestion.setText(""+mquestions.get(pos).getNombre());
            textopenquestion1.setText(""+mquestions.get(pos).getNombre());
            textbooleanonly.setText(""+mquestions.get(pos).getNombre());
        }
    }

    private void sendAnswer(Integer type, String AnswerDesc,Integer AnswerId) {
        mview.setAndswersF(pos,mquestions.get(pos).getId(),type,AnswerDesc,AnswerId);
    }

    public QuestionFragment2(Pregunta question, questionFragment mview, int position, List<Pregunta> questions) {
        this.question = question;
        this.mview=mview;
        this.pos=position;
        this.mquestions=questions;
    }

    public static QuestionFragment2 newInstance(Pregunta question, questionFragment mview, int position, List<Pregunta> questions) {
        return new QuestionFragment2(question,mview,position,questions);
    }
}