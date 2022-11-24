package com.bmc.suchane_svamitva.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.bmc.suchane_svamitva.model.ApprovedDPRTbl;
import com.bmc.suchane_svamitva.model.District;
import com.bmc.suchane_svamitva.model.DocumentTbl;
import com.bmc.suchane_svamitva.model.Hobli;
import com.bmc.suchane_svamitva.model.Image;
import com.bmc.suchane_svamitva.model.ImageTempTbl;
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

    @Query("Select DistrictCode, TalukCode, BhoomiTalukName from USER_DETAILS where DistrictCode = :DistrictCode order by BhoomiTalukName")
    List<Taluka> getUserTaluk(String DistrictCode);

    //Hobli Tbl
    @Insert
    Long[] InsertHobliDetails(List<Hobli> hobliList);

    //2 Delete Hobli
    @Query("delete from Hobli")
    int deleteHobliDetails();

    @Query("Select * from Hobli where DISTRICT_CODE = :DistrictCode and TALUKA_CODE = :TalukCode order by HOBLI_NAME")
    List<Hobli> getHobliDetails(String DistrictCode, String TalukCode);

    //Village Tbl
    @Insert
    Long[] InsertVillageDetails(List<Village> villageList);

    @Query("delete from Village where DISTRICT_CODE = :DistrictCode and TALUK_CODE = :TalukCode and HOBLI_CODE = :HobliCode")
    int deleteVillageDetails(String DistrictCode, String TalukCode, String HobliCode);

    @Query("Select * from Village where DISTRICT_CODE = :DistrictCode and TALUK_CODE = :TalukCode and HOBLI_CODE = :HobliCode order by VILLAGE_NAME")
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

    //Document Tbl
    @Insert
    long InsertDocument(DocumentTbl documentTbl);

    @Query("Select * from DocumentTbl where NOTICE_NO = :NOTICE_NO and property_no = :property_no")
    List<DocumentTbl> getDocumentTblValues(String NOTICE_NO, String property_no);

    @Query("delete from DocumentTbl where NOTICE_NO = :NoticeNo and property_no = :property_no")
    int deleteDocumentDetails(String NoticeNo, String property_no);

    //Image_Temp Tbl
    @Insert
    long InsertTempImage(ImageTempTbl image);

    @Query("Select count(NOTICE_NO) from ImageTempTbl where NOTICE_NO = :NOTICE_NO and property_no = :property_no")
    int getTempImagePageCount(String NOTICE_NO, String property_no);

    @Query("Select * from ImageTempTbl where NOTICE_NO = :NOTICE_NO and property_no = :property_no")
    List<ImageTempTbl> getTempImagePath(String NOTICE_NO, String property_no);

    @Query("Select * from ImageTempTbl where NOTICE_NO = :NOTICE_NO and property_no = :property_no and DocumentID = :DocumentID")
    ImageTempTbl getTempImageDataByDocsID(String NOTICE_NO, String property_no, int DocumentID);

    @Query("delete from ImageTempTbl where NOTICE_NO = :NoticeNo and property_no = :property_no")
    int deleteTempImageDetails(String NoticeNo, String property_no);

    @Query("delete from ImageTempTbl where NOTICE_NO = :NoticeNo and property_no = :property_no and DocumentID = :DocumentID")
    int deleteTempImageDetailsByID(String NoticeNo, String property_no, int DocumentID);

    //PendingDPR Tbl
    @Insert
    Long[] InsertPendingDPRDetails(List<PendingDPRTbl> pendingDPRTblList);

    @Query("delete from PendingDPRTbl where DPROWNFNL_VLGDCODE = :DPROWNFNL_VLGDCODE")
    int deletePendingDPRDetails(String DPROWNFNL_VLGDCODE);

    @Query("Select count(NTC_NOTICE_NO) from PendingDPRTbl where DPROWNFNL_VLGDCODE = :DPROWNFNL_VLGDCODE")
    int getPendingDPRCount(String DPROWNFNL_VLGDCODE);

    @Query("Select DPRFNL_NOTICE_NO as NOTICE_NO, DPRFNL_PROPERTYCODE as property_no, DPROWNFNL_OWNERNAME as Owner_Name from PendingDPRTbl where DPROWNFNL_VLGDCODE = :DPROWNFNL_VLGDCODE")
    List<OwnerTbl> getPendingDPRList(String DPROWNFNL_VLGDCODE);

    @Query("Select * from PendingDPRTbl where DPRFNL_NOTICE_NO = :NoticeNo and DPRFNL_PROPERTYCODE = :PropertyNo")
    List<PendingDPRTbl> getPendingDPRDetailsByNoticeNo(String NoticeNo, String PropertyNo);

    @Query("Select NTC_NOTICE_NO from PendingDPRTbl pp inner join PendingDPRTbl_Updated pu on pp.DPRFNL_NOTICE_NO = pu.NOTICE_NO and pp.DPRFNL_PROPERTYCODE = pu.PROPERTY_CODE where pu.UPD_FLAG != 1")
    String[] getPendingDPRUpdatedDetails();

    @Query("Select Count(*) from PendingDPRTbl pp inner join PendingDPRTbl_Updated pu on pp.DPRFNL_NOTICE_NO = pu.NOTICE_NO and pp.DPRFNL_PROPERTYCODE = pu.PROPERTY_CODE where pu.UPD_FLAG != 1")
    int getPendingDPRUpdatedCountDetails();

    @Query("delete from PendingDPRTbl where DPRFNL_NOTICE_NO = :NOTICE_NO and DPRFNL_PROPERTYCODE = :PROPERTY_CODE")
    int deletePendingDPRDetails(String NOTICE_NO, String PROPERTY_CODE);

    // PendingDPRTbl_Updated
    @Insert
    long InsertPendingDPRUpdatedDetails(PendingDPRTbl_Updated pendingDPRTbl_updated);

    @Query("delete from PendingDPRTbl_Updated where NOTICE_NO = :NOTICE_NO and PROPERTY_CODE = :PROPERTY_CODE")
    int deletePendingDPRUpdatedDetails(String NOTICE_NO, String PROPERTY_CODE);

    @Query("SELECT CASE WHEN EXISTS (SELECT * FROM PendingDPRTbl_Updated where NOTICE_NO = :NOTICE_NO and PROPERTY_CODE = :PROPERTY_CODE)THEN CAST(1 AS BIT)ELSE CAST(0 AS BIT) END")
    Boolean isPendingDPRUpdatedAvailable(String NOTICE_NO, String PROPERTY_CODE);

    @Query("Select * from PendingDPRTbl_Updated")
    List<PendingDPRTbl_Updated> getPendingDPRTbl_UpdatedValues();

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
