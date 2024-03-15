package com.fhl.sistemadedistribucionfh.checkList.Questions.view;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.checkList.Questions.adapter.QuestionAdapter;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.Datum;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.responseChecklist;
import com.fhl.sistemadedistribucionfh.checkList.Questions.presenter.presenterQuestions;
import com.fhl.sistemadedistribucionfh.checkList.Questions.presenter.presenterQuestionsImpl;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.Pregunta;
import com.fhl.sistemadedistribucionfh.checkList.view.checkList;
import com.google.gson.Gson;

import java.util.List;

public class questionFragment extends Fragment implements View.OnClickListener ,questionsView {
    public static final String TAG = questionFragment.class.getSimpleName();
    private Button buttonstartChecklist;
    private TextView helpertext;
    private ViewPager2 ViewPager;
    private  List<Pregunta> mdata;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private String nombre,placa,vigencia,periodicida;
    private TextView namechecklist,vehiclTypeChecklist,manifestChecklist,statusChecklist;
    private Boolean ischeklistsetupok=false;
    private presenterQuestions presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questions, container, false);
        Bundle args = getArguments();

        initView(view);
        if (args != null) {
            nombre = args.getString("nombre");
            placa = args.getString("placa");
            vigencia = args.getString("vigencia");
        periodicida = args.getString("periodicida");
            if (vigencia.equals("Vigente")) {
                int mcolor = getContext().getColor(R.color.green);
                statusChecklist.setTextColor(mcolor);
            }else{
                    int mcolor = getContext().getColor(R.color.red);
                statusChecklist.setTextColor(mcolor);
            }

            namechecklist.setText(nombre);
            vehiclTypeChecklist .setText(placa);
            manifestChecklist.setText(periodicida );
            statusChecklist .setText( vigencia );
        }

        return view;
    }


    private void initView(View view) {
        buttonstartChecklist=view.findViewById(R.id.buttonstartChecklist);
        buttonstartChecklist.setOnClickListener(this);
        helpertext=view.findViewById(R.id.helpertext);
        ViewPager=view.findViewById(R.id.ViewPager);
        namechecklist=view.findViewById(R.id.namechecklist);
        vehiclTypeChecklist =view.findViewById(R.id.vehiclTypeChecklist);
        manifestChecklist=view.findViewById(R.id. manifestChecklist);
        statusChecklist  =view.findViewById(R.id. statusChecklist);
        ViewPager = view.findViewById(R.id.ViewPager);
        presenter= new presenterQuestionsImpl(this,getContext());
        presenter.requestQuestions();
    }

    @Override
    public void setQuestiomns(List<Pregunta> mdata) {
        fillViewPager(mdata);
        ischeklistsetupok=true;
    }
    public void fillViewPager(List<Pregunta> mdata){
        QuestionAdapter questionAdapter = new QuestionAdapter(mdata,this.getActivity(),this,ViewPager); // Pass the list of questions to the adapter
        ViewPager.setAdapter(questionAdapter);
    }


    public void showbutton() {
        buttonstartChecklist.setVisibility(View.VISIBLE);
        buttonstartChecklist.setText("Finalizar");
    }

    public void hidebutton() {
        buttonstartChecklist.setVisibility(View.GONE);
        buttonstartChecklist.setText("Iniciar");
    }
    private  void mangeF()
    {
        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        checkList mcheckList= new checkList();
        transaction.replace(R.id.fragments, mcheckList, checkList.TAG).commit();
    }
    @Override
    //Pressed return button - returns to the results menu
    public void onResume() { //onback
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.e("drahEventes"," "+keyCode+" "+event);
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    mangeF();
                    return true;
                }
                return false;
            }
        });
    }
    public void dismisedDialog() {
       // Toast.makeText(getContext(), "dismissed 4", Toast.LENGTH_SHORT).show();
        mangeF();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonstartChecklist:
                if(ischeklistsetupok){
                    buttonstartChecklist.setVisibility(View.GONE);
                    helpertext.setVisibility(View.GONE);
                    ViewPager.setVisibility(View.VISIBLE);
                    if(buttonstartChecklist.getText().equals("Finalizar")){
                        Toast.makeText(getContext(), "Send checklist ir a manifiestos", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "el checklist no esta seteado", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
