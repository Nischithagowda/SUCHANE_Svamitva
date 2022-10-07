package com.bmc.suchane_svamitva.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MultipartImageResponse {

    @SerializedName("RESPONSE_MESSAGE")
    @Expose
    private String RESPONSE_MESSAGE;

    @SerializedName("RESPONSE_CODE")
    @Expose
    private String RESPONSE_CODE;

    @SerializedName("RESPONSE_REF_ID")
    @Expose
    private String RESPONSE_REF_ID;

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

    public String getRESPONSE_REF_ID() {
        return RESPONSE_REF_ID;
    }

    public void setRESPONSE_REF_ID(String RESPONSE_REF_ID) {
        this.RESPONSE_REF_ID = RESPONSE_REF_ID;
    }
}
