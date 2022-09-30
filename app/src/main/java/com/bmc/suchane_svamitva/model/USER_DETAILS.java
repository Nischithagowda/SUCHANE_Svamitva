package com.bmc.suchane_svamitva.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class USER_DETAILS {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "UserID")
    @SerializedName("UserID")
    @Expose
    private String UserID;

    @ColumnInfo(name = "DesignationID")
    @SerializedName("DesignationID")
    @Expose
    private String DesignationID;

    @ColumnInfo(name = "RoleId")
    @SerializedName("RoleId")
    @Expose
    private String RoleId;

    @ColumnInfo(name = "DistrictCode")
    @SerializedName("DistrictCode")
    @Expose
    private String DistrictCode;

    @ColumnInfo(name = "TalukCode")
    @SerializedName("TalukCode")
    @Expose
    private String TalukCode;

    @ColumnInfo(name = "BhoomiDistrictName")
    @SerializedName("BhoomiDistrictName")
    @Expose
    private String BhoomiDistrictName;

    @ColumnInfo(name = "BhoomiTalukName")
    @SerializedName("BhoomiTalukName")
    @Expose
    private String BhoomiTalukName;

    @ColumnInfo(name = "BhoomiDistrictNameKN")
    @SerializedName("BhoomiDistrictNameKN")
    @Expose
    private String BhoomiDistrictNameKN;

    @ColumnInfo(name = "BhoomiTalukNameKN")
    @SerializedName("BhoomiTalukNameKN")
    @Expose
    private String BhoomiTalukNameKN;

    @ColumnInfo(name = "design_KN")
    @SerializedName("design_KN")
    @Expose
    private String design_KN;

    @ColumnInfo(name = "design_EN")
    @SerializedName("design_EN")
    @Expose
    private String design_EN;

    @ColumnInfo(name = "role_KN")
    @SerializedName("role_KN")
    @Expose
    private String role_KN;

    @ColumnInfo(name = "role_EN")
    @SerializedName("role_EN")
    @Expose
    private String role_EN;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getDesignationID() {
        return DesignationID;
    }

    public void setDesignationID(String designationID) {
        DesignationID = designationID;
    }

    public String getRoleId() {
        return RoleId;
    }

    public void setRoleId(String roleId) {
        RoleId = roleId;
    }

    public String getDistrictCode() {
        return DistrictCode;
    }

    public void setDistrictCode(String districtCode) {
        DistrictCode = districtCode;
    }

    public String getTalukCode() {
        return TalukCode;
    }

    public void setTalukCode(String talukCode) {
        TalukCode = talukCode;
    }

    public String getBhoomiDistrictName() {
        return BhoomiDistrictName;
    }

    public void setBhoomiDistrictName(String bhoomiDistrictName) {
        BhoomiDistrictName = bhoomiDistrictName;
    }

    public String getBhoomiTalukName() {
        return BhoomiTalukName;
    }

    public void setBhoomiTalukName(String bhoomiTalukName) {
        BhoomiTalukName = bhoomiTalukName;
    }

    public String getBhoomiDistrictNameKN() {
        return BhoomiDistrictNameKN;
    }

    public void setBhoomiDistrictNameKN(String bhoomiDistrictNameKN) {
        BhoomiDistrictNameKN = bhoomiDistrictNameKN;
    }

    public String getBhoomiTalukNameKN() {
        return BhoomiTalukNameKN;
    }

    public void setBhoomiTalukNameKN(String bhoomiTalukNameKN) {
        BhoomiTalukNameKN = bhoomiTalukNameKN;
    }

    public String getDesign_KN() {
        return design_KN;
    }

    public void setDesign_KN(String design_KN) {
        this.design_KN = design_KN;
    }

    public String getDesign_EN() {
        return design_EN;
    }

    public void setDesign_EN(String design_EN) {
        this.design_EN = design_EN;
    }

    public String getRole_KN() {
        return role_KN;
    }

    public void setRole_KN(String role_KN) {
        this.role_KN = role_KN;
    }

    public String getRole_EN() {
        return role_EN;
    }

    public void setRole_EN(String role_EN) {
        this.role_EN = role_EN;
    }
}
