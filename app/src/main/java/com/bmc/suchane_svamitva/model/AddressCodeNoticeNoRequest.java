package com.bmc.suchane_svamitva.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddressCodeNoticeNoRequest {
    @SerializedName("LAT")
    @Expose
    private String LAT;

    @SerializedName("LONG")
    @Expose
    private String LONG;

    public String getLAT() {
        return LAT;
    }

    public void setLAT(String LAT) {
        this.LAT = LAT;
    }

    public String getLONG() {
        return LONG;
    }

    public void setLONG(String LONG) {
        this.LONG = LONG;
    }
}
