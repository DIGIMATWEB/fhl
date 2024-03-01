package com.fhl.sistemadedistribucionfh.checkList.Questions.model.questions;
import com.google.gson.annotations.SerializedName;
public class TimeMeasure {

    @SerializedName("IsRunning")
    private Boolean isRunning;
    @SerializedName("Elapsed")
    private String elapsed;
    @SerializedName("ElapsedMilliseconds")
    private Integer elapsedMilliseconds;
    @SerializedName("ElapsedTicks")
    private Integer elapsedTicks;

    public TimeMeasure(Boolean isRunning, String elapsed, Integer elapsedMilliseconds, Integer elapsedTicks) {
        super();
        this.isRunning = isRunning;
        this.elapsed = elapsed;
        this.elapsedMilliseconds = elapsedMilliseconds;
        this.elapsedTicks = elapsedTicks;
    }

    public Boolean getIsRunning() {
        return isRunning;
    }

    public void setIsRunning(Boolean isRunning) {
        this.isRunning = isRunning;
    }

    public String getElapsed() {
        return elapsed;
    }

    public void setElapsed(String elapsed) {
        this.elapsed = elapsed;
    }

    public Integer getElapsedMilliseconds() {
        return elapsedMilliseconds;
    }

    public void setElapsedMilliseconds(Integer elapsedMilliseconds) {
        this.elapsedMilliseconds = elapsedMilliseconds;
    }

    public Integer getElapsedTicks() {
        return elapsedTicks;
    }

    public void setElapsedTicks(Integer elapsedTicks) {
        this.elapsedTicks = elapsedTicks;
    }
}
