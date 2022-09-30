package com.bmc.suchane_svamitva.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VillageRequest {
    @SerializedName("DISTRICT_CODE")
    @Expose
    private String DISTRICT_CODE;

    @SerializedName("TALUK_CODE")
    @Expose
    private String TALUK_CODE;

    @SerializedName("HOBLI_CODE")
    @Expose
    private String HOBLI_CODE;

    public String getDISTRICT_CODE() {
        return DISTRICT_CODE;
    }

    public void setDISTRICT_CODE(String DISTRICT_CODE) {
        this.DISTRICT_CODE = DISTRICT_CODE;
    }

    public String getTALUK_CODE() {
        return TALUK_CODE;
    }

    public void setTALUK_CODE(String TALUK_CODE) {
        this.TALUK_CODE = TALUK_CODE;
    }

    public String getHOBLI_CODE() {
        return HOBLI_CODE;
    }

    public void setHOBLI_CODE(String HOBLI_CODE) {
        this.HOBLI_CODE = HOBLI_CODE;
    }
}
