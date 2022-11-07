package com.bmc.suchane_svamitva.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Image {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "NTC_PROPERTYCODE")
    private String NTC_PROPERTYCODE;

    @ColumnInfo(name = "NOTICE_NO")
    private String NOTICE_NO;

    @ColumnInfo(name = "ADDRESS_CODE")
    private String ADDRESS_CODE;

    @ColumnInfo(name = "DOC_TYPE_ID")
    private String DOC_TYPE_ID;

    @ColumnInfo(name = "DOC_NAME")
    private String DOC_NAME;

    @ColumnInfo(name = "DOC_PATH")
    private String DOC_PATH;

    @ColumnInfo(name = "USER_ID")
    private String USER_ID;

    @ColumnInfo(name = "DOC_TIMESTAMP")
    private String DOC_TIMESTAMP;

    @ColumnInfo(name = "is_not_sent")
    private boolean isNotSent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNTC_PROPERTYCODE() {
        return NTC_PROPERTYCODE;
    }

    public void setNTC_PROPERTYCODE(String NTC_PROPERTYCODE) {
        this.NTC_PROPERTYCODE = NTC_PROPERTYCODE;
    }

    public String getNOTICE_NO() {
        return NOTICE_NO;
    }

    public void setNOTICE_NO(String NOTICE_NO) {
        this.NOTICE_NO = NOTICE_NO;
    }

    public String getADDRESS_CODE() {
        return ADDRESS_CODE;
    }

    public void setADDRESS_CODE(String ADDRESS_CODE) {
        this.ADDRESS_CODE = ADDRESS_CODE;
    }

    public String getDOC_TYPE_ID() {
        return DOC_TYPE_ID;
    }

    public void setDOC_TYPE_ID(String DOC_TYPE_ID) {
        this.DOC_TYPE_ID = DOC_TYPE_ID;
    }

    public String getDOC_NAME() {
        return DOC_NAME;
    }

    public void setDOC_NAME(String DOC_NAME) {
        this.DOC_NAME = DOC_NAME;
    }

    public String getDOC_PATH() {
        return DOC_PATH;
    }

    public void setDOC_PATH(String DOC_PATH) {
        this.DOC_PATH = DOC_PATH;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getDOC_TIMESTAMP() {
        return DOC_TIMESTAMP;
    }

    public void setDOC_TIMESTAMP(String DOC_TIMESTAMP) {
        this.DOC_TIMESTAMP = DOC_TIMESTAMP;
    }

    public boolean isNotSent() {
        return isNotSent;
    }

    public void setNotSent(boolean notSent) {
        isNotSent = notSent;
    }
}
