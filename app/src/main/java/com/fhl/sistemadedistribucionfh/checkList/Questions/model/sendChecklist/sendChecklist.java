package com.fhl.sistemadedistribucionfh.checkList.Questions.model.sendChecklist;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class sendChecklist {//todo este debe ser un objeto para guardar las respuestas previamente seleccionadas en el checklist antes del sendchecklist

    @SerializedName("sendChecklist")
    private List<SendCheck> sendChecklist;
    @SerializedName("user")
    private String user;
    @SerializedName("cve_checklist")
    private Integer cveChecklist;
    public sendChecklist(List<SendCheck> sendChecklist, String user, Integer cveChecklist) {
        super();
        this.sendChecklist = sendChecklist;
        this.user = user;
        this.cveChecklist = cveChecklist;
    }

    public List<SendCheck> getSendChecklist() {
        return sendChecklist;
    }

    public void setSendChecklist(List<SendCheck> sendChecklist) {
        this.sendChecklist = sendChecklist;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getCveChecklist() {
        return cveChecklist;
    }

    public void setCveChecklist(Integer cveChecklist) {
        this.cveChecklist = cveChecklist;
    }
}
