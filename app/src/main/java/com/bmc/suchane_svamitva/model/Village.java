package com.bmc.suchane_svamitva.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

public class Village {

    @ColumnInfo(name = "districtCode")
    private String districtCode;
    @ColumnInfo(name = "talukCode")
    private String talukCode;
    @ColumnInfo(name = "hobliCode")
    private String hobliCode;
    @ColumnInfo(name = "villageCode")
    private String villageCode;
    @ColumnInfo(name = "bhoomiVillageName")
    private String bhoomiVillageName;

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getTalukCode() {
        return talukCode;
    }

    public void setTalukCode(String talukCode) {
        this.talukCode = talukCode;
    }

    public String getHobliCode() {
        return hobliCode;
    }

    public void setHobliCode(String hobliCode) {
        this.hobliCode = hobliCode;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public String getBhoomiVillageName() {
        return bhoomiVillageName;
    }

    public void setBhoomiVillageName(String bhoomiVillageName) {
        this.bhoomiVillageName = bhoomiVillageName;
    }

    @NonNull
    @Override
    public String toString() {
        return bhoomiVillageName;
    }
}
