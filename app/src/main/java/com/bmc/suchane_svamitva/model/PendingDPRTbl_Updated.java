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

    @ColumnInfo(name = "NOTICE_NO")
    @SerializedName("NOTICE_NO")
    @Expose
    private String NOTICE_NO;

    @ColumnInfo(name = "PROPERTY_CODE")
    @SerializedName("PROPERTY_CODE")
    @Expose
    private String PROPERTY_CODE;

    @ColumnInfo(name = "ADDRESS_CODE")
    @SerializedName("ADDRESS_CODE")
    @Expose
    private String ADDRESS_CODE;

    @ColumnInfo(name = "USER_ID")
    @SerializedName("USER_ID")
    @Expose
    private String USER_ID;

    @ColumnInfo(name = "UPD_FLAG")
    @SerializedName("UPD_FLAG")
    @Expose
    private int UPD_FLAG;

    public int getId_UPD() {
        return id_UPD;
    }

    public void setId_UPD(int id_UPD) {
        this.id_UPD = id_UPD;
    }

    public String getNOTICE_NO() {
        return NOTICE_NO;
    }

    public void setNOTICE_NO(String NOTICE_NO) {
        this.NOTICE_NO = NOTICE_NO;
    }

    public String getPROPERTY_CODE() {
        return PROPERTY_CODE;
    }

    public void setPROPERTY_CODE(String PROPERTY_CODE) {
        this.PROPERTY_CODE = PROPERTY_CODE;
    }

    public String getADDRESS_CODE() {
        return ADDRESS_CODE;
    }

    public void setADDRESS_CODE(String ADDRESS_CODE) {
        this.ADDRESS_CODE = ADDRESS_CODE;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public int getUPD_FLAG() {
        return UPD_FLAG;
    }

    public void setUPD_FLAG(int UPD_FLAG) {
        this.UPD_FLAG = UPD_FLAG;
    }
}
