
package com.fhl.sistemadedistribucionfh.sendTripPlus.model.request;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trip {

    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("orderFolio")
    @Expose
    private Integer orderFolio;
    @SerializedName("order_driver")
    @Expose
    private String orderDriver;
    @SerializedName("order_timestamp_d")
    @Expose
    private String orderTimestampD;
    @SerializedName("order_timestamp_interval_e")
    @Expose
    private String orderTimestampIntervalE;
    @SerializedName("order_timestamp_interval_s")
    @Expose
    private String orderTimestampIntervalS;
    @SerializedName("order_timestamp_o")
    @Expose
    private String orderTimestampO;
    @SerializedName("order_uploading_time")
    @Expose
    private Integer orderUploadingTime;
    @SerializedName("package_counts")
    @Expose
    private Integer packageCounts;
    @SerializedName("packages")
    @Expose
    private List<Package> packages;
    @SerializedName("payment_type")
    @Expose
    private String paymentType;
    @SerializedName("provider")
    @Expose
    private String provider;
    @SerializedName("recipient_city")
    @Expose
    private String recipientCity;
    @SerializedName("recipient_companyname")
    @Expose
    private String recipientCompanyname;
    @SerializedName("recipient_country")
    @Expose
    private String recipientCountry;
    @SerializedName("recipient_email")
    @Expose
    private String recipientEmail;
    @SerializedName("recipient_latitude")
    @Expose
    private Integer recipientLatitude;
    @SerializedName("recipient_longitude")
    @Expose
    private Integer recipientLongitude;
    @SerializedName("recipient_name")
    @Expose
    private String recipientName;
    @SerializedName("recipient_phone")
    @Expose
    private String recipientPhone;
    @SerializedName("recipient_postalcode")
    @Expose
    private String recipientPostalcode;
    @SerializedName("recipient_street")
    @Expose
    private String recipientStreet;
    @SerializedName("route")
    @Expose
    private String route;
    @SerializedName("service_type")
    @Expose
    private String serviceType;
    @SerializedName("shipper_city")
    @Expose
    private String shipperCity;
    @SerializedName("shipper_companyname")
    @Expose
    private String shipperCompanyname;
    @SerializedName("shipper_country")
    @Expose
    private String shipperCountry;
    @SerializedName("shipper_email")
    @Expose
    private String shipperEmail;
    @SerializedName("shipper_name")
    @Expose
    private String shipperName;
    @SerializedName("shipper_phone")
    @Expose
    private String shipperPhone;
    @SerializedName("shipper_postalcode")
    @Expose
    private String shipperPostalcode;
    @SerializedName("shipper_street")
    @Expose
    private String shipperStreet;
    @SerializedName("vehicle_name")
    @Expose
    private String vehicleName;
    @SerializedName("vehicle_plate")
    @Expose
    private String vehiclePlate;

    public Trip(String comments, Integer orderFolio, String orderDriver, String orderTimestampD, String orderTimestampIntervalE, String orderTimestampIntervalS, String orderTimestampO, Integer orderUploadingTime, Integer packageCounts, List<Package> packages, String paymentType, String provider, String recipientCity, String recipientCompanyname, String recipientCountry, String recipientEmail, Integer recipientLatitude, Integer recipientLongitude, String recipientName, String recipientPhone, String recipientPostalcode, String recipientStreet, String route, String serviceType, String shipperCity, String shipperCompanyname, String shipperCountry, String shipperEmail, String shipperName, String shipperPhone, String shipperPostalcode, String shipperStreet, String vehicleName, String vehiclePlate) {
        super();
        this.comments = comments;
        this.orderFolio = orderFolio;
        this.orderDriver = orderDriver;
        this.orderTimestampD = orderTimestampD;
        this.orderTimestampIntervalE = orderTimestampIntervalE;
        this.orderTimestampIntervalS = orderTimestampIntervalS;
        this.orderTimestampO = orderTimestampO;
        this.orderUploadingTime = orderUploadingTime;
        this.packageCounts = packageCounts;
        this.packages = packages;
        this.paymentType = paymentType;
        this.provider = provider;
        this.recipientCity = recipientCity;
        this.recipientCompanyname = recipientCompanyname;
        this.recipientCountry = recipientCountry;
        this.recipientEmail = recipientEmail;
        this.recipientLatitude = recipientLatitude;
        this.recipientLongitude = recipientLongitude;
        this.recipientName = recipientName;
        this.recipientPhone = recipientPhone;
        this.recipientPostalcode = recipientPostalcode;
        this.recipientStreet = recipientStreet;
        this.route = route;
        this.serviceType = serviceType;
        this.shipperCity = shipperCity;
        this.shipperCompanyname = shipperCompanyname;
        this.shipperCountry = shipperCountry;
        this.shipperEmail = shipperEmail;
        this.shipperName = shipperName;
        this.shipperPhone = shipperPhone;
        this.shipperPostalcode = shipperPostalcode;
        this.shipperStreet = shipperStreet;
        this.vehicleName = vehicleName;
        this.vehiclePlate = vehiclePlate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getOrderFolio() {
        return orderFolio;
    }

    public void setOrderFolio(Integer orderFolio) {
        this.orderFolio = orderFolio;
    }

    public String getOrderDriver() {
        return orderDriver;
    }

    public void setOrderDriver(String orderDriver) {
        this.orderDriver = orderDriver;
    }

    public String getOrderTimestampD() {
        return orderTimestampD;
    }

    public void setOrderTimestampD(String orderTimestampD) {
        this.orderTimestampD = orderTimestampD;
    }

    public String getOrderTimestampIntervalE() {
        return orderTimestampIntervalE;
    }

    public void setOrderTimestampIntervalE(String orderTimestampIntervalE) {
        this.orderTimestampIntervalE = orderTimestampIntervalE;
    }

    public String getOrderTimestampIntervalS() {
        return orderTimestampIntervalS;
    }

    public void setOrderTimestampIntervalS(String orderTimestampIntervalS) {
        this.orderTimestampIntervalS = orderTimestampIntervalS;
    }

    public String getOrderTimestampO() {
        return orderTimestampO;
    }

    public void setOrderTimestampO(String orderTimestampO) {
        this.orderTimestampO = orderTimestampO;
    }

    public Integer getOrderUploadingTime() {
        return orderUploadingTime;
    }

    public void setOrderUploadingTime(Integer orderUploadingTime) {
        this.orderUploadingTime = orderUploadingTime;
    }

    public Integer getPackageCounts() {
        return packageCounts;
    }

    public void setPackageCounts(Integer packageCounts) {
        this.packageCounts = packageCounts;
    }

    public List<Package> getPackages() {
        return packages;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getRecipientCity() {
        return recipientCity;
    }

    public void setRecipientCity(String recipientCity) {
        this.recipientCity = recipientCity;
    }

    public String getRecipientCompanyname() {
        return recipientCompanyname;
    }

    public void setRecipientCompanyname(String recipientCompanyname) {
        this.recipientCompanyname = recipientCompanyname;
    }

    public String getRecipientCountry() {
        return recipientCountry;
    }

    public void setRecipientCountry(String recipientCountry) {
        this.recipientCountry = recipientCountry;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public Integer getRecipientLatitude() {
        return recipientLatitude;
    }

    public void setRecipientLatitude(Integer recipientLatitude) {
        this.recipientLatitude = recipientLatitude;
    }

    public Integer getRecipientLongitude() {
        return recipientLongitude;
    }

    public void setRecipientLongitude(Integer recipientLongitude) {
        this.recipientLongitude = recipientLongitude;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public String getRecipientPostalcode() {
        return recipientPostalcode;
    }

    public void setRecipientPostalcode(String recipientPostalcode) {
        this.recipientPostalcode = recipientPostalcode;
    }

    public String getRecipientStreet() {
        return recipientStreet;
    }

    public void setRecipientStreet(String recipientStreet) {
        this.recipientStreet = recipientStreet;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getShipperCity() {
        return shipperCity;
    }

    public void setShipperCity(String shipperCity) {
        this.shipperCity = shipperCity;
    }

    public String getShipperCompanyname() {
        return shipperCompanyname;
    }

    public void setShipperCompanyname(String shipperCompanyname) {
        this.shipperCompanyname = shipperCompanyname;
    }

    public String getShipperCountry() {
        return shipperCountry;
    }

    public void setShipperCountry(String shipperCountry) {
        this.shipperCountry = shipperCountry;
    }

    public String getShipperEmail() {
        return shipperEmail;
    }

    public void setShipperEmail(String shipperEmail) {
        this.shipperEmail = shipperEmail;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public String getShipperPhone() {
        return shipperPhone;
    }

    public void setShipperPhone(String shipperPhone) {
        this.shipperPhone = shipperPhone;
    }

    public String getShipperPostalcode() {
        return shipperPostalcode;
    }

    public void setShipperPostalcode(String shipperPostalcode) {
        this.shipperPostalcode = shipperPostalcode;
    }

    public String getShipperStreet() {
        return shipperStreet;
    }

    public void setShipperStreet(String shipperStreet) {
        this.shipperStreet = shipperStreet;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

}
