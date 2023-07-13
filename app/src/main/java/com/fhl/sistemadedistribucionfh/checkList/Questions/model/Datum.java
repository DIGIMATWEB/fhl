
package com.fhl.sistemadedistribucionfh.checkList.Questions.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("origin_adm")
    @Expose
    private Integer originAdm;
    @SerializedName("cve_trip_mgm_section")
    @Expose
    private Integer cveTripMgmSection;
    @SerializedName("desc_trip_mgm_section")
    @Expose
    private String descTripMgmSection;
    @SerializedName("questions")
    @Expose
    private List<Question> questions;

    public Datum(Integer originAdm, Integer cveTripMgmSection, String descTripMgmSection, List<Question> questions) {
        super();
        this.originAdm = originAdm;
        this.cveTripMgmSection = cveTripMgmSection;
        this.descTripMgmSection = descTripMgmSection;
        this.questions = questions;
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

    public String getDescTripMgmSection() {
        return descTripMgmSection;
    }

    public void setDescTripMgmSection(String descTripMgmSection) {
        this.descTripMgmSection = descTripMgmSection;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}
