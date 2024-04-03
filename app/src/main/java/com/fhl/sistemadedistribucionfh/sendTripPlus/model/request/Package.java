
package com.fhl.sistemadedistribucionfh.sendTripPlus.model.request;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Package {

    @SerializedName("items")
    @Expose
    private List<Item> items;
    @SerializedName("package_height")
    @Expose
    private Integer packageHeight;
    @SerializedName("package_id")
    @Expose
    private Integer packageId;
    @SerializedName("package_length")
    @Expose
    private Integer packageLength;
    @SerializedName("package_value")
    @Expose
    private Integer packageValue;
    @SerializedName("package_weigth")
    @Expose
    private Integer packageWeigth;
    @SerializedName("package_width")
    @Expose
    private Integer packageWidth;
    public Package(List<Item> items, Integer packageHeight, Integer packageId, Integer packageLength, Integer packageValue, Integer packageWeigth, Integer packageWidth) {
        super();
        this.items = items;
        this.packageHeight = packageHeight;
        this.packageId = packageId;
        this.packageLength = packageLength;
        this.packageValue = packageValue;
        this.packageWeigth = packageWeigth;
        this.packageWidth = packageWidth;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Integer getPackageHeight() {
        return packageHeight;
    }

    public void setPackageHeight(Integer packageHeight) {
        this.packageHeight = packageHeight;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public Integer getPackageLength() {
        return packageLength;
    }

    public void setPackageLength(Integer packageLength) {
        this.packageLength = packageLength;
    }

    public Integer getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(Integer packageValue) {
        this.packageValue = packageValue;
    }

    public Integer getPackageWeigth() {
        return packageWeigth;
    }

    public void setPackageWeigth(Integer packageWeigth) {
        this.packageWeigth = packageWeigth;
    }

    public Integer getPackageWidth() {
        return packageWidth;
    }

    public void setPackageWidth(Integer packageWidth) {
        this.packageWidth = packageWidth;
    }

}
