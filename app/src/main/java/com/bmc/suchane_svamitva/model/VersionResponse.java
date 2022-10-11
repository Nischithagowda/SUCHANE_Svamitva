package com.bmc.suchane_svamitva.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VersionResponse {

    @SerializedName("RESPONSE_MESSAGE")
    @Expose
    private String RESPONSE_MESSAGE;

    @SerializedName("RESPONSE_CODE")
    @Expose
    private String RESPONSE_CODE;

    @SerializedName("VERSION_CODE")
    @Expose
    private String VERSION_CODE;

    @SerializedName("DISTANCE_RANGE")
    @Expose
    private String DISTANCE_RANGE;

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

    public String getVERSION_CODE() {
        return VERSION_CODE;
    }

    public void setVERSION_CODE(String VERSION_CODE) {
        this.VERSION_CODE = VERSION_CODE;
    }

    public String getDISTANCE_RANGE() {
        return DISTANCE_RANGE;
    }

    public void setDISTANCE_RANGE(String DISTANCE_RANGE) {
        this.DISTANCE_RANGE = DISTANCE_RANGE;
    }
}
