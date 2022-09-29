package com.bmc.suchane_svamitva.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Image {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "DistCode")
    private int DistCode;

    @ColumnInfo(name = "TalukCode")
    private String TalukCode;

    @ColumnInfo(name = "HobliCode")
    private String HobliCode;

    @ColumnInfo(name = "VillageCode")
    private String VillageCode;

    @ColumnInfo(name = "HamletCode")
    private int HamletCode;

    @ColumnInfo(name = "PropertyNo")
    private int PropertyNo;

    @ColumnInfo(name = "IsDraftCopyIssued")
    private int IsDraftCopyIssued;

    @ColumnInfo(name = "image_path")
    private String imagePath;

    @ColumnInfo(name = "image_name")
    private String imageName;

    @ColumnInfo(name = "latitude")
    private double latitude;

    @ColumnInfo(name = "longitude")
    private double longitude;

    @ColumnInfo(name = "created_date")
    private String createdDate;

    @ColumnInfo(name = "created_by")
    private String createdBy;

    @ColumnInfo(name = "created_mobile")
    private String createdMobile;

    @ColumnInfo(name = "is_not_sent")
    private boolean isNotSent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistCode() {
        return DistCode;
    }

    public void setDistCode(int distCode) {
        DistCode = distCode;
    }

    public String getTalukCode() {
        return TalukCode;
    }

    public void setTalukCode(String talukCode) {
        TalukCode = talukCode;
    }

    public String getHobliCode() {
        return HobliCode;
    }

    public void setHobliCode(String hobliCode) {
        HobliCode = hobliCode;
    }

    public String getVillageCode() {
        return VillageCode;
    }

    public void setVillageCode(String villageCode) {
        VillageCode = villageCode;
    }

    public int getHamletCode() {
        return HamletCode;
    }

    public void setHamletCode(int hamletCode) {
        HamletCode = hamletCode;
    }

    public int getPropertyNo() {
        return PropertyNo;
    }

    public void setPropertyNo(int propertyNo) {
        PropertyNo = propertyNo;
    }

    public int getIsDraftCopyIssued() {
        return IsDraftCopyIssued;
    }

    public void setIsDraftCopyIssued(int isDraftCopyIssued) {
        IsDraftCopyIssued = isDraftCopyIssued;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedMobile() {
        return createdMobile;
    }

    public void setCreatedMobile(String createdMobile) {
        this.createdMobile = createdMobile;
    }

    public boolean isNotSent() {
        return isNotSent;
    }

    public void setNotSent(boolean notSent) {
        isNotSent = notSent;
    }
}
