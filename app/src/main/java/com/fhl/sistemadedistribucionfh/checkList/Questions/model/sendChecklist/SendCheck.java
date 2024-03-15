package com.fhl.sistemadedistribucionfh.checkList.Questions.model.sendChecklist;

import com.google.gson.annotations.SerializedName;

public class SendCheck {
    @SerializedName("cve_pregunta")
    private Integer cvePregunta;
    @SerializedName("cve_answer")
    private Integer cveAnswer;
    @SerializedName("desc_answer_abierta")
    private String descAnswerAbierta;
    @SerializedName("archivo_de_evidencia")
    private String archivoDeEvidencia;

    public SendCheck(Integer cvePregunta, Integer cveAnswer, String descAnswerAbierta, String archivoDeEvidencia) {
        super();
        this.cvePregunta = cvePregunta;
        this.cveAnswer = cveAnswer;
        this.descAnswerAbierta = descAnswerAbierta;
        this.archivoDeEvidencia = archivoDeEvidencia;
    }

    public Integer getCvePregunta() {
        return cvePregunta;
    }

    public void setCvePregunta(Integer cvePregunta) {
        this.cvePregunta = cvePregunta;
    }

    public Integer getCveAnswer() {
        return cveAnswer;
    }

    public void setCveAnswer(Integer cveAnswer) {
        this.cveAnswer = cveAnswer;
    }

    public String getDescAnswerAbierta() {
        return descAnswerAbierta;
    }

    public void setDescAnswerAbierta(String descAnswerAbierta) {
        this.descAnswerAbierta = descAnswerAbierta;
    }

    public String getArchivoDeEvidencia() {
        return archivoDeEvidencia;
    }

    public void setArchivoDeEvidencia(String archivoDeEvidencia) {
        this.archivoDeEvidencia = archivoDeEvidencia;
    }
}
