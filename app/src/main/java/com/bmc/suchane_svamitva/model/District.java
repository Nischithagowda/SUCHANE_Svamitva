package com.bmc.suchane_svamitva.model;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

public class District extends BaseObservable {

    @SerializedName("DistrictCode")
    @ColumnInfo(name = "DistrictCode")
    private String DISTRICT_CODE;

    @SerializedName("BhoomiDistrictName")
    @ColumnInfo(name = "BhoomiDistrictName")
    private String DISTRICT_NAME;

    public String getDISTRICT_CODE() {
        return DISTRICT_CODE;
    }

    public void setDISTRICT_CODE(String DISTRICT_CODE) {
        this.DISTRICT_CODE = DISTRICT_CODE;
    }

    public String getDISTRICT_NAME() {
        return DISTRICT_NAME;
    }

    public void setDISTRICT_NAME(String DISTRICT_NAME) {
        this.DISTRICT_NAME = DISTRICT_NAME;
    }

    @NonNull
    @Override
    public String toString() {
        return DISTRICT_NAME;
    }
}
