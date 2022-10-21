package com.bmc.suchane_svamitva.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.bmc.suchane_svamitva.model.ApprovedDPRTbl;
import com.bmc.suchane_svamitva.model.District;
import com.bmc.suchane_svamitva.model.Hobli;
import com.bmc.suchane_svamitva.model.Image;
import com.bmc.suchane_svamitva.model.NoticeDetailsTbl;
import com.bmc.suchane_svamitva.model.PendingDPRTbl;
import com.bmc.suchane_svamitva.model.Taluka;
import com.bmc.suchane_svamitva.model.USER_DETAILS;
import com.bmc.suchane_svamitva.model.Village;

import java.util.List;

/**
 * Created by Nischitha on 18,May,2022
 **/
@Dao
public interface DatabaseDAO {

    //USER_DETAILS Tbl
    @Insert
    Long[] InsertUserDetails(List<USER_DETAILS> userDetailsList);

    @Query("SELECT CASE WHEN EXISTS (SELECT * FROM USER_DETAILS)THEN CAST(1 AS BIT)ELSE CAST(0 AS BIT) END")
    Boolean isUserDetailsAvailable();

    //1.Delete USER_DETAILS
    @Query("delete from USER_DETAILS")
    int deleteUserDetails();

    @Query("Select * from USER_DETAILS")
    List<USER_DETAILS> getUserDetails();

    @Query("Select DistrictCode, BhoomiDistrictName from USER_DETAILS")
    List<District> getUserDistrict();

    @Query("Select DistrictCode, TalukCode, BhoomiTalukName from USER_DETAILS where DistrictCode = :DistrictCode")
    List<Taluka> getUserTaluk(String DistrictCode);

    //Hobli Tbl
    @Insert
    Long[] InsertHobliDetails(List<Hobli> hobliList);

    //2 Delete Hobli
    @Query("delete from Hobli")
    int deleteHobliDetails();

    @Query("Select * from Hobli where DISTRICT_CODE = :DistrictCode and TALUKA_CODE = :TalukCode")
    List<Hobli> getHobliDetails(String DistrictCode, String TalukCode);

    //Village Tbl
    @Insert
    Long[] InsertVillageDetails(List<Village> villageList);

    @Query("delete from Village where DISTRICT_CODE = :DistrictCode and TALUK_CODE = :TalukCode and HOBLI_CODE = :HobliCode")
    int deleteVillageDetails(String DistrictCode, String TalukCode, String HobliCode);

    @Query("Select * from Village where DISTRICT_CODE = :DistrictCode and TALUK_CODE = :TalukCode and HOBLI_CODE = :HobliCode")
    List<Village> getVillageDetails(String DistrictCode, String TalukCode, String HobliCode);

    //NoticeDetails Tbl
    @Insert
    long InsertNoticeDetailsDetails(NoticeDetailsTbl noticeDetailsTbl);

    @Query("SELECT CASE WHEN EXISTS (SELECT * FROM NoticeDetailsTbl where NTC_NOTICE_NO = :NTC_NOTICE_NO and NTC_ADD_CODE = :NTC_ADD_CODE and NTC_VID = :NTC_VID)THEN CAST(1 AS BIT)ELSE CAST(0 AS BIT) END")
    Boolean isNoticeDetailsAvailable(String NTC_NOTICE_NO, String NTC_ADD_CODE, String NTC_VID);

    @Query("Select * from NoticeDetailsTbl")
    List<NoticeDetailsTbl> getNoticeDetailsTblValues();

    @Query("delete from NoticeDetailsTbl where NTC_NOTICE_NO = :NTC_NOTICE_NO and NTC_ADD_CODE = :NTC_ADD_CODE and NTC_VID = :NTC_VID")
    int deleteNoticeDetails(String NTC_NOTICE_NO, String NTC_ADD_CODE, String NTC_VID);

    //Image Tbl
    @Insert
    long InsertImage(Image image);

    @Query("Select * from Image where is_not_sent = :notSent")
    List<Image> getImageTblValues(boolean notSent);

    @Query("delete from Image where NOTICE_NO = :NoticeNo and ADDRESS_CODE = :AddrCode")
    int deleteImageDetails(String NoticeNo, String AddrCode);

    //PendingDPR Tbl
    @Insert
    Long[] InsertPendingDPRDetails(List<PendingDPRTbl> pendingDPRTblList);

    @Query("delete from PendingDPRTbl where NTC_WARD_VILLAGE = :NTC_WARD_VILLAGE")
    int deletePendingDPRDetails(String NTC_WARD_VILLAGE);

    @Query("Select count(NTC_NOTICE_NO) from PendingDPRTbl where NTC_WARD_VILLAGE = :NTC_WARD_VILLAGE")
    int getPendingDPRCount(String NTC_WARD_VILLAGE);

    @Query("Select * from PendingDPRTbl where NTC_WARD_VILLAGE = :NTC_WARD_VILLAGE")
    List<PendingDPRTbl> getPendingDPRDetails(String NTC_WARD_VILLAGE);

    //ApprovedDPR Tbl
    @Insert
    Long[] InsertApprovedDPRDetails(List<ApprovedDPRTbl> approvedDPRTblList);

    @Query("delete from ApprovedDPRTbl where NTC_WARD_VILLAGE = :NTC_WARD_VILLAGE")
    int deleteApprovedDPRDetails(String NTC_WARD_VILLAGE);

    @Query("Select count(NTC_NOTICE_NO) from ApprovedDPRTbl where NTC_WARD_VILLAGE = :NTC_WARD_VILLAGE")
    int getApprovedDPRCount(String NTC_WARD_VILLAGE);

    @Query("Select * from ApprovedDPRTbl where NTC_WARD_VILLAGE = :NTC_WARD_VILLAGE")
    List<ApprovedDPRTbl> getApprovedDPRDetails(String NTC_WARD_VILLAGE);

    //1.Delete USER_DETAILS

    //2 Delete Hobli

    //3 Delete Village
    @Query("delete from Village")
    void deleteFullVillageDetails();

    //4 Delete NoticeDetailsTbl
    @Query("delete from NoticeDetailsTbl")
    void deleteFullNoticeDetails();

    //5 Delete Image
    @Query("delete from Image")
    void deleteFullImageDetails();

    //6 Delete PendingDPRTbl
    @Query("delete from PendingDPRTbl")
    void deleteFullPendingDPRDetails();

    //7 Delete ApprovedDPRTbl
    @Query("delete from ApprovedDPRTbl")
    void deleteFullApprovedDPRDetails();
}
