package com.bmc.suchane_svamitva.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

public class Hobli {

    @ColumnInfo(name = "BhoomiDistrictCode")
    private String BhoomiDistrictCode;
    @ColumnInfo(name = "BhoomiTalukCode")
    private String BhoomiTalukCode;
    @ColumnInfo(name = "BhoomiHobliCode")
    private String BhoomiHobliCode;
    @ColumnInfo(name = "BhoomiHobliName")
    private String BhoomiHobliName;

    public String getBhoomiDistrictCode() {
        return BhoomiDistrictCode;
    }

    public void setBhoomiDistrictCode(String bhoomiDistrictCode) {
        BhoomiDistrictCode = bhoomiDistrictCode;
    }

    public String getBhoomiTalukCode() {
        return BhoomiTalukCode;
    }

    public void setBhoomiTalukCode(String bhoomiTalukCode) {
        BhoomiTalukCode = bhoomiTalukCode;
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

    @NonNull
    @Override
    public String toString() {
        return BhoomiHobliName;
    }
}
