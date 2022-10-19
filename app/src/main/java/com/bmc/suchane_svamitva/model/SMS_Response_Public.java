package com.bmc.suchane_svamitva.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SMS_Response_Public {

    @SerializedName("RESPONSE_MESSAGE")
    @Expose
    private String RESPONSE_MESSAGE;

    @SerializedName("RESPONSE_MOBILE_NUMBER")
    @Expose
    private String RESPONSE_MOBILE_NUMBER;

    @SerializedName("RESPONSE_CODE")
    @Expose
    private String RESPONSE_CODE;

    public String getRESPONSE_MESSAGE() {
        return RESPONSE_MESSAGE;
    }

    public void setRESPONSE_MESSAGE(String RESPONSE_MESSAGE) {
        this.RESPONSE_MESSAGE = RESPONSE_MESSAGE;
    }

    public String getRESPONSE_MOBILE_NUMBER() {
        return RESPONSE_MOBILE_NUMBER;
    }

    public void setRESPONSE_MOBILE_NUMBER(String RESPONSE_MOBILE_NUMBER) {
        this.RESPONSE_MOBILE_NUMBER = RESPONSE_MOBILE_NUMBER;
    }

    public String getRESPONSE_CODE() {
        return RESPONSE_CODE;
    }

    public void setRESPONSE_CODE(String RESPONSE_CODE) {
        this.RESPONSE_CODE = RESPONSE_CODE;
    }
}
