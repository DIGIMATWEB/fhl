
package com.fhl.sistemadedistribucionfh.sendTripPlus.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Package {

    @SerializedName("package_id")
    @Expose
    private Integer packageId;
    @SerializedName("tracking_number")
    @Expose
    private String trackingNumber;
    @SerializedName("label_image_url")
    @Expose
    private String labelImageUrl;

    public Package(Integer packageId, String trackingNumber, String labelImageUrl) {
        super();
        this.packageId = packageId;
        this.trackingNumber = trackingNumber;
        this.labelImageUrl = labelImageUrl;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getLabelImageUrl() {
        return labelImageUrl;
    }

    public void setLabelImageUrl(String labelImageUrl) {
        this.labelImageUrl = labelImageUrl;
    }

}
