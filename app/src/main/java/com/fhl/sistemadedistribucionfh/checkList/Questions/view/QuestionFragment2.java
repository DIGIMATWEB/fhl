package com.fhl.sistemadedistribucionfh.checkList.Questions.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.fhl.sistemadedistribucionfh.R;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.Answer;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.Question;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.Pregunta;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.Respuesta;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionFragment2 extends Fragment {
    private Pregunta question;
    private int pos;
    private questionFragment mview;
    private ConstraintLayout switchanswer, optionanswer, openanswer;
    private List<Pregunta> mquestions;
    private TextView textopenquestion, textopenquestion1, textbooleanonly, textPosition1, textPosition2, textPosition3, textButtonFalse, textButtonTrue, textCameraOnlySwitch, textCameraOnlyMultiple, textCameraOnlyOpen;
    private ImageButton buttonTrue, buttonFalse;
    private ImageView buttonCameraSwitch, buttonCameraMultiple, buttonCameraOpen;
    private TextInputEditText inputEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_question, container, false);
        initView(view);
        return view;
    }

    public void initView(View view) {
        switchanswer = view.findViewById(R.id.switchanswer);
        optionanswer = view.findViewById(R.id.optionanswer);
        openanswer = view.findViewById(R.id.openanswer);
        textopenquestion = view.findViewById(R.id.textopenquestion);
        textopenquestion1 = view.findViewById(R.id.textopenquestion1);
        textbooleanonly = view.findViewById(R.id.textbooleanonly);
        textPosition1 = view.findViewById(R.id.textPosition);
        textPosition2 = view.findViewById(R.id.textPosition2);
        textPosition3 = view.findViewById(R.id.textPosition3);
        textButtonFalse = view.findViewById(R.id.textView21);
        textButtonTrue = view.findViewById(R.id.textView22);
        inputEditText = view.findViewById(R.id.inputTextd);
        textCameraOnlySwitch = view.findViewById(R.id.textView20);
        textCameraOnlyMultiple = view.findViewById(R.id.cameraTextMulti);
        textCameraOnlyOpen = view.findViewById(R.id.cameraTextOpen);

        buttonTrue = view.findViewById(R.id.trueButton);
        buttonFalse = view.findViewById(R.id.falseButton);
        buttonCameraSwitch = view.findViewById(R.id.imagephoto2);
        buttonCameraMultiple = view.findViewById(R.id.imagephoto);
        buttonCameraOpen = view.findViewById(R.id.imagephoto1);
        buttonFalse.setOnClickListener(this::initView);
        buttonTrue.setOnClickListener(this::initView);
        buttonCameraSwitch.setOnClickListener(this::initView);
        buttonCameraMultiple.setOnClickListener(this::initView);
        buttonCameraOpen.setOnClickListener(this::initView);

        // Log.d("SendCheck: ", Arrays.toString(question.getRespuestas().toArray()));
        Log.d("SendCheck: ", Arrays.toString(mview.checklist.toArray()));

        // Aqui revisamos si contiene fotos o no
        if (mview.checklist.get(pos).getArchivoDeEvidencia().isEmpty()) {
            // Si viene vacio el archivo de evidencia
            buttonCameraSwitch.setColorFilter(ContextCompat.getColor(getContext(), R.color.grayalfa), android.graphics.PorterDuff.Mode.SRC_IN);
            buttonCameraMultiple.setColorFilter(ContextCompat.getColor(getContext(), R.color.grayalfa), android.graphics.PorterDuff.Mode.SRC_IN);
            buttonCameraOpen.setColorFilter(ContextCompat.getColor(getContext(), R.color.grayalfa), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            // No viene vacio
            buttonCameraSwitch.setColorFilter(ContextCompat.getColor(getContext(), R.color.purple), android.graphics.PorterDuff.Mode.SRC_IN);
            buttonCameraMultiple.setColorFilter(ContextCompat.getColor(getContext(), R.color.purple), android.graphics.PorterDuff.Mode.SRC_IN);
            buttonCameraOpen.setColorFilter(ContextCompat.getColor(getContext(), R.color.purple), android.graphics.PorterDuff.Mode.SRC_IN);
        }

        buttonFalse.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForColorStateLists")
            @Override
            public void onClick(View view) {
                buttonFalse.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.purple));
                textButtonFalse.setTextColor(Color.WHITE);

                buttonTrue.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.grayalfa));
                textButtonTrue.setTextColor(getContext().getResources().getColorStateList(R.color.purple));

                Log.d("SendCheck: ", question.getRespuestas().get(1).getId().toString());
                sendAnswer(pos, question.getId(), question.getRespuestas().get(1).getId(), "", "");
            }
        });

        buttonTrue.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForColorStateLists")
            @Override
            public void onClick(View view) {
                buttonTrue.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.purple));
                textButtonTrue.setTextColor(Color.WHITE);

                buttonFalse.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.grayalfa));
                textButtonFalse.setTextColor(getContext().getResources().getColorStateList(R.color.purple));

                Log.d("SendCheck: ", question.getRespuestas().get(0).getId().toString());
                sendAnswer(pos, question.getId(), question.getRespuestas().get(0).getId(), "", "");
            }
        });

        // Set textPosition after initialization

        if (question.getTipoCampo() == 1) {//todo TIPO SWITCH
            textPosition1.setText((pos + 1) + "/" + mquestions.size());
            textPosition1 = view.findViewById(R.id.textPosition);
            switchanswer.setVisibility(View.VISIBLE);
            openanswer.setVisibility(View.GONE);
            optionanswer.setVisibility(View.GONE);

            Integer posicion = 0;

            // Revisamos la data
            if (mview.checklist.get(pos).getCveAnswer() != null || mview.checklist.get(pos).getCveAnswer() != 0) {
                // Recorremos el array para buscar el elemento con cve
                for (int i = 0; i < question.getRespuestas().size(); i++) {
                    Respuesta respuesta = question.getRespuestas().get(i);
                    if (respuesta.getId() == mview.checklist.get(pos).getCveAnswer()) {
                        // Se encontró el elemento con cve
                        posicion = i;

                        Log.d("SendCheck: ", question.getRespuestas().get(posicion).getNombre());

                        // Cuando es diferente a 0 la posicion y la encontro
                        if (question.getRespuestas().get(posicion).getNombre().equals("Sí")) {
                            // Cuando es Si
                            buttonTrue.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.purple));
                            textButtonTrue.setTextColor(Color.WHITE);

                            buttonFalse.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.grayalfa));
                            textButtonFalse.setTextColor(getContext().getResources().getColorStateList(R.color.purple));
                        } else if (question.getRespuestas().get(posicion).getNombre().equals("No")) {
                            // Cuando es No
                            buttonFalse.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.purple));
                            textButtonFalse.setTextColor(Color.WHITE);

                            buttonTrue.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.grayalfa));
                            textButtonTrue.setTextColor(getContext().getResources().getColorStateList(R.color.purple));
                        } else {
                            // Los dejamos neutros
                            buttonFalse.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.grayalfa));
                            buttonTrue.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.grayalfa));
                        }
                        break; // Salimos del bucle una vez que encontramos el elemento
                    }
                }
            } else {
                // Los dejamos neutros
                buttonFalse.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.grayalfa));
                buttonTrue.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.grayalfa));

                textButtonTrue.setTextColor(getContext().getResources().getColorStateList(R.color.purple));
                textButtonFalse.setTextColor(getContext().getResources().getColorStateList(R.color.purple));
            }

            //sendAnswer(3,"true",null);
        } else if (question.getTipoCampo() == 2) { //todo TIPO OPEN
            textPosition2.setText((pos + 1) + "/" + mquestions.size());
            openanswer.setVisibility(View.VISIBLE);
            switchanswer.setVisibility(View.GONE);
            optionanswer.setVisibility(View.GONE);
            //sendAnswer(3,"text",null);

        } else if (question.getTipoCampo() == 3) {//todo TIPOS MULTIPLE
            textPosition3.setText((pos + 1) + "/" + mquestions.size());
            openanswer.setVisibility(View.GONE);
            switchanswer.setVisibility(View.GONE);
            optionanswer.setVisibility(View.VISIBLE);

            // Esto es el Spinner
            Spinner spinner = view.findViewById(R.id.spinnerquestionary);

            List<String> items = new ArrayList<>(); // Replace with your desired data items
            items.clear();
            for (Respuesta r : mquestions.get(pos).getRespuestas()) {
                items.add(r.getNombre());
            }
            items.add(0, "Selecciona una opción");

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, items);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setPopupBackgroundResource(android.R.color.transparent); // Make dropdown background transparent
            spinner.setDropDownVerticalOffset(spinner.getHeight()); // Set vertical offset to position the dialog

            // Esto es para setear el dato de respuesta si es que existe
            if (question.getRespuestas() != null) {
                // Si la data contiene datos revisamos si existe la respuesta dentro del arreglo
                for (int i = 0; i < question.getRespuestas().size(); i++) {
                    Log.e("SendCheck: ", " " + question.getRespuestas().get(i).getId() + "   " + mview.checklist.get(pos).getCveAnswer());//Questions.fulChecklist.get(i).getAnswerId()
                    if (question.getRespuestas().get(i).getId() == mview.checklist.get(pos).getCveAnswer()) {
                        // Aqui seteamos la posicion para que se seleccione la respuesta del usuario
                        spinner.setSelection(i + 1);
                        Log.e("SendCheck: ", " " + spinner.getSelectedItem());
                    } else {
                        // No hacemos nada si no encuentra la respuesta
                    }
                }
            } else {
                // Nada si viene vacia la data
            }

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Integer idAnswer = 0;
                    for (Respuesta r : mquestions.get(pos).getRespuestas()) {
                        if (r.getNombre() == parent.getSelectedItem()) {
                            idAnswer = r.getId();
                        }
                    }

                    // Mandamos el dato seleccionado
                    Log.d("SendCheck: ", idAnswer.toString());
                    //sendAnswer(3,parent.getSelectedItem().toString(),idAnswer);
                    sendAnswer(pos, question.getId(), idAnswer, "", "");
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        // Revisamos para el boton de camara
        if (question.getFotoRequerida().equals(true)) {
            // Cuando pide la foto
            // Texto
            textCameraOnlySwitch.setVisibility(View.VISIBLE);
            textCameraOnlyMultiple.setVisibility(View.VISIBLE);
            textCameraOnlyOpen.setVisibility(View.VISIBLE);

            // Boton
            buttonCameraSwitch.setVisibility(View.VISIBLE);
            buttonCameraOpen.setVisibility(View.VISIBLE);
            buttonCameraMultiple.setVisibility(View.VISIBLE);
        } else {
            // Cuando no pide la foto
            // Texto
            textCameraOnlySwitch.setVisibility(View.GONE);
            textCameraOnlyMultiple.setVisibility(View.GONE);
            textCameraOnlyOpen.setVisibility(View.GONE);

            // Boton
            buttonCameraSwitch.setVisibility(View.GONE);
            buttonCameraOpen.setVisibility(View.GONE);
            buttonCameraMultiple.setVisibility(View.GONE);
        }

        buttonCameraSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("SendCheck: ", "Button pressed");
                mview.takePick(question.getId(), pos);
            }
        });

        buttonCameraOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("SendCheck: ", "Button pressed");
                mview.takePick(question.getId(), pos);
            }
        });

        buttonCameraMultiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("SendCheck: ", "Button pressed");
                mview.takePick(question.getId(), pos);
            }
        });

        if (textopenquestion != null) {
            textopenquestion.setText("" + mquestions.get(pos).getNombre());
            textopenquestion1.setText("" + mquestions.get(pos).getNombre());
            textbooleanonly.setText("" + mquestions.get(pos).getNombre());
        }

        inputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence textWatcher, int i, int i1, int i2) {
                //Log.e("textWatcher1", "beforeTextChanged: " + textWatcher);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Log.e("textWatcher2", "onTextChanged: " + charSequence);
                //myview.safeValues(position, false, 1, 1, data.get(position).getCveTripMgmQuestion(), 0, data.get(position).getAnswers().get(0).getCveTripMgmAnswer(),  charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d("SendCheck: ", "Text Writing" + editable.toString());

                //Funcion nueva para la DB nueva
                sendAnswer(pos, question.getId(), mview.checklist.get(pos).getCveAnswer(), editable.toString(), "");
            }

        });
    }

    public void updateView(Pregunta question) {
        this.question = question;
        initView(getView()); // Llama a initView() para actualizar la vista
    }

    private void sendAnswer(Integer pos, Integer cvePregunta, Integer cveAnswer, String descAnswerAbierta, String evidencia) {
        //mview.setAndswersF(pos,mquestions.get(pos).getId(),type,AnswerDesc,AnswerId);
        mview.setAndswersF(pos, cvePregunta, cveAnswer, descAnswerAbierta, evidencia);
    }

    public QuestionFragment2(Pregunta question, questionFragment mview, int position, List<Pregunta> questions) {
        this.question = question;
        this.mview = mview;
        this.pos = position;
        this.mquestions = questions;
    }

    public static QuestionFragment2 newInstance(Pregunta question, questionFragment mview, int position, List<Pregunta> questions) {
        // Aqui crea las vistas
        return new QuestionFragment2(question, mview, position, questions);
    }
}