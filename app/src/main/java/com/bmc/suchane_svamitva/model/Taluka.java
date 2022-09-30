package com.bmc.suchane_svamitva.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

public class Taluka {

    @ColumnInfo(name = "DistrictCode")
    @SerializedName("DistrictCode")
    private String DISTRICT_CODE;

    @ColumnInfo(name = "TalukCode")
    @SerializedName("TalukCode")
    private String TALUKA_CODE;

    @ColumnInfo(name = "BhoomiTalukName")
    @SerializedName("BhoomiTalukName")
    private String TALUKA_NAME;

    public String getDISTRICT_CODE() {
        return DISTRICT_CODE;
    }

    public void setDISTRICT_CODE(String DISTRICT_CODE) {
        this.DISTRICT_CODE = DISTRICT_CODE;
    }

    public String getTALUKA_CODE() {
        return TALUKA_CODE;
    }

    public void setTALUKA_CODE(String TALUKA_CODE) {
        this.TALUKA_CODE = TALUKA_CODE;
    }

    public String getTALUKA_NAME() {
        return TALUKA_NAME;
    }

    public void setTALUKA_NAME(String TALUKA_NAME) {
        this.TALUKA_NAME = TALUKA_NAME;
    }

    @NonNull
    @Override
    public String toString() {
        return TALUKA_NAME;
    }
}
