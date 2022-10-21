package com.bmc.suchane_svamitva.model;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FnGetPendingDPRRequest {
    @SerializedName("LGD_VILLAGECODE")
    @Expose
    private String LGD_VILLAGECODE;

    public String getLGD_VILLAGECODE() {
        return LGD_VILLAGECODE;
    }

    public void setLGD_VILLAGECODE(String LGD_VILLAGECODE) {
        this.LGD_VILLAGECODE = LGD_VILLAGECODE;
    }
}
