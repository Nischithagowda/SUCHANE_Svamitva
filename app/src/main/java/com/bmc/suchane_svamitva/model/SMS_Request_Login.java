package com.bmc.suchane_svamitva.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SMS_Request_Login {
    @SerializedName("MobileNumberToSendOTP")
    @Expose
    private String MobileNumberToSendOTP;

    @SerializedName("DeviceId")
    @Expose
    private String DeviceId;

    public String getMobileNumberToSendOTP() {
        return MobileNumberToSendOTP;
    }

    public void setMobileNumberToSendOTP(String mobileNumberToSendOTP) {
        MobileNumberToSendOTP = mobileNumberToSendOTP;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }
}
