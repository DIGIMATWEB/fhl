
package com.fhl.sistemadedistribucionfh.Sellos.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class TimeMeasure {

    @SerializedName("IsRunning")
    @Expose
    private Boolean isRunning;
    @SerializedName("Elapsed")
    @Expose
    private String elapsed;
    @SerializedName("ElapsedMilliseconds")
    @Expose
    private Integer elapsedMilliseconds;
    @SerializedName("ElapsedTicks")
    @Expose
    private Integer elapsedTicks;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TimeMeasure() {
    }

    /**
     * 
     * @param elapsed
     * @param elapsedTicks
     * @param isRunning
     * @param elapsedMilliseconds
     */
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
