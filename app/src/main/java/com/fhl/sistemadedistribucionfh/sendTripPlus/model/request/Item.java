
package com.fhl.sistemadedistribucionfh.sendTripPlus.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("pieces")
    @Expose
    private Integer pieces;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Item() {
    }

    /**
     * 
     * @param pieces
     * @param description
     */
    public Item(String description, Integer pieces) {
        super();
        this.description = description;
        this.pieces = pieces;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPieces() {
        return pieces;
    }

    public void setPieces(Integer pieces) {
        this.pieces = pieces;
    }

}
