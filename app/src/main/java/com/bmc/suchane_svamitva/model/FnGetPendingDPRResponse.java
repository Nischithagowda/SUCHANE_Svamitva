package com.bmc.suchane_svamitva.model;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FnGetPendingDPRResponse {
    @SerializedName("RESPONSE_MESSAGE")
    @Expose
    private String RESPONSE_MESSAGE;

    @SerializedName("RESPONSE_CODE")
    @Expose
    private String RESPONSE_CODE;

    @SerializedName("PENDING_DPR")
    @Expose
    private List<PendingDPRTbl> pendingDPRTblList;

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

    public List<PendingDPRTbl> getPendingDPRTblList() {
        return pendingDPRTblList;
    }

    public void setPendingDPRTblList(List<PendingDPRTbl> pendingDPRTblList) {
        this.pendingDPRTblList = pendingDPRTblList;
    }
}
