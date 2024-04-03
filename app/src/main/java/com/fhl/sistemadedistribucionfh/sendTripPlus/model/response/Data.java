
package com.fhl.sistemadedistribucionfh.sendTripPlus.model.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("orderFolio")
    @Expose
    private String orderFolio;
    @SerializedName("provider")
    @Expose
    private String provider;
    @SerializedName("service_type")
    @Expose
    private String serviceType;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("delivery_tea")
    @Expose
    private String deliveryTea;
    @SerializedName("folTrip")
    @Expose
    private String folTrip;
    @SerializedName("packages")
    @Expose
    private List<Package> packages;

    public Data(String message, String orderFolio, String provider, String serviceType, String currency, String amount, String deliveryTea, String folTrip, List<Package> packages) {
        super();
        this.message = message;
        this.orderFolio = orderFolio;
        this.provider = provider;
        this.serviceType = serviceType;
        this.currency = currency;
        this.amount = amount;
        this.deliveryTea = deliveryTea;
        this.folTrip = folTrip;
        this.packages = packages;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrderFolio() {
        return orderFolio;
    }

    public void setOrderFolio(String orderFolio) {
        this.orderFolio = orderFolio;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDeliveryTea() {
        return deliveryTea;
    }

    public void setDeliveryTea(String deliveryTea) {
        this.deliveryTea = deliveryTea;
    }

    public String getFolTrip() {
        return folTrip;
    }

    public void setFolTrip(String folTrip) {
        this.folTrip = folTrip;
    }

    public List<Package> getPackages() {
        return packages;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

}
