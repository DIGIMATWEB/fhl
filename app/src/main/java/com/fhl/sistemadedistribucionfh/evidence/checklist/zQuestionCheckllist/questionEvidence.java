package com.fhl.sistemadedistribucionfh.evidence.checklist.zQuestionCheckllist;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.fhl.sistemadedistribucionfh.Retrofit.GeneralConstants;
import com.fhl.sistemadedistribucionfh.checkList.Questions.adapter.QuestionAdapter;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.sendChecklist.SendCheck;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.sendChecklist.sendChecklist;
import com.fhl.sistemadedistribucionfh.checkList.Questions.presenter.presenterQuestions;
import com.fhl.sistemadedistribucionfh.checkList.Questions.presenter.presenterQuestionsImpl;
import com.fhl.sistemadedistribucionfh.checkList.Questions.view.questionsView;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.Pregunta;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.VehiculoVsCheck;
import com.fhl.sistemadedistribucionfh.checkList.view.checkList;
import com.fhl.sistemadedistribucionfh.evidence.adapter.QuestionAdapterEvidence;

import java.util.ArrayList;
import java.util.List;

public class questionEvidence extends Fragment implements View.OnClickListener , questionsView {
    public static final String TAG = questionEvidence.class.getSimpleName();
    private Button buttonstartChecklist;
    private TextView helpertext;
    private ViewPager2 ViewPager;
    private List<Pregunta> mfdata;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private String nombre,placa,vigencia,periodicida;
    private TextView namechecklist,vehiclTypeChecklist,manifestChecklist,statusChecklist;
    private Boolean ischeklistsetupok=false;
    private presenterQuestions presenter;
    private sendChecklist sendChakclist;
    private List<SendCheck> checklist=null;

    private Integer cvetemp, positionRespuestas;
    private Integer positionTemp, vehiculoChkId, despachoId, vehiculoId, checklistId;
    private String fechaAplicado;
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
            positionRespuestas = args.getInt("position", 0); // Valor predeterminado 0 si no se encuentra

            // Esto es para enviar el checklist
            vehiculoChkId = args.getInt("VehiculoChkId" , 0);
            despachoId = args.getInt("DespachoId", 0);
        //    fechaAplicado = args.getString("FechaAplicado", "");
            vehiculoId = args.getInt("VehiculoId", 0);
            checklistId = args.getInt("ChecklistId", 0);
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
        SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstants.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String user = preferences.getString(GeneralConstants.OPERADOR_ID, null);
        buttonstartChecklist=view.findViewById(R.id.buttonstartChecklist);
        buttonstartChecklist.setOnClickListener(this);
        helpertext=view.findViewById(R.id.helpertext);
        ViewPager=view.findViewById(R.id.ViewPager);
        namechecklist=view.findViewById(R.id.namechecklist);
        vehiclTypeChecklist =view.findViewById(R.id.vehiclTypeChecklist);
        manifestChecklist=view.findViewById(R.id. manifestChecklist);
        statusChecklist  =view.findViewById(R.id. statusChecklist);
        ViewPager = view.findViewById(R.id.ViewPager);
        sendChakclist=new sendChecklist(checklist,user,2);
        presenter= new presenterQuestionsImpl(this,getContext());
        presenter.requestQuestions(positionRespuestas,checklistId);
    }

    @Override
    public void setQuestiomns(List<Pregunta> mdata) {
        this.mfdata=mdata;
        if(mfdata!=null){
            checklist=new ArrayList<>();
            checklist.clear();
            for(Pregunta p: mfdata){
                checklist.add(new SendCheck(p.getId(),0,"",""));
            }
        }

        fillViewPager(mfdata);
        ischeklistsetupok=true;
    }

    @Override
    public void changeStatusManifestTicket() {

    }

    @Override
    public void setData(List<VehiculoVsCheck> data) {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void closeCheckListError2() {

    }

    @Override
    public void successetCehcklist() {

    }

    public void fillViewPager(List<Pregunta> mdata) {
        QuestionAdapterEvidence questionAdapter = new QuestionAdapterEvidence(mdata,this.getActivity(),this,ViewPager); // Pass the list of questions to the adapter
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

    public void updated() {
        ViewPager.notifyAll();
    }

    private  void mangeF() {
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

    public void setAndswersF(int pos, Integer id, Integer type, String answerDesc, Integer answerId) {

    }
}