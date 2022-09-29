package com.bmc.suchane_svamitva.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValidateOtpRequest {
    @SerializedName("OTP")
    @Expose
    private int oTP;
    @SerializedName("MobileNo")
    @Expose
    private String mobileNo;

    public int getOTP() {
        return oTP;
    }

    public void setOTP(int oTP) {
        this.oTP = oTP;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

}

