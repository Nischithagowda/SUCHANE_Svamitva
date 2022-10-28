package com.bmc.suchane_svamitva.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

public class OwnerTbl {
    @ColumnInfo(name = "NOTICE_NO")
    private long NOTICE_NO;

    @ColumnInfo(name = "property_no")
    private String Property_no;

//    @ColumnInfo(name = "Municipal_GPFD")
//    private String Municipal_GPFD;

    @ColumnInfo(name = "Owner_Name")
    private String Owner_Name;

    public long getNOTICE_NO() {
        return NOTICE_NO;
    }

    public void setNOTICE_NO(long NOTICE_NO) {
        this.NOTICE_NO = NOTICE_NO;
    }

    public String getProperty_no() {
        return Property_no;
    }

    public void setProperty_no(String property_no) {
        Property_no = property_no;
    }

//    public String getMunicipal_GPFD() {
//        return Municipal_GPFD;
//    }
//
//    public void setMunicipal_GPFD(String municipal_GPFD) {
//        Municipal_GPFD = municipal_GPFD;
//    }

    public String getOwner_Name() {
        return Owner_Name;
    }

    public void setOwner_Name(String owner_Name) {
        Owner_Name = owner_Name;
    }

    @NonNull
    @Override
    public String toString() {
        return NOTICE_NO + " " + Property_no + " " + Owner_Name;
    }

}
