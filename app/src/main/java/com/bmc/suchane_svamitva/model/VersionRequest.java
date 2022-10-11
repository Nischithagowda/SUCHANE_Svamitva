package com.bmc.suchane_svamitva.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VersionRequest {
    @SerializedName("VERSION_CODE")
    @Expose
    private String VERSION_CODE;

    public String getVERSION_CODE() {
        return VERSION_CODE;
    }

    public void setVERSION_CODE(String VERSION_CODE) {
        this.VERSION_CODE = VERSION_CODE;
    }
}
