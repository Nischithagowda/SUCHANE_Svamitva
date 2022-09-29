package com.bmc.suchane_svamitva.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValidateOtpResponse {
    @SerializedName("status")
    @Expose
    private boolean Status;

    @SerializedName("statusCode")
    @Expose
    private int StatusCode;

    @SerializedName("exceptionMessage")
    @Expose
    private String ExceptionMessage;

    @SerializedName("messageToDisplay")
    @Expose
    private String MessageToDisplay;

    @SerializedName("useR_MOBILE")
    @Expose
    private String USER_MOBILE;

    @SerializedName("useR_Name")
    @Expose
    private String USER_Name;

    @SerializedName("useR_EMail")
    @Expose
    private String USER_EMail;

    @SerializedName("useR_DesignationID")
    @Expose
    private String USER_DesignationID;

    @SerializedName("useR_DistrictCode")
    @Expose
    private String USER_DistrictCode;

    @SerializedName("useR_TalukCode")
    @Expose
    private String USER_TalukCode;

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public String getExceptionMessage() {
        return ExceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        ExceptionMessage = exceptionMessage;
    }

    public String getMessageToDisplay() {
        return MessageToDisplay;
    }

    public void setMessageToDisplay(String messageToDisplay) {
        MessageToDisplay = messageToDisplay;
    }

    public String getUSER_MOBILE() {
        return USER_MOBILE;
    }

    public void setUSER_MOBILE(String USER_MOBILE) {
        this.USER_MOBILE = USER_MOBILE;
    }

    public String getUSER_Name() {
        return USER_Name;
    }

    public void setUSER_Name(String USER_Name) {
        this.USER_Name = USER_Name;
    }

    public String getUSER_EMail() {
        return USER_EMail;
    }

    public void setUSER_EMail(String USER_EMail) {
        this.USER_EMail = USER_EMail;
    }

    public String getUSER_DesignationID() {
        return USER_DesignationID;
    }

    public void setUSER_DesignationID(String USER_DesignationID) {
        this.USER_DesignationID = USER_DesignationID;
    }

    public String getUSER_DistrictCode() {
        return USER_DistrictCode;
    }

    public void setUSER_DistrictCode(String USER_DistrictCode) {
        this.USER_DistrictCode = USER_DistrictCode;
    }

    public String getUSER_TalukCode() {
        return USER_TalukCode;
    }

    public void setUSER_TalukCode(String USER_TalukCode) {
        this.USER_TalukCode = USER_TalukCode;
    }
}
