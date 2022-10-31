package com.bmc.suchane_svamitva.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

public class OwnerTbl {
    @ColumnInfo(name = "NOTICE_NO")
    private String NOTICE_NO;

    @ColumnInfo(name = "property_no")
    private String Property_no;

    @ColumnInfo(name = "Owner_Name")
    private String Owner_Name;

    public String getNOTICE_NO() {
        return NOTICE_NO;
    }

    public void setNOTICE_NO(String NOTICE_NO) {
        this.NOTICE_NO = NOTICE_NO;
    }

    public String getProperty_no() {
        return Property_no;
    }

    public void setProperty_no(String property_no) {
        Property_no = property_no;
    }

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
