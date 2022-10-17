package com.bmc.suchane_svamitva.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddressCodeNoticeNoResponse {

    @SerializedName("RESPONSE_MESSAGE")
    @Expose
    private String RESPONSE_MESSAGE;

    @SerializedName("RESPONSE_CODE")
    @Expose
    private String RESPONSE_CODE;

    @SerializedName("NOTICE_NO")
    @Expose
    private String NOTICE_NO;

    @SerializedName("GPLUS_ADDRESS_CODE")
    @Expose
    private String GPLUS_ADDRESS_CODE;

    @SerializedName("VIRTUAL_ID")
    @Expose
    private String VIRTUAL_ID;

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

    public String getNOTICE_NO() {
        return NOTICE_NO;
    }

    public void setNOTICE_NO(String NOTICE_NO) {
        this.NOTICE_NO = NOTICE_NO;
    }

    public String getGPLUS_ADDRESS_CODE() {
        return GPLUS_ADDRESS_CODE;
    }

    public void setGPLUS_ADDRESS_CODE(String GPLUS_ADDRESS_CODE) {
        this.GPLUS_ADDRESS_CODE = GPLUS_ADDRESS_CODE;
    }

    public String getVIRTUAL_ID() {
        return VIRTUAL_ID;
    }

    public void setVIRTUAL_ID(String VIRTUAL_ID) {
        this.VIRTUAL_ID = VIRTUAL_ID;
    }
}
