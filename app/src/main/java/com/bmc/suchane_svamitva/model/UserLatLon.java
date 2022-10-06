package com.bmc.suchane_svamitva.model;

import java.io.Serializable;

public class UserLatLon implements Serializable {
    private double latitude;
    private double longitude;
    private double accuracy;
    private String address;
    private String NoticeNo;
    private String addressCode;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public String getNoticeNo() {
        return NoticeNo;
    }

    public void setNoticeNo(String noticeNo) {
        NoticeNo = noticeNo;
    }

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }
}
