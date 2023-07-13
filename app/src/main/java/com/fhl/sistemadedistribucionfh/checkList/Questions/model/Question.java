
package com.fhl.sistemadedistribucionfh.checkList.Questions.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("origin_adm")
    @Expose
    private Integer originAdm;
    @SerializedName("cve_trip_mgm_section")
    @Expose
    private Integer cveTripMgmSection;
    @SerializedName("cve_trip_mgm_question")
    @Expose
    private Integer cveTripMgmQuestion;
    @SerializedName("desc_trip_mgm_question")
    @Expose
    private String descTripMgmQuestion;
    @SerializedName("required_evidence")
    @Expose
    private Boolean requiredEvidence;
    @SerializedName("instructions")
    @Expose
    private String instructions;
    @SerializedName("answers")
    @Expose
    private List<Answer> answers;

    public Question(Integer originAdm, Integer cveTripMgmSection, Integer cveTripMgmQuestion, String descTripMgmQuestion, Boolean requiredEvidence, String instructions, List<Answer> answers) {
        super();
        this.originAdm = originAdm;
        this.cveTripMgmSection = cveTripMgmSection;
        this.cveTripMgmQuestion = cveTripMgmQuestion;
        this.descTripMgmQuestion = descTripMgmQuestion;
        this.requiredEvidence = requiredEvidence;
        this.instructions = instructions;
        this.answers = answers;
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

    public String getDescTripMgmQuestion() {
        return descTripMgmQuestion;
    }

    public void setDescTripMgmQuestion(String descTripMgmQuestion) {
        this.descTripMgmQuestion = descTripMgmQuestion;
    }

    public Boolean getRequiredEvidence() {
        return requiredEvidence;
    }

    public void setRequiredEvidence(Boolean requiredEvidence) {
        this.requiredEvidence = requiredEvidence;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

}
