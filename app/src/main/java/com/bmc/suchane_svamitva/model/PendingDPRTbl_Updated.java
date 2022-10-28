package com.bmc.suchane_svamitva.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class PendingDPRTbl_Updated {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_UPD")
    private int id_UPD;

    @ColumnInfo(name = "NTC_NOTICE_NO_UPD")
    @SerializedName("NTC_NOTICE_NO_UPD")
    @Expose
    private String NTC_NOTICE_NO_UPD;

    @ColumnInfo(name = "NTC_ADD_CODE_UPD")
    @SerializedName("NTC_ADD_CODE_UPD")
    @Expose
    private String NTC_ADD_CODE_UPD;

    @ColumnInfo(name = "NTC_UPD_FLAG")
    @SerializedName("NTC_UPD_FLAG")
    @Expose
    private int NTC_UPD_FLAG;

    public int getId_UPD() {
        return id_UPD;
    }

    public void setId_UPD(int id_UPD) {
        this.id_UPD = id_UPD;
    }

    public String getNTC_NOTICE_NO_UPD() {
        return NTC_NOTICE_NO_UPD;
    }

    public void setNTC_NOTICE_NO_UPD(String NTC_NOTICE_NO_UPD) {
        this.NTC_NOTICE_NO_UPD = NTC_NOTICE_NO_UPD;
    }

    public String getNTC_ADD_CODE_UPD() {
        return NTC_ADD_CODE_UPD;
    }

    public void setNTC_ADD_CODE_UPD(String NTC_ADD_CODE_UPD) {
        this.NTC_ADD_CODE_UPD = NTC_ADD_CODE_UPD;
    }

    public int getNTC_UPD_FLAG() {
        return NTC_UPD_FLAG;
    }

    public void setNTC_UPD_FLAG(int NTC_UPD_FLAG) {
        this.NTC_UPD_FLAG = NTC_UPD_FLAG;
    }
}
