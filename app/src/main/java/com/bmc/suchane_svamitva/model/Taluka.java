package com.bmc.suchane_svamitva.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

public class Taluka {
    @ColumnInfo(name = "taluk_code")
    private String talukId;
    @ColumnInfo(name = "taluk_name")
    private String talukName;

    public String getTalukId() {
        return talukId;
    }

    public void setTalukId(String talukId) {
        this.talukId = talukId;
    }

    public String getTalukName() {
        return talukName;
    }

    public void setTalukName(String talukName) {
        this.talukName = talukName;
    }

    @NonNull
    @Override
    public String toString() {
        return talukName;
    }
}
