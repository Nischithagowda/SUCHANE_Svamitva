package com.bmc.suchane_svamitva.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class District {

    @SerializedName("district_code")
    @ColumnInfo(name = "district_code")
    private String districtId;
    @SerializedName("DISTRICT_NAME")
    @ColumnInfo(name = "district_name")
    private String districtName;

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    @NonNull
    @Override
    public String toString() {
        return districtName;
    }
}
