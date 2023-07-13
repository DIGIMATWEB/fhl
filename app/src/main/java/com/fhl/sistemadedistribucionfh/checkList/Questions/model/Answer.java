
package com.fhl.sistemadedistribucionfh.checkList.Questions.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Answer {

    @SerializedName("origin_adm")
    @Expose
    private Integer originAdm;
    @SerializedName("cve_trip_mgm_section")
    @Expose
    private Integer cveTripMgmSection;
    @SerializedName("cve_trip_mgm_question")
    @Expose
    private Integer cveTripMgmQuestion;
    @SerializedName("cve_trip_mgm_answer")
    @Expose
    private Integer cveTripMgmAnswer;
    @SerializedName("desc_trip_mgm_answer")
    @Expose
    private String descTripMgmAnswer;
    @SerializedName("trip_mgm_answer_value")
    @Expose
    private Integer tripMgmAnswerValue;
    @SerializedName("object_type")
    @Expose
    private Integer objectType;

    public Answer(Integer originAdm, Integer cveTripMgmSection, Integer cveTripMgmQuestion, Integer cveTripMgmAnswer, String descTripMgmAnswer, Integer tripMgmAnswerValue, Integer objectType) {
        super();
        this.originAdm = originAdm;
        this.cveTripMgmSection = cveTripMgmSection;
        this.cveTripMgmQuestion = cveTripMgmQuestion;
        this.cveTripMgmAnswer = cveTripMgmAnswer;
        this.descTripMgmAnswer = descTripMgmAnswer;
        this.tripMgmAnswerValue = tripMgmAnswerValue;
        this.objectType = objectType;
    }

    public Integer getOriginAdm() {
        return originAdm;
    }

    public void setOriginAdm(Integer originAdm) {
        this.originAdm = originAdm;
    }

    public Integer getCveTripMgmSection() {
        return cveTripMgmSection;
    }

    public void setCveTripMgmSection(Integer cveTripMgmSection) {
        this.cveTripMgmSection = cveTripMgmSection;
    }

    public Integer getCveTripMgmQuestion() {
        return cveTripMgmQuestion;
    }

    public void setCveTripMgmQuestion(Integer cveTripMgmQuestion) {
        this.cveTripMgmQuestion = cveTripMgmQuestion;
    }

    public Integer getCveTripMgmAnswer() {
        return cveTripMgmAnswer;
    }

    public void setCveTripMgmAnswer(Integer cveTripMgmAnswer) {
        this.cveTripMgmAnswer = cveTripMgmAnswer;
    }

    public String getDescTripMgmAnswer() {
        return descTripMgmAnswer;
    }

    public void setDescTripMgmAnswer(String descTripMgmAnswer) {
        this.descTripMgmAnswer = descTripMgmAnswer;
    }

    public Integer getTripMgmAnswerValue() {
        return tripMgmAnswerValue;
    }

    public void setTripMgmAnswerValue(Integer tripMgmAnswerValue) {
        this.tripMgmAnswerValue = tripMgmAnswerValue;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

}
