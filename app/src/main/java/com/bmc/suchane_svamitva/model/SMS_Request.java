package com.bmc.suchane_svamitva.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SMS_Request {
    @SerializedName("MobileNumberToSendOTP")
    @Expose
    private String MobileNumberToSendOTP;

    public String getMobileNumberToSendOTP() {
        return MobileNumberToSendOTP;
    }

    public void setMobileNumberToSendOTP(String mobileNumberToSendOTP) {
        MobileNumberToSendOTP = mobileNumberToSendOTP;
    }
}
