package com.bmc.suchane_svamitva.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class ApprovedDPRTbl {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "NTC_NOTICE_NO")
    @SerializedName("NTC_NOTICE_NO")
    @Expose
    private String NTC_NOTICE_NO;

    @ColumnInfo(name = "NTC_ADD_CODE")
    @SerializedName("NTC_ADD_CODE")
    @Expose
    private String NTC_ADD_CODE;

    @ColumnInfo(name = "NTC_OWNER_NAME")
    @SerializedName("NTC_OWNER_NAME")
    @Expose
    private String NTC_OWNER_NAME;

    @ColumnInfo(name = "NTC_OWNER_MOBILE")
    @SerializedName("NTC_OWNER_MOBILE")
    @Expose
    private String NTC_OWNER_MOBILE;

    @ColumnInfo(name = "NTC_ADD_DOORNO")
    @SerializedName("NTC_ADD_DOORNO")
    @Expose
    private String NTC_ADD_DOORNO;

    @ColumnInfo(name = "NTC_BUILDING")
    @SerializedName("NTC_BUILDING")
    @Expose
    private String NTC_BUILDING;

    @ColumnInfo(name = "NTC_STREET_AREA")
    @SerializedName("NTC_STREET_AREA")
    @Expose
    private String NTC_STREET_AREA;

    @ColumnInfo(name = "NTC_LANDMARK")
    @SerializedName("NTC_LANDMARK")
    @Expose
    private String NTC_LANDMARK;

    @ColumnInfo(name = "NTC_WARD_VILLAGE")
    @SerializedName("NTC_WARD_VILLAGE")
    @Expose
    private String NTC_WARD_VILLAGE;

    @ColumnInfo(name = "NTC_CTY_TLK")
    @SerializedName("NTC_CTY_TLK")
    @Expose
    private String NTC_CTY_TLK;

    @ColumnInfo(name = "NTC_DISTRICT")
    @SerializedName("NTC_DISTRICT")
    @Expose
    private String NTC_DISTRICT;

    @ColumnInfo(name = "NTC_LAT")
    @SerializedName("NTC_LAT")
    @Expose
    private String NTC_LAT;

    @ColumnInfo(name = "NTC_LONG")
    @SerializedName("NTC_LONG")
    @Expose
    private String NTC_LONG;

    @ColumnInfo(name = "DPRFNL_PROPERTYCODE")
    @SerializedName("DPRFNL_PROPERTYCODE")
    @Expose
    private String DPRFNL_PROPERTYCODE;

    @ColumnInfo(name = "DPRFNL_NOTICE_NO")
    @SerializedName("DPRFNL_NOTICE_NO")
    @Expose
    private String DPRFNL_NOTICE_NO;

    @ColumnInfo(name = "DPRFNL_ADD_CODE")
    @SerializedName("DPRFNL_ADD_CODE")
    @Expose
    private String DPRFNL_ADD_CODE;

    @ColumnInfo(name = "DPRFNL_PROPERTYNO")
    @SerializedName("DPRFNL_PROPERTYNO")
    @Expose
    private String DPRFNL_PROPERTYNO;

    @ColumnInfo(name = "DPRFNL_PROPERTYID")
    @SerializedName("DPRFNL_PROPERTYID")
    @Expose
    private String DPRFNL_PROPERTYID;

    @ColumnInfo(name = "DPRFNL_REMARKS")
    @SerializedName("DPRFNL_REMARKS")
    @Expose
    private String DPRFNL_REMARKS;

    @ColumnInfo(name = "DPRFNL_DPRDATE")
    @SerializedName("DPRFNL_DPRDATE")
    @Expose
    private String DPRFNL_DPRDATE;

    @ColumnInfo(name = "DPROWNFNL_OWNERNAME")
    @SerializedName("DPROWNFNL_OWNERNAME")
    @Expose
    private String DPROWNFNL_OWNERNAME;

    @ColumnInfo(name = "DPROWNFNL_RELATIONNAME")
    @SerializedName("DPROWNFNL_RELATIONNAME")
    @Expose
    private String DPROWNFNL_RELATIONNAME;

    @ColumnInfo(name = "DPROWNFNL_MRNO")
    @SerializedName("DPROWNFNL_MRNO")
    @Expose
    private String DPROWNFNL_MRNO;

    @ColumnInfo(name = "DPROWNFNL_MOBILENO")
    @SerializedName("DPROWNFNL_MOBILENO")
    @Expose
    private String DPROWNFNL_MOBILENO;

    @ColumnInfo(name = "DPROWNFNL_VLGDCODE")
    @SerializedName("DPROWNFNL_VLGDCODE")
    @Expose
    private String DPROWNFNL_VLGDCODE;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNTC_NOTICE_NO() {
        return NTC_NOTICE_NO;
    }

    public void setNTC_NOTICE_NO(String NTC_NOTICE_NO) {
        this.NTC_NOTICE_NO = NTC_NOTICE_NO;
    }

    public String getNTC_ADD_CODE() {
        return NTC_ADD_CODE;
    }

    public void setNTC_ADD_CODE(String NTC_ADD_CODE) {
        this.NTC_ADD_CODE = NTC_ADD_CODE;
    }

    public String getNTC_OWNER_NAME() {
        return NTC_OWNER_NAME;
    }

    public void setNTC_OWNER_NAME(String NTC_OWNER_NAME) {
        this.NTC_OWNER_NAME = NTC_OWNER_NAME;
    }

    public String getNTC_OWNER_MOBILE() {
        return NTC_OWNER_MOBILE;
    }

    public void setNTC_OWNER_MOBILE(String NTC_OWNER_MOBILE) {
        this.NTC_OWNER_MOBILE = NTC_OWNER_MOBILE;
    }

    public String getNTC_ADD_DOORNO() {
        return NTC_ADD_DOORNO;
    }

    public void setNTC_ADD_DOORNO(String NTC_ADD_DOORNO) {
        this.NTC_ADD_DOORNO = NTC_ADD_DOORNO;
    }

    public String getNTC_BUILDING() {
        return NTC_BUILDING;
    }

    public void setNTC_BUILDING(String NTC_BUILDING) {
        this.NTC_BUILDING = NTC_BUILDING;
    }

    public String getNTC_STREET_AREA() {
        return NTC_STREET_AREA;
    }

    public void setNTC_STREET_AREA(String NTC_STREET_AREA) {
        this.NTC_STREET_AREA = NTC_STREET_AREA;
    }

    public String getNTC_LANDMARK() {
        return NTC_LANDMARK;
    }

    public void setNTC_LANDMARK(String NTC_LANDMARK) {
        this.NTC_LANDMARK = NTC_LANDMARK;
    }

    public String getNTC_WARD_VILLAGE() {
        return NTC_WARD_VILLAGE;
    }

    public void setNTC_WARD_VILLAGE(String NTC_WARD_VILLAGE) {
        this.NTC_WARD_VILLAGE = NTC_WARD_VILLAGE;
    }

    public String getNTC_CTY_TLK() {
        return NTC_CTY_TLK;
    }

    public void setNTC_CTY_TLK(String NTC_CTY_TLK) {
        this.NTC_CTY_TLK = NTC_CTY_TLK;
    }

    public String getNTC_DISTRICT() {
        return NTC_DISTRICT;
    }

    public void setNTC_DISTRICT(String NTC_DISTRICT) {
        this.NTC_DISTRICT = NTC_DISTRICT;
    }

    public String getNTC_LAT() {
        return NTC_LAT;
    }

    public void setNTC_LAT(String NTC_LAT) {
        this.NTC_LAT = NTC_LAT;
    }

    public String getNTC_LONG() {
        return NTC_LONG;
    }

    public void setNTC_LONG(String NTC_LONG) {
        this.NTC_LONG = NTC_LONG;
    }

    public String getDPRFNL_PROPERTYCODE() {
        return DPRFNL_PROPERTYCODE;
    }

    public void setDPRFNL_PROPERTYCODE(String DPRFNL_PROPERTYCODE) {
        this.DPRFNL_PROPERTYCODE = DPRFNL_PROPERTYCODE;
    }

    public String getDPRFNL_NOTICE_NO() {
        return DPRFNL_NOTICE_NO;
    }

    public void setDPRFNL_NOTICE_NO(String DPRFNL_NOTICE_NO) {
        this.DPRFNL_NOTICE_NO = DPRFNL_NOTICE_NO;
    }

    public String getDPRFNL_ADD_CODE() {
        return DPRFNL_ADD_CODE;
    }

    public void setDPRFNL_ADD_CODE(String DPRFNL_ADD_CODE) {
        this.DPRFNL_ADD_CODE = DPRFNL_ADD_CODE;
    }

    public String getDPRFNL_PROPERTYNO() {
        return DPRFNL_PROPERTYNO;
    }

    public void setDPRFNL_PROPERTYNO(String DPRFNL_PROPERTYNO) {
        this.DPRFNL_PROPERTYNO = DPRFNL_PROPERTYNO;
    }

    public String getDPRFNL_PROPERTYID() {
        return DPRFNL_PROPERTYID;
    }

    public void setDPRFNL_PROPERTYID(String DPRFNL_PROPERTYID) {
        this.DPRFNL_PROPERTYID = DPRFNL_PROPERTYID;
    }

    public String getDPRFNL_REMARKS() {
        return DPRFNL_REMARKS;
    }

    public void setDPRFNL_REMARKS(String DPRFNL_REMARKS) {
        this.DPRFNL_REMARKS = DPRFNL_REMARKS;
    }

    public String getDPRFNL_DPRDATE() {
        return DPRFNL_DPRDATE;
    }

    public void setDPRFNL_DPRDATE(String DPRFNL_DPRDATE) {
        this.DPRFNL_DPRDATE = DPRFNL_DPRDATE;
    }

    public String getDPROWNFNL_OWNERNAME() {
        return DPROWNFNL_OWNERNAME;
    }

    public void setDPROWNFNL_OWNERNAME(String DPROWNFNL_OWNERNAME) {
        this.DPROWNFNL_OWNERNAME = DPROWNFNL_OWNERNAME;
    }

    public String getDPROWNFNL_RELATIONNAME() {
        return DPROWNFNL_RELATIONNAME;
    }

    public void setDPROWNFNL_RELATIONNAME(String DPROWNFNL_RELATIONNAME) {
        this.DPROWNFNL_RELATIONNAME = DPROWNFNL_RELATIONNAME;
    }

    public String getDPROWNFNL_MRNO() {
        return DPROWNFNL_MRNO;
    }

    public void setDPROWNFNL_MRNO(String DPROWNFNL_MRNO) {
        this.DPROWNFNL_MRNO = DPROWNFNL_MRNO;
    }

    public String getDPROWNFNL_MOBILENO() {
        return DPROWNFNL_MOBILENO;
    }

    public void setDPROWNFNL_MOBILENO(String DPROWNFNL_MOBILENO) {
        this.DPROWNFNL_MOBILENO = DPROWNFNL_MOBILENO;
    }

    public String getDPROWNFNL_VLGDCODE() {
        return DPROWNFNL_VLGDCODE;
    }

    public void setDPROWNFNL_VLGDCODE(String DPROWNFNL_VLGDCODE) {
        this.DPROWNFNL_VLGDCODE = DPROWNFNL_VLGDCODE;
    }
}
