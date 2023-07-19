package com.fhl.sistemadedistribucionfh.checkList.Questions.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.fhl.sistemadedistribucionfh.Dialogs.dialogCompletedespacho;
import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.checkList.Questions.adapter.QuestionAdapter;
import com.fhl.sistemadedistribucionfh.checkList.Questions.dialog.dialogChecklistWarning;
import com.fhl.sistemadedistribucionfh.checkList.Questions.dialog.dialogchecklistok;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.Datum;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.responseChecklist;
import com.fhl.sistemadedistribucionfh.checkList.view.checkList;
import com.google.gson.Gson;

import java.util.List;

public class questionFragment extends Fragment implements View.OnClickListener  {
    public static final String TAG = questionFragment.class.getSimpleName();
    private Button buttonstartChecklist;
    private TextView helpertext;
    private ViewPager2 ViewPager;
    private  List<Datum> mdata;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questions, container, false);
        initView(view);
        gsonData();
        initViewPager(view);
        return view;
    }

    private void initViewPager(View view) {
        ViewPager = view.findViewById(R.id.ViewPager);
        QuestionAdapter questionAdapter = new QuestionAdapter(mdata.get(0).getQuestions(),this.getActivity(),this); // Pass the list of questions to the adapter
        ViewPager.setAdapter(questionAdapter);
    }

    private void initView(View view) {
        buttonstartChecklist=view.findViewById(R.id.buttonstartChecklist);
        buttonstartChecklist.setOnClickListener(this);
        helpertext=view.findViewById(R.id.helpertext);
        ViewPager=view.findViewById(R.id.ViewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonstartChecklist:
                if(buttonstartChecklist.getText().equals("Iniciar")) {
                    if (buttonstartChecklist.getVisibility() == View.VISIBLE) {
                        buttonstartChecklist.setVisibility(View.GONE);
                        helpertext.setVisibility(View.GONE);
                        ViewPager.setVisibility(View.VISIBLE);
                    }
                }else {

                    questionFragment fragment = new questionFragment();
                    dialogchecklistok dg = new dialogchecklistok();
                    dg.show(getActivity().getSupportFragmentManager(),"dialogchecklistok");


                }
                break;
        }
    }
    public void gsonData(){
        //region json
        String jsonString = "{\n" +
                "  \"responseCode\": 105,\n" +
                "  \"message\": \"Operation success\",\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"origin_adm\": 688,\n" +
                "      \"cve_trip_mgm_section\": 82,\n" +
                "      \"desc_trip_mgm_section\": \"Llantas\",\n" +
                "      \"questions\": [\n" +
                "        {\n" +
                "          \"origin_adm\": 688,\n" +
                "          \"cve_trip_mgm_section\": 82,\n" +
                "          \"cve_trip_mgm_question\": 103,\n" +
                "          \"desc_trip_mgm_question\": \"¿Cual es e tiempo trascurrido desde la instalacion de las ultimas llantas?\",\n" +
                "          \"required_evidence\": false,\n" +
                "          \"instructions\": \"El tiempo debe ser desde la primera instalación en el vehículo\",\n" +
                "          \"answers\": [\n" +
                "            {\n" +
                "              \"origin_adm\": 688,\n" +
                "              \"cve_trip_mgm_section\": 82,\n" +
                "              \"cve_trip_mgm_question\": 103,\n" +
                "              \"cve_trip_mgm_answer\": 211,\n" +
                "              \"desc_trip_mgm_answer\": \"Pregunta abierta\",\n" +
                "              \"trip_mgm_answer_value\": 10,\n" +
                "              \"object_type\": 1\n" +
                "            }\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"origin_adm\": 688,\n" +
                "          \"cve_trip_mgm_section\": 82,\n" +
                "          \"cve_trip_mgm_question\": 104,\n" +
                "          \"desc_trip_mgm_question\": \"¿Cuenta con llantas de repuesto ademas de las dadas en el inicio del viaje?\",\n" +
                "          \"required_evidence\": true,\n" +
                "          \"instructions\": \"La llanta debe ser nueva y sin rodada\",\n" +
                "          \"answers\": [\n" +
                "            {\n" +
                "              \"origin_adm\": 688,\n" +
                "              \"cve_trip_mgm_section\": 82,\n" +
                "              \"cve_trip_mgm_question\": 104,\n" +
                "              \"cve_trip_mgm_answer\": 212,\n" +
                "              \"desc_trip_mgm_answer\": \"Si\",\n" +
                "              \"trip_mgm_answer_value\": 50,\n" +
                "              \"object_type\": 2\n" +
                "            },\n" +
                "            {\n" +
                "              \"origin_adm\": 688,\n" +
                "              \"cve_trip_mgm_section\": 82,\n" +
                "              \"cve_trip_mgm_question\": 104,\n" +
                "              \"cve_trip_mgm_answer\": 213,\n" +
                "              \"desc_trip_mgm_answer\": \"No\",\n" +
                "              \"trip_mgm_answer_value\": 50,\n" +
                "              \"object_type\": 2\n" +
                "            }\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"origin_adm\": 688,\n" +
                "          \"cve_trip_mgm_section\": 82,\n" +
                "          \"cve_trip_mgm_question\": 105,\n" +
                "          \"desc_trip_mgm_question\": \"Nivel de aire en las llantas\",\n" +
                "          \"required_evidence\": false,\n" +
                "          \"instructions\": \"No debe ser bajo o estar en su nivel máximo para evitar que explote\",\n" +
                "          \"answers\": [\n" +
                "            {\n" +
                "              \"origin_adm\": 688,\n" +
                "              \"cve_trip_mgm_section\": 82,\n" +
                "              \"cve_trip_mgm_question\": 105,\n" +
                "              \"cve_trip_mgm_answer\": 214,\n" +
                "              \"desc_trip_mgm_answer\": \"Baja\",\n" +
                "              \"trip_mgm_answer_value\": 10,\n" +
                "              \"object_type\": 3\n" +
                "            },\n" +
                "            {\n" +
                "              \"origin_adm\": 688,\n" +
                "              \"cve_trip_mgm_section\": 82,\n" +
                "              \"cve_trip_mgm_question\": 105,\n" +
                "              \"cve_trip_mgm_answer\": 215,\n" +
                "              \"desc_trip_mgm_answer\": \"Media\",\n" +
                "              \"trip_mgm_answer_value\": 20,\n" +
                "              \"object_type\": 3\n" +
                "            },\n" +
                "            {\n" +
                "              \"origin_adm\": 688,\n" +
                "              \"cve_trip_mgm_section\": 82,\n" +
                "              \"cve_trip_mgm_question\": 105,\n" +
                "              \"cve_trip_mgm_answer\": 216,\n" +
                "              \"desc_trip_mgm_answer\": \"Alta\",\n" +
                "              \"trip_mgm_answer_value\": 30,\n" +
                "              \"object_type\": 3\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        //endregion

        Gson gson = new Gson();
        responseChecklist responseChecklist = gson.fromJson(jsonString, responseChecklist.class);

        // Access the deserialized object
        int responseCode = responseChecklist.getResponseCode();
        String message = responseChecklist.getMessage();
        List<Datum> dataList = responseChecklist.getData();
        this.mdata=dataList;
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


}
