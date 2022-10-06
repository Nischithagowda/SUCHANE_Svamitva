package com.bmc.suchane_svamitva.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.bmc.suchane_svamitva.model.District;
import com.bmc.suchane_svamitva.model.Hobli;
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

    @Query("SELECT CASE WHEN EXISTS (SELECT * FROM Hobli)THEN CAST(1 AS BIT)ELSE CAST(0 AS BIT) END")
    Boolean isHobliDetailsAvailable();

    @Query("delete from Hobli")
    int deleteHobliDetails();

    @Query("Select * from Hobli where DISTRICT_CODE = :DistrictCode and TALUKA_CODE = :TalukCode")
    List<Hobli> getHobliDetails(String DistrictCode, String TalukCode);

    //Village Tbl
    @Insert
    Long[] InsertVillageDetails(List<Village> villageList);

    @Query("SELECT CASE WHEN EXISTS (SELECT * FROM Village)THEN CAST(1 AS BIT)ELSE CAST(0 AS BIT) END")
    Boolean isVillageDetailsAvailable();

    @Query("delete from Village where DISTRICT_CODE = :DistrictCode and TALUK_CODE = :TalukCode and HOBLI_CODE = :HobliCode")
    int deleteVillageDetails(String DistrictCode, String TalukCode, String HobliCode);

    @Query("Select * from Village where DISTRICT_CODE = :DistrictCode and TALUK_CODE = :TalukCode and HOBLI_CODE = :HobliCode")
    List<Village> getVillageDetails(String DistrictCode, String TalukCode, String HobliCode);
}
