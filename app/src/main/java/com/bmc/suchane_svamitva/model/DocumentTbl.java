package com.bmc.suchane_svamitva.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DocumentTbl {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "DocumentID")
    private int DocumentID;

    @ColumnInfo(name = "NOTICE_NO")
    private String NOTICE_NO;

    @ColumnInfo(name = "property_no")
    private String Property_no;

    @ColumnInfo(name = "DocumentName")
    private String DocumentName;

    @ColumnInfo(name = "DocumentPath")
    private String DocumentPath;

    @ColumnInfo(name = "USER_ID")
    private String USER_ID;

    @ColumnInfo(name = "DOC_TIMESTAMP")
    private String DOC_TIMESTAMP;

    public int getDocumentID() {
        return DocumentID;
    }

    public void setDocumentID(int documentID) {
        DocumentID = documentID;
    }

    public String getNOTICE_NO() {
        return NOTICE_NO;
    }

    public void setNOTICE_NO(String NOTICE_NO) {
        this.NOTICE_NO = NOTICE_NO;
    }

    public String getProperty_no() {
        return Property_no;
    }

    public void setProperty_no(String property_no) {
        Property_no = property_no;
    }

    public String getDocumentName() {
        return DocumentName;
    }

    public void setDocumentName(String documentName) {
        DocumentName = documentName;
    }

    public String getDocumentPath() {
        return DocumentPath;
    }

    public void setDocumentPath(String documentPath) {
        DocumentPath = documentPath;
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

    @NonNull
    @Override
    public String toString() {
        return DocumentID + " " + DocumentName;
    }
}
