package com.fhl.sistemadedistribucionfh.evidence.adapter;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.fhl.sistemadedistribucionfh.checkList.Questions.model.sendChecklist.SendCheck;
import com.fhl.sistemadedistribucionfh.checkList.Questions.view.QuestionFragment2;
import com.fhl.sistemadedistribucionfh.checkList.Questions.view.questionFragment;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.Pregunta;
import com.fhl.sistemadedistribucionfh.evidence.checklist.zQuestionCheckllist.QuestionFragment2Evidence;
import com.fhl.sistemadedistribucionfh.evidence.checklist.zQuestionCheckllist.questionEvidence;

import java.util.Arrays;
import java.util.List;

public class QuestionAdapterEvidence extends FragmentStateAdapter {
    private static List<Pregunta> questions;
    private questionEvidence mview;
    private ViewPager2 viewPager;
    public static int pagerIndicatorActual;
    public static Fragment fragmentGlobal;
    public static Integer pos;
    public QuestionAdapterEvidence(List<Pregunta> questionList, @NonNull FragmentActivity fragmentActivity, questionEvidence mview, ViewPager2 viewPager) {
        super(fragmentActivity);
        this.mview=mview;
        this.questions = questionList;
        this.viewPager = viewPager;
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                Log.d("SendCheck: ", "Datos en el PageSelector" + Arrays.toString(mview.checklist.toArray()));
                Log.d("SendCheck: ", "ObjectType: " + questions.get(position).getTipoCampo());

                Fragment fragment = fragmentActivity.getSupportFragmentManager().findFragmentByTag("f" + position);
                fragmentGlobal = fragmentActivity.getSupportFragmentManager().findFragmentByTag("f" + position);
                pos = position;

                mostrarToastSiRespuestaVacias(mview.checklist, questions);

                // Empezamos a validar para las alertas y el flujo del CheckList
                if (position != pagerIndicatorActual) {
                    // Cuando es diferente estan cambiando de ventana
                    Log.d("SendCheck", "Es diferente el valor ");

                    if (position < pagerIndicatorActual) {
                        // Cuando el position es menor, estan retrocediendo de ventana sin haber contestado
                        // Revisamos si es Type 2 o no.
                        if (questions.get(position).getTipoCampo() == 2) {
                            // Cuando es Type 2
                            if (mview.checklist.get(position).getDescAnswerAbierta().isEmpty()) {
                                // Si viene vacia la respuesta
                                Toast.makeText(mview.getContext(), "Se necesita agregar una respuesta.", Toast.LENGTH_SHORT).show();
                                viewPager.setCurrentItem(pagerIndicatorActual, true);
                            } else {
                                // Si viene contestada
                                if (fragment instanceof QuestionFragment2Evidence) {
                                    ((QuestionFragment2Evidence) fragment).updateView(questions.get(position));
                                }
                            }
                        } else {
                            // Cuando no es Type 2
                            // Revisamos que tenga respuesta
                            if (mview.checklist.get(position).getCveAnswer().equals(0)) {
                                // Cuando viene vacio
                                Toast.makeText(mview.getContext(), "Se necesita agregar una respuesta.", Toast.LENGTH_SHORT).show();
                                viewPager.setCurrentItem(pagerIndicatorActual, true);
                            } else {
                                // Si la respuesta no viene vacia
                                if (fragment instanceof QuestionFragment2Evidence) {
                                    ((QuestionFragment2Evidence) fragment).updateView(questions.get(position));
                                }
                            }
                        }
                    } else {
                        // Cuando la position sea mayor
                        Log.d("SendCheck", "Es mayor la position al actualPagerIndicator");
                        if (questions.get(pagerIndicatorActual).getTipoCampo() == 2) {
                            // Cuando es Type 2
                            if (mview.checklist.get(pagerIndicatorActual).getDescAnswerAbierta().isEmpty()) {
                                // Si viene vacia la respuesta
                                Toast.makeText(mview.getContext(), "Se necesita agregar una respuesta.", Toast.LENGTH_SHORT).show();
                                viewPager.setCurrentItem(pagerIndicatorActual, true);
                            } else {
                                // Si viene contestada
                                if (fragment instanceof QuestionFragment2Evidence) {
                                    ((QuestionFragment2Evidence) fragment).updateView(questions.get(position));
                                }
                            }
                        } else {
                            // Cuando no es Type 2
                            if (mview.checklist.get(pagerIndicatorActual).getCveAnswer().equals(0)) {
                                // Cuando viene vacio
                                Toast.makeText(mview.getContext(), "Se necesita agregar una respuesta.", Toast.LENGTH_SHORT).show();
                                viewPager.setCurrentItem(pagerIndicatorActual, true);
                            } else {
                                // Cuando no viene vacio
                                if (fragment instanceof QuestionFragment2Evidence) {
                                    ((QuestionFragment2Evidence) fragment).updateView(questions.get(position));
                                }
                            }
                        }
                    }
                } else {
                    //Cuando es igual (entrar a una ventana desde 0)
                    Log.d("SendCheck", "Es igual el valor");

                    if (mview.checklist.get(position).getCveAnswer().equals(0)) {
                        //Toast.makeText(getContext(), "Se necesita agregar una respuesta.", Toast.LENGTH_SHORT).show();
                        viewPager.setCurrentItem(pagerIndicatorActual, true);
                    } else {
                        //Sigue el proceso
                    }
                }

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

    public static void updateViewPagerAtPosition() {
        if (fragmentGlobal instanceof QuestionFragment2Evidence) {
            ((QuestionFragment2Evidence) fragmentGlobal).updateView(questions.get(pos));
        }
    }

    public static void mostrarToastSiRespuestaVacias(List<SendCheck> respuestas, List<Pregunta> questions) {
        // Log.d("SendCheck: ", "Datos en el PageSelector" + Arrays.toString(respuestas.toArray()));

        for (int position = 0; position < questions.size(); position++) {
            Pregunta question = questions.get(position);
            int tipoCampo = question.getTipoCampo();

            // Verificar si el tipo de campo es 1
            if (tipoCampo == 1) {
                // Obtener la respuesta correspondiente a esta pregunta
                SendCheck respuesta = respuestas.get(position);

                // Verificar si la respuesta está vacía o nula
                if (respuesta == null || respuesta.getCveAnswer() == null || respuesta.getCveAnswer().equals(0)) {
                    // Mostrar mensaje de error o tomar alguna acción necesaria
                    Log.d("SendCheck: ", "La respuesta en la posición " + position + " está vacía");

                    pagerIndicatorActual = position;

                    // Detener el proceso
                    return;
                }
            } else if (tipoCampo == 2) {
                // Verificar si la descripción de respuesta abierta está vacía o nula
                String descAnswerAbierta = respuestas.get(position).getDescAnswerAbierta();
                if (descAnswerAbierta == null || descAnswerAbierta.isEmpty()) {
                    // Mostrar mensaje de error o tomar alguna acción necesaria
                    Log.d("SendCheck: ", "La descripción de respuesta abierta en la posición " + position + " está vacía");

                    pagerIndicatorActual = position;

                    // Detener el proceso
                    return;
                }
            } else if (tipoCampo == 3) {
                // Obtener la respuesta correspondiente a esta pregunta
                SendCheck respuesta = respuestas.get(position);

                // Verificar si la respuesta está vacía o nula
                if (respuesta == null || respuesta.getCveAnswer() == null || respuesta.getCveAnswer().equals(0)) {
                    // Mostrar mensaje de error o tomar alguna acción necesaria
                    Log.d("SendCheck: ", "La respuesta en la posición " + position + " está vacía");

                    pagerIndicatorActual = position;

                    // Detener el proceso
                    return;
                }
            }
        }

        // Si se completaron todas las validaciones, continuar con el proceso
        Log.d("SendCheck: ", "Todas las respuestas son válidas");
    }
}