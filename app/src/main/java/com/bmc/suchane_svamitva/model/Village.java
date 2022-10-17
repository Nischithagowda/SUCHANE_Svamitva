package com.bmc.suchane_svamitva.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Village {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @SerializedName("DISTRICT_CODE")
    @Expose
    private String DISTRICT_CODE;

    @SerializedName("TALUK_CODE")
    @Expose
    private String TALUK_CODE;

    @SerializedName("HOBLI_CODE")
    @ColumnInfo(name = "HOBLI_CODE")
    private String HOBLI_CODE;

    @SerializedName("VILLAGE_CODE")
    @ColumnInfo(name = "VILLAGE_CODE")
    private String VILLAGE_CODE;

    @SerializedName("LGD_VILLAGE_CODE")
    @ColumnInfo(name = "LGD_VILLAGE_CODE")
    private String LGD_VILLAGE_CODE;

    @SerializedName("VILLAGE_NAME")
    @ColumnInfo(name = "VILLAGE_NAME")
    private String VILLAGE_NAME;

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

    public String getTALUK_CODE() {
        return TALUK_CODE;
    }

    public void setTALUK_CODE(String TALUK_CODE) {
        this.TALUK_CODE = TALUK_CODE;
    }

    public String getHOBLI_CODE() {
        return HOBLI_CODE;
    }

    public void setHOBLI_CODE(String HOBLI_CODE) {
        this.HOBLI_CODE = HOBLI_CODE;
    }

    public String getLGD_VILLAGE_CODE() {
        return LGD_VILLAGE_CODE;
    }

    public void setLGD_VILLAGE_CODE(String LGD_VILLAGE_CODE) {
        this.LGD_VILLAGE_CODE = LGD_VILLAGE_CODE;
    }

    public String getVILLAGE_CODE() {
        return VILLAGE_CODE;
    }

    public void setVILLAGE_CODE(String VILLAGE_CODE) {
        this.VILLAGE_CODE = VILLAGE_CODE;
    }

    public String getVILLAGE_NAME() {
        return VILLAGE_NAME;
    }

    public void setVILLAGE_NAME(String VILLAGE_NAME) {
        this.VILLAGE_NAME = VILLAGE_NAME;
    }

    @NonNull
    @Override
    public String toString() {
        return VILLAGE_NAME;
    }
}
