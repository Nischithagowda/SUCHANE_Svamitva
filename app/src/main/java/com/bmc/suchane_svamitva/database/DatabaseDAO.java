package com.bmc.suchane_svamitva.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.bmc.suchane_svamitva.model.ApprovedDPRTbl;
import com.bmc.suchane_svamitva.model.District;
import com.bmc.suchane_svamitva.model.Hobli;
import com.bmc.suchane_svamitva.model.Image;
import com.bmc.suchane_svamitva.model.NoticeDetailsTbl;
import com.bmc.suchane_svamitva.model.OwnerTbl;
import com.bmc.suchane_svamitva.model.PendingDPRTbl;
import com.bmc.suchane_svamitva.model.PendingDPRTbl_Updated;
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

    @Query("delete from PendingDPRTbl where DPROWNFNL_VLGDCODE = :DPROWNFNL_VLGDCODE")
    int deletePendingDPRDetails(String DPROWNFNL_VLGDCODE);

    @Query("Select count(NTC_NOTICE_NO) from PendingDPRTbl where DPROWNFNL_VLGDCODE = :DPROWNFNL_VLGDCODE")
    int getPendingDPRCount(String DPROWNFNL_VLGDCODE);

    @Query("Select DPRFNL_NOTICE_NO as NOTICE_NO, DPRFNL_PROPERTYCODE as property_no, DPROWNFNL_OWNERNAME as Owner_Name from PendingDPRTbl where DPROWNFNL_VLGDCODE = :DPROWNFNL_VLGDCODE")
    List<OwnerTbl> getPendingDPRList(String DPROWNFNL_VLGDCODE);

    @Query("Select * from PendingDPRTbl where DPRFNL_NOTICE_NO = :NoticeNo")
    List<PendingDPRTbl> getPendingDPRDetailsByNoticeNo(String NoticeNo);

    @Query("Select NTC_NOTICE_NO from PendingDPRTbl pp inner join PendingDPRTbl_Updated pu on pp.NTC_NOTICE_NO = pu.NTC_NOTICE_NO_UPD and pp.NTC_ADD_CODE = pu.NTC_ADD_CODE_UPD")
    String[] getPendingDPRUpdatedDetails();

    @Query("Select Count(*) from PendingDPRTbl pp inner join PendingDPRTbl_Updated pu on pp.NTC_NOTICE_NO = pu.NTC_NOTICE_NO_UPD and pp.NTC_ADD_CODE = pu.NTC_ADD_CODE_UPD")
    int getPendingDPRUpdatedCountDetails();

    // PendingDPRTbl_Updated
    @Insert
    Long[] InsertPendingDPRUpdatedDetails(List<PendingDPRTbl_Updated> pendingDPRTbl_updatedList);

    @Query("delete from PendingDPRTbl_Updated where NTC_NOTICE_NO_UPD = :NTC_NOTICE_NO")
    int deletePendingDPRUpdatedDetails(String NTC_NOTICE_NO);

    //ApprovedDPR Tbl
    @Insert
    Long[] InsertApprovedDPRDetails(List<ApprovedDPRTbl> approvedDPRTblList);

    @Query("delete from ApprovedDPRTbl where DPROWNFNL_VLGDCODE = :DPROWNFNL_VLGDCODE")
    int deleteApprovedDPRDetails(String DPROWNFNL_VLGDCODE);

    @Query("Select count(NTC_NOTICE_NO) from ApprovedDPRTbl where DPROWNFNL_VLGDCODE = :DPROWNFNL_VLGDCODE")
    int getApprovedDPRCount(String DPROWNFNL_VLGDCODE);

    @Query("Select DPRFNL_NOTICE_NO as NOTICE_NO, DPRFNL_PROPERTYCODE as property_no, DPROWNFNL_OWNERNAME as Owner_Name from ApprovedDPRTbl where DPROWNFNL_VLGDCODE = :DPROWNFNL_VLGDCODE")
    List<OwnerTbl> getApprovedDPRDetails(String DPROWNFNL_VLGDCODE);

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
