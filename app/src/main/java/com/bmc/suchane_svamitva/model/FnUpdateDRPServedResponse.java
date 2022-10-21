package com.bmc.suchane_svamitva.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FnUpdateDRPServedResponse {

    @SerializedName("RESPONSE_MESSAGE")
    @Expose
    private String RESPONSE_MESSAGE;

    @SerializedName("RESPONSE_CODE")
    @Expose
    private String RESPONSE_CODE;

    @SerializedName("RESPONSE_NOTICE_NO")
    @Expose
    private String RESPONSE_NOTICE_NO;

    @SerializedName("RESPONSE_PROPERTY_CODE")
    @Expose
    private String RESPONSE_PROPERTY_CODE;

    @SerializedName("RESPONSE_ADDRESS_CODE")
    @Expose
    private String RESPONSE_ADDRESS_CODE;

    public String getRESPONSE_MESSAGE() {
        return RESPONSE_MESSAGE;
    }

    public void setRESPONSE_MESSAGE(String RESPONSE_MESSAGE) {
        this.RESPONSE_MESSAGE = RESPONSE_MESSAGE;
    }

    public String getRESPONSE_CODE() {
        return RESPONSE_CODE;
    }

    public void setRESPONSE_CODE(String RESPONSE_CODE) {
        this.RESPONSE_CODE = RESPONSE_CODE;
    }

    public String getRESPONSE_NOTICE_NO() {
        return RESPONSE_NOTICE_NO;
    }

    public void setRESPONSE_NOTICE_NO(String RESPONSE_NOTICE_NO) {
        this.RESPONSE_NOTICE_NO = RESPONSE_NOTICE_NO;
    }

    public String getRESPONSE_PROPERTY_CODE() {
        return RESPONSE_PROPERTY_CODE;
    }

    public void setRESPONSE_PROPERTY_CODE(String RESPONSE_PROPERTY_CODE) {
        this.RESPONSE_PROPERTY_CODE = RESPONSE_PROPERTY_CODE;
    }

    public String getRESPONSE_ADDRESS_CODE() {
        return RESPONSE_ADDRESS_CODE;
    }

    public void setRESPONSE_ADDRESS_CODE(String RESPONSE_ADDRESS_CODE) {
        this.RESPONSE_ADDRESS_CODE = RESPONSE_ADDRESS_CODE;
    }
}
