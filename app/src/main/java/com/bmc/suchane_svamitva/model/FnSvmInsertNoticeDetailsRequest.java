package com.bmc.suchane_svamitva.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FnSvmInsertNoticeDetailsRequest {

//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "id")
//    private int id;

    @ColumnInfo(name = "NTC_VID")
    @SerializedName("NTC_VID")
    @Expose
    private String NTC_VID;

    @ColumnInfo(name = "NTC_DIST_CODE")
    @SerializedName("NTC_DIST_CODE")
    @Expose
    private String NTC_DIST_CODE;

    @ColumnInfo(name = "NTC_TLK_TWN_CODE")
    @SerializedName("NTC_TLK_TWN_CODE")
    @Expose
    private String NTC_TLK_TWN_CODE;

    @ColumnInfo(name = "NTC_WRD_VLG_CODE")
    @SerializedName("NTC_WRD_VLG_CODE")
    @Expose
    private String NTC_WRD_VLG_CODE;

    @ColumnInfo(name = "NTC_AREA_TYPE")
    @SerializedName("NTC_AREA_TYPE")
    @Expose
    private String NTC_AREA_TYPE;

    @ColumnInfo(name = "NTC_NOTICE_NO")
    @SerializedName("NTC_NOTICE_NO")
    @Expose
    private String NTC_NOTICE_NO;

    @ColumnInfo(name = "NTC_ADD_CODE")
    @SerializedName("NTC_ADD_CODE")
    @Expose
    private String NTC_ADD_CODE;

    @ColumnInfo(name = "NTC_OWNER_NAME")
    @SerializedName("NTC_OWNER_NAME")
    @Expose
    private String NTC_OWNER_NAME;

    @ColumnInfo(name = "NTC_OWNER_MOBILE_NO")
    @SerializedName("NTC_OWNER_MOBILE_NO")
    @Expose
    private String NTC_OWNER_MOBILE_NO;

    @ColumnInfo(name = "NTC_ADD_DOORNO")
    @SerializedName("NTC_ADD_DOORNO")
    @Expose
    private String NTC_ADD_DOORNO;

    @ColumnInfo(name = "NTC_BUILDING")
    @SerializedName("NTC_BUILDING")
    @Expose
    private String NTC_BUILDING;

    @ColumnInfo(name = "NTC_STREET_AREA")
    @SerializedName("NTC_STREET_AREA")
    @Expose
    private String NTC_STREET_AREA;

    @ColumnInfo(name = "NTC_LANDMARK")
    @SerializedName("NTC_LANDMARK")
    @Expose
    private String NTC_LANDMARK;

    @ColumnInfo(name = "NTC_WARD_VILLAGE")
    @SerializedName("NTC_WARD_VILLAGE")
    @Expose
    private String NTC_WARD_VILLAGE;

    @ColumnInfo(name = "NTC_CTY_TLK")
    @SerializedName("NTC_CTY_TLK")
    @Expose
    private String NTC_CTY_TLK;

    @ColumnInfo(name = "NTC_DISTRICT")
    @SerializedName("NTC_DISTRICT")
    @Expose
    private String NTC_DISTRICT;

    @ColumnInfo(name = "NTC_LAT")
    @SerializedName("NTC_LAT")
    @Expose
    private String NTC_LAT;

    @ColumnInfo(name = "NTC_LONG")
    @SerializedName("NTC_LONG")
    @Expose
    private String NTC_LONG;

    @ColumnInfo(name = "NTC_ACCURACY")
    @SerializedName("NTC_ACCURACY")
    @Expose
    private String NTC_ACCURACY;

    @ColumnInfo(name = "NTC_CBY")
    @SerializedName("NTC_CBY")
    @Expose
    private String NTC_CBY;

    public String getNTC_VID() {
        return NTC_VID;
    }

    public void setNTC_VID(String NTC_VID) {
        this.NTC_VID = NTC_VID;
    }

    public String getNTC_DIST_CODE() {
        return NTC_DIST_CODE;
    }

    public void setNTC_DIST_CODE(String NTC_DIST_CODE) {
        this.NTC_DIST_CODE = NTC_DIST_CODE;
    }

    public String getNTC_TLK_TWN_CODE() {
        return NTC_TLK_TWN_CODE;
    }

    public void setNTC_TLK_TWN_CODE(String NTC_TLK_TWN_CODE) {
        this.NTC_TLK_TWN_CODE = NTC_TLK_TWN_CODE;
    }

    public String getNTC_WRD_VLG_CODE() {
        return NTC_WRD_VLG_CODE;
    }

    public void setNTC_WRD_VLG_CODE(String NTC_WRD_VLG_CODE) {
        this.NTC_WRD_VLG_CODE = NTC_WRD_VLG_CODE;
    }

    public String getNTC_AREA_TYPE() {
        return NTC_AREA_TYPE;
    }

    public void setNTC_AREA_TYPE(String NTC_AREA_TYPE) {
        this.NTC_AREA_TYPE = NTC_AREA_TYPE;
    }

    public String getNTC_NOTICE_NO() {
        return NTC_NOTICE_NO;
    }

    public void setNTC_NOTICE_NO(String NTC_NOTICE_NO) {
        this.NTC_NOTICE_NO = NTC_NOTICE_NO;
    }

    public String getNTC_ADD_CODE() {
        return NTC_ADD_CODE;
    }

    public void setNTC_ADD_CODE(String NTC_ADD_CODE) {
        this.NTC_ADD_CODE = NTC_ADD_CODE;
    }

    public String getNTC_OWNER_NAME() {
        return NTC_OWNER_NAME;
    }

    public void setNTC_OWNER_NAME(String NTC_OWNER_NAME) {
        this.NTC_OWNER_NAME = NTC_OWNER_NAME;
    }

    public String getNTC_OWNER_MOBILE_NO() {
        return NTC_OWNER_MOBILE_NO;
    }

    public void setNTC_OWNER_MOBILE_NO(String NTC_OWNER_MOBILE_NO) {
        this.NTC_OWNER_MOBILE_NO = NTC_OWNER_MOBILE_NO;
    }

    public String getNTC_ADD_DOORNO() {
        return NTC_ADD_DOORNO;
    }

    public void setNTC_ADD_DOORNO(String NTC_ADD_DOORNO) {
        this.NTC_ADD_DOORNO = NTC_ADD_DOORNO;
    }

    public String getNTC_BUILDING() {
        return NTC_BUILDING;
    }

    public void setNTC_BUILDING(String NTC_BUILDING) {
        this.NTC_BUILDING = NTC_BUILDING;
    }

    public String getNTC_STREET_AREA() {
        return NTC_STREET_AREA;
    }

    public void setNTC_STREET_AREA(String NTC_STREET_AREA) {
        this.NTC_STREET_AREA = NTC_STREET_AREA;
    }

    public String getNTC_LANDMARK() {
        return NTC_LANDMARK;
    }

    public void setNTC_LANDMARK(String NTC_LANDMARK) {
        this.NTC_LANDMARK = NTC_LANDMARK;
    }

    public String getNTC_WARD_VILLAGE() {
        return NTC_WARD_VILLAGE;
    }

    public void setNTC_WARD_VILLAGE(String NTC_WARD_VILLAGE) {
        this.NTC_WARD_VILLAGE = NTC_WARD_VILLAGE;
    }

    public String getNTC_CTY_TLK() {
        return NTC_CTY_TLK;
    }

    public void setNTC_CTY_TLK(String NTC_CTY_TLK) {
        this.NTC_CTY_TLK = NTC_CTY_TLK;
    }

    public String getNTC_DISTRICT() {
        return NTC_DISTRICT;
    }

    public void setNTC_DISTRICT(String NTC_DISTRICT) {
        this.NTC_DISTRICT = NTC_DISTRICT;
    }

    public String getNTC_LAT() {
        return NTC_LAT;
    }

    public void setNTC_LAT(String NTC_LAT) {
        this.NTC_LAT = NTC_LAT;
    }

    public String getNTC_LONG() {
        return NTC_LONG;
    }

    public void setNTC_LONG(String NTC_LONG) {
        this.NTC_LONG = NTC_LONG;
    }

    public String getNTC_ACCURACY() {
        return NTC_ACCURACY;
    }

    public void setNTC_ACCURACY(String NTC_ACCURACY) {
        this.NTC_ACCURACY = NTC_ACCURACY;
    }

    public String getNTC_CBY() {
        return NTC_CBY;
    }

    public void setNTC_CBY(String NTC_CBY) {
        this.NTC_CBY = NTC_CBY;
    }
}
