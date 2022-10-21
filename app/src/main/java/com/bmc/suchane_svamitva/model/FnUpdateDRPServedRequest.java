package com.bmc.suchane_svamitva.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FnUpdateDRPServedRequest {

    @SerializedName("NOTICE_NO")
    @Expose
    private String NOTICE_NO;

    @SerializedName("PROPERTY_CODE")
    @Expose
    private String PROPERTY_CODE;

    @SerializedName("ADDRESS_CODE")
    @Expose
    private String ADDRESS_CODE;

    @SerializedName("USER_ID")
    @Expose
    private String USER_ID;

    public String getNOTICE_NO() {
        return NOTICE_NO;
    }

    public void setNOTICE_NO(String NOTICE_NO) {
        this.NOTICE_NO = NOTICE_NO;
    }

    public String getPROPERTY_CODE() {
        return PROPERTY_CODE;
    }

    public void setPROPERTY_CODE(String PROPERTY_CODE) {
        this.PROPERTY_CODE = PROPERTY_CODE;
    }

    public String getADDRESS_CODE() {
        return ADDRESS_CODE;
    }

    public void setADDRESS_CODE(String ADDRESS_CODE) {
        this.ADDRESS_CODE = ADDRESS_CODE;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }
}
