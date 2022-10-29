package com.bmc.suchane_svamitva.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FnCheckUserLoggedInRequest {
    @SerializedName("MOBILE_NO")
    @Expose
    private String MOBILE_NO;

    @SerializedName("DEVICE_ID")
    @Expose
    private String DEVICE_ID;

    public String getMOBILE_NO() {
        return MOBILE_NO;
    }

    public void setMOBILE_NO(String MOBILE_NO) {
        this.MOBILE_NO = MOBILE_NO;
    }

    public String getDEVICE_ID() {
        return DEVICE_ID;
    }

    public void setDEVICE_ID(String DEVICE_ID) {
        this.DEVICE_ID = DEVICE_ID;
    }
}
