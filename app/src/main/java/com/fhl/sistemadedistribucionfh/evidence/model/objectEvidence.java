package com.fhl.sistemadedistribucionfh.evidence.model;

public class objectEvidence {
    private String evidence;
    private Boolean  hasthisEvidence;
    private Boolean  isTaken;
    public objectEvidence(String evidence,Boolean  hasthisEvidence,Boolean  isTaken){
        this.evidence=evidence;
        this.hasthisEvidence=hasthisEvidence;
        this.isTaken=isTaken;

    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public Boolean getHasthisEvidence() {
        return hasthisEvidence;
    }

    public void setHasthisEvidence(Boolean hasthisEvidence) {
        this.hasthisEvidence = hasthisEvidence;
    }

    public Boolean getTaken() {
        return isTaken;
    }

    public void setTaken(Boolean taken) {
        isTaken = taken;
    }
}
