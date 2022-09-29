package com.bmc.suchane_svamitva.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Nischitha on 18,May,2022
 **/
@Entity
public class SvamitvaVillageMaster {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "BhoomiVMSTID")
    @SerializedName("BhoomiVMSTID")
    @Expose
    private String BhoomiVMSTID;

    @ColumnInfo(name = "BhoomiDistrictCode")
    @SerializedName("BhoomiDistrictCode")
    @Expose
    private String BhoomiDistrictCode;

    @ColumnInfo(name = "BhoomiDistrictName")
    @SerializedName("BhoomiDistrictName")
    @Expose
    private String BhoomiDistrictName;

    @ColumnInfo(name = "BhoomiDistrictNameKN")
    @SerializedName("BhoomiDistrictNameKN")
    @Expose
    private String BhoomiDistrictNameKN;

    @ColumnInfo(name = "BhoomiTalukCode")
    @SerializedName("BhoomiTalukCode")
    @Expose
    private String BhoomiTalukCode;

    @ColumnInfo(name = "BhoomiTalukName")
    @SerializedName("BhoomiTalukName")
    @Expose
    private String BhoomiTalukName;

    @ColumnInfo(name = "BhoomiTalukNameKN")
    @SerializedName("BhoomiTalukNameKN")
    @Expose
    private String BhoomiTalukNameKN;

    @ColumnInfo(name = "BhoomiHobliCode")
    @SerializedName("BhoomiHobliCode")
    @Expose
    private String BhoomiHobliCode;

    @ColumnInfo(name = "BhoomiHobliName")
    @SerializedName("BhoomiHobliName")
    @Expose
    private String BhoomiHobliName;

    @ColumnInfo(name = "BhoomiHobliNameKN")
    @SerializedName("BhoomiHobliNameKN")
    @Expose
    private String BhoomiHobliNameKN;

    @ColumnInfo(name = "BhoomiCircleCode")
    @SerializedName("BhoomiCircleCode")
    @Expose
    private String BhoomiCircleCode;

    @ColumnInfo(name = "BhoomiCircleName")
    @SerializedName("BhoomiCircleName")
    @Expose
    private String BhoomiCircleName;

    @ColumnInfo(name = "BhoomiCircleNameKN")
    @SerializedName("BhoomiCircleNameKN")
    @Expose
    private String BhoomiCircleNameKN;

    @ColumnInfo(name = "BhoomiVillageCode")
    @SerializedName("BhoomiVillageCode")
    @Expose
    private String BhoomiVillageCode;

    @ColumnInfo(name = "BhoomiVillageName")
    @SerializedName("BhoomiVillageName")
    @Expose
    private String BhoomiVillageName;

    @ColumnInfo(name = "BhoomiVillageNameKN")
    @SerializedName("BhoomiVillageNameKN")
    @Expose
    private String BhoomiVillageNameKN;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBhoomiVMSTID() {
        return BhoomiVMSTID;
    }

    public void setBhoomiVMSTID(String bhoomiVMSTID) {
        BhoomiVMSTID = bhoomiVMSTID;
    }

    public String getBhoomiDistrictCode() {
        return BhoomiDistrictCode;
    }

    public void setBhoomiDistrictCode(String bhoomiDistrictCode) {
        BhoomiDistrictCode = bhoomiDistrictCode;
    }

    public String getBhoomiDistrictName() {
        return BhoomiDistrictName;
    }

    public void setBhoomiDistrictName(String bhoomiDistrictName) {
        BhoomiDistrictName = bhoomiDistrictName;
    }

    public String getBhoomiDistrictNameKN() {
        return BhoomiDistrictNameKN;
    }

    public void setBhoomiDistrictNameKN(String bhoomiDistrictNameKN) {
        BhoomiDistrictNameKN = bhoomiDistrictNameKN;
    }

    public String getBhoomiTalukCode() {
        return BhoomiTalukCode;
    }

    public void setBhoomiTalukCode(String bhoomiTalukCode) {
        BhoomiTalukCode = bhoomiTalukCode;
    }

    public String getBhoomiTalukName() {
        return BhoomiTalukName;
    }

    public void setBhoomiTalukName(String bhoomiTalukName) {
        BhoomiTalukName = bhoomiTalukName;
    }

    public String getBhoomiTalukNameKN() {
        return BhoomiTalukNameKN;
    }

    public void setBhoomiTalukNameKN(String bhoomiTalukNameKN) {
        BhoomiTalukNameKN = bhoomiTalukNameKN;
    }

    public String getBhoomiHobliCode() {
        return BhoomiHobliCode;
    }

    public void setBhoomiHobliCode(String bhoomiHobliCode) {
        BhoomiHobliCode = bhoomiHobliCode;
    }

    public String getBhoomiHobliName() {
        return BhoomiHobliName;
    }

    public void setBhoomiHobliName(String bhoomiHobliName) {
        BhoomiHobliName = bhoomiHobliName;
    }

    public String getBhoomiHobliNameKN() {
        return BhoomiHobliNameKN;
    }

    public void setBhoomiHobliNameKN(String bhoomiHobliNameKN) {
        BhoomiHobliNameKN = bhoomiHobliNameKN;
    }

    public String getBhoomiCircleCode() {
        return BhoomiCircleCode;
    }

    public void setBhoomiCircleCode(String bhoomiCircleCode) {
        BhoomiCircleCode = bhoomiCircleCode;
    }

    public String getBhoomiCircleName() {
        return BhoomiCircleName;
    }

    public void setBhoomiCircleName(String bhoomiCircleName) {
        BhoomiCircleName = bhoomiCircleName;
    }

    public String getBhoomiCircleNameKN() {
        return BhoomiCircleNameKN;
    }

    public void setBhoomiCircleNameKN(String bhoomiCircleNameKN) {
        BhoomiCircleNameKN = bhoomiCircleNameKN;
    }

    public String getBhoomiVillageCode() {
        return BhoomiVillageCode;
    }

    public void setBhoomiVillageCode(String bhoomiVillageCode) {
        BhoomiVillageCode = bhoomiVillageCode;
    }

    public String getBhoomiVillageName() {
        return BhoomiVillageName;
    }

    public void setBhoomiVillageName(String bhoomiVillageName) {
        BhoomiVillageName = bhoomiVillageName;
    }

    public String getBhoomiVillageNameKN() {
        return BhoomiVillageNameKN;
    }

    public void setBhoomiVillageNameKN(String bhoomiVillageNameKN) {
        BhoomiVillageNameKN = bhoomiVillageNameKN;
    }
}
