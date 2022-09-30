package com.bmc.suchane_svamitva.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SMS_Response implements Serializable {

    @SerializedName("RESPONSE_STATUS")
    @Expose
    private String RESPONSE_STATUS;

    @SerializedName("RESPONSE_MESSAGE")
    @Expose
    private String RESPONSE_MESSAGE;

    @SerializedName("RESPONSE_MOBILE_NUMBER")
    @Expose
    private String RESPONSE_MOBILE_NUMBER;

    @SerializedName("RESPONSE_CODE")
    @Expose
    private String RESPONSE_CODE;

    @SerializedName("USER_DETAILS")
    @Expose
    private List<USER_DETAILS> userDetailsList;

    public String getRESPONSE_STATUS() {
        return RESPONSE_STATUS;
    }

    public void setRESPONSE_STATUS(String RESPONSE_STATUS) {
        this.RESPONSE_STATUS = RESPONSE_STATUS;
    }

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

    public List<USER_DETAILS> getUserDetailsList() {
        return userDetailsList;
    }

    public void setUserDetailsList(List<USER_DETAILS> userDetailsList) {
        this.userDetailsList = userDetailsList;
    }
}
