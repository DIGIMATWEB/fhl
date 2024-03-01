package com.fhl.sistemadedistribucionfh.checkList.Questions.util;

import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.Answer;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.Datum;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.Question;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.questions.responseQuestions;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.responseChecklist;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.responseChecklistV2;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface utilQuestionary {

    @GET(RetrofitEndPoints.CHECKLISTV2)
    Call<responseQuestions> getChecklist(@Header("Authorization") String authorizationHeader,
                                         @Query("vehiculoId") Integer vehiculoId ,
                                         @Query("checklistId") Integer checklistId);
}
