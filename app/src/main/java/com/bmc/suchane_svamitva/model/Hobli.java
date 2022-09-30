package com.bmc.suchane_svamitva.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Hobli {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @SerializedName("DISTRICT_CODE")
    @ColumnInfo(name = "DISTRICT_CODE")
    private String DISTRICT_CODE;

    @SerializedName("TALUKA_CODE")
    @ColumnInfo(name = "TALUKA_CODE")
    private String TALUKA_CODE;

    @SerializedName("HOBLI_CODE")
    @ColumnInfo(name = "HOBLI_CODE")
    private String HOBLI_CODE;

    @SerializedName("HOBLI_NAME")
    @ColumnInfo(name = "HOBLI_NAME")
    private String HOBLI_NAME;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDISTRICT_CODE() {
        return DISTRICT_CODE;
    }

    public void setDISTRICT_CODE(String DISTRICT_CODE) {
        this.DISTRICT_CODE = DISTRICT_CODE;
    }

    public String getTALUKA_CODE() {
        return TALUKA_CODE;
    }

    public void setTALUKA_CODE(String TALUKA_CODE) {
        this.TALUKA_CODE = TALUKA_CODE;
    }

    public String getHOBLI_CODE() {
        return HOBLI_CODE;
    }

    public void setHOBLI_CODE(String HOBLI_CODE) {
        this.HOBLI_CODE = HOBLI_CODE;
    }

    public String getHOBLI_NAME() {
        return HOBLI_NAME;
    }

    public void setHOBLI_NAME(String HOBLI_NAME) {
        this.HOBLI_NAME = HOBLI_NAME;
    }

    @NonNull
    @Override
    public String toString() {
        return HOBLI_NAME;
    }
}
