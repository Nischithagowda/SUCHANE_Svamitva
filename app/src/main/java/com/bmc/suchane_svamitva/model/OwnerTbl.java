package com.bmc.suchane_svamitva.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

public class OwnerTbl {
    @ColumnInfo(name = "owner_id")
    private long owner_id;

    @ColumnInfo(name = "chalta_No")
    private String chalta_No;

    @ColumnInfo(name = "Municipal_GPFD")
    private String Municipal_GPFD;

    @ColumnInfo(name = "Owner_Name")
    private String Owner_Name;

    public long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }

    public String getChalta_No() {
        return chalta_No;
    }

    public void setChalta_No(String chalta_No) {
        this.chalta_No = chalta_No;
    }

    public String getMunicipal_GPFD() {
        return Municipal_GPFD;
    }

    public void setMunicipal_GPFD(String municipal_GPFD) {
        Municipal_GPFD = municipal_GPFD;
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
        return owner_id + " " + chalta_No + " " + Municipal_GPFD+" "+Owner_Name;
    }

}
