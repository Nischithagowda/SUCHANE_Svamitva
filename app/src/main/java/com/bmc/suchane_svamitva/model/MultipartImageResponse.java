package com.bmc.suchane_svamitva.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MultipartImageResponse {

    @SerializedName("status_code")
    @Expose
    private int statusCode;

    @SerializedName("status_messaage")
    @Expose
    private String statusMessaage;

    @SerializedName("DOC_Hamlet_Code")
    @Expose
    private int DOC_Hamlet_Code;

    @SerializedName("DOC_Property_No")
    @Expose
    private int DOC_Property_No;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessaage() {
        return statusMessaage;
    }

    public void setStatusMessaage(String statusMessaage) {
        this.statusMessaage = statusMessaage;
    }

    public int getDOC_Hamlet_Code() {
        return DOC_Hamlet_Code;
    }

    public void setDOC_Hamlet_Code(int DOC_Hamlet_Code) {
        this.DOC_Hamlet_Code = DOC_Hamlet_Code;
    }

    public int getDOC_Property_No() {
        return DOC_Property_No;
    }

    public void setDOC_Property_No(int DOC_Property_No) {
        this.DOC_Property_No = DOC_Property_No;
    }
}
