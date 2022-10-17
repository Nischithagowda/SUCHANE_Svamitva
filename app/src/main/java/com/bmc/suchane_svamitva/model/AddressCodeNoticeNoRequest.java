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

    @SerializedName("LGD_VILLAGECODE")
    @Expose
    private String LGD_VILLAGECODE;

    @SerializedName("USER_ID")
    @Expose
    private String USER_ID;

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

    public String getLGD_VILLAGECODE() {
        return LGD_VILLAGECODE;
    }

    public void setLGD_VILLAGECODE(String LGD_VILLAGECODE) {
        this.LGD_VILLAGECODE = LGD_VILLAGECODE;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }
}
