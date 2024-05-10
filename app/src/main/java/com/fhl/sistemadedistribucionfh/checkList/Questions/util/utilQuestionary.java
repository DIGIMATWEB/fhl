package com.fhl.sistemadedistribucionfh.checkList.Questions.util;

import com.fhl.sistemadedistribucionfh.Retrofit.RetrofitEndPoints;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.Answer;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.Datum;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.Question;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.questions.responseQuestions;
import com.fhl.sistemadedistribucionfh.checkList.Questions.model.responseChecklist;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.responseChecklistV2;
import com.fhl.sistemadedistribucionfh.checkList.model.v2.responseSendChecklist;
import com.fhl.sistemadedistribucionfh.evidence.model.changeStatusmanifestticket.responseStatusManifestOrTicket;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface utilQuestionary {

    @GET(RetrofitEndPoints.CHECKLISTV2)
    Call<responseQuestions> getChecklist(@Header("Authorization") String authorizationHeader,
                                         @Query("vehiculoId") Integer vehiculoId ,
                                         @Query("checklistId") Integer checklistId);

    @Multipart
    @POST(RetrofitEndPoints.SETCHECKLISTBYVEHICULO)
    Call<responseSendChecklist> setChecklistByVehiculo(
            @Header("Authorization") String authorization,
            @Part("VehiculoChkId") RequestBody vehiculoChkId,
            @Part("DespachoId") RequestBody despachoId,
            @Part("FechaAplicado") RequestBody fechaAplicado,
            @Part("JsonRespuestas") RequestBody jsonRespuestas,
            @Part("Usuario") RequestBody usuario,
            @Part("VehiculoId") RequestBody vehiculoId,
            @Part("ChecklistId") RequestBody checklistId
    );
}
