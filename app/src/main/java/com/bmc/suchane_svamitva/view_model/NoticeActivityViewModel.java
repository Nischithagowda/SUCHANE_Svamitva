package com.bmc.suchane_svamitva.view_model;

import android.view.View;
import android.widget.AdapterView;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.bmc.suchane_svamitva.model.District;
import com.bmc.suchane_svamitva.model.Hobli;
import com.bmc.suchane_svamitva.model.Taluka;
import com.bmc.suchane_svamitva.model.Village;
import com.bmc.suchane_svamitva.view.interfaces.NoticeActivityInterface;

public class NoticeActivityViewModel {
    NoticeActivityInterface noticeActivityInterface;
    public final ObservableField<String> noticeNumber = new ObservableField<>("1234");
    public final ObservableField<String> addressCode = new ObservableField<>("34567");
    public final ObservableField<String> Lat = new ObservableField<>("12.567");
    public final ObservableField<String> Long = new ObservableField<>("77.567");
    public ObservableBoolean fieldEnable = new ObservableBoolean(true);
    public final ObservableField<String> doorNoError = new ObservableField<>();
    public final ObservableField<String> doorNo = new ObservableField<>("");
    public final ObservableField<String> buildingError = new ObservableField<>();
    public final ObservableField<String> building = new ObservableField<>("");
    public final ObservableField<String> streetError = new ObservableField<>();
    public final ObservableField<String> street = new ObservableField<>("");
    public final ObservableField<String> landmarkError = new ObservableField<>();
    public final ObservableField<String> landmark = new ObservableField<>("");
    public final ObservableList<District> districtNameList = new ObservableArrayList<>();
    public final ObservableField<String> districtCode = new ObservableField<>();
    public final ObservableField<String> districtError = new ObservableField<>();
    public final ObservableField<String> districtName = new ObservableField<>("");
    public final ObservableList<Taluka> talukNameList = new ObservableArrayList<>();
    public final ObservableField<String> talukCode = new ObservableField<>("");
    public final ObservableField<String> talukError = new ObservableField<>();
    public final ObservableField<String> talukName = new ObservableField<>("");
    public final ObservableList<Hobli> hobliNameList = new ObservableArrayList<>();
    public final ObservableField<String> hobliName = new ObservableField<>("");
    public final ObservableField<String> hobliError = new ObservableField<>();
    public final ObservableField<String> hobliCode = new ObservableField<>("");
    public final ObservableList<Village> villageNameList = new ObservableArrayList<>();
    public final ObservableField<String> villageName = new ObservableField<>("");
    public final ObservableField<String> villageError = new ObservableField<>();
    public final ObservableField<String> villageCode = new ObservableField<>("");

    public NoticeActivityViewModel(NoticeActivityInterface noticeActivityInterface) {
        this.noticeActivityInterface = noticeActivityInterface;
    }

    public void capturePropertyPicture(View view){

    }

    public void captureNoticePicture(View view){

    }

    public void onContactDoorNoTextChanged(CharSequence s, int start, int before, int count) {
        doorNo.set(s.toString());
        doorNoError.set(null);
    }

    public void onContactBuildingTextChanged(CharSequence s, int start, int before, int count) {
        building.set(s.toString());
        buildingError.set(null);
    }

    public void onContactStreetTextChanged(CharSequence s, int start, int before, int count) {
        street.set(s.toString());
        streetError.set(null);
    }

    public void onContactLandmarkTextChanged(CharSequence s, int start, int before, int count) {
        landmark.set(s.toString());
        landmarkError.set(null);
    }

    public void onContactDistrictItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
        District district = (District) parent.getItemAtPosition(position);
        this.districtName.set(district.getDISTRICT_NAME());
        this.districtCode.set(district.getDISTRICT_CODE());
        districtError.set(null);
        this.talukCode.set("");
        this.talukName.set("");
        this.hobliCode.set("");
        this.hobliName.set("");
        this.villageCode.set("");
        this.villageName.set("");
        noticeActivityInterface.getUserTaluk(this);
    }

    public void onContactTalukItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
        Taluka taluka = (Taluka) parent.getItemAtPosition(position);
        this.talukName.set(taluka.getTALUKA_NAME());
        this.talukCode.set(taluka.getTALUKA_CODE());
        talukError.set(null);
        this.hobliCode.set("");
        this.hobliName.set("");
        this.villageCode.set("");
        this.villageName.set("");
        noticeActivityInterface.getHobli(this);
    }

    public void onContactHobliItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
        Hobli hobli = (Hobli) parent.getItemAtPosition(position);
        this.hobliName.set(hobli.getHOBLI_NAME());
        this.hobliCode.set(hobli.getHOBLI_CODE());
        hobliError.set(null);
        this.villageCode.set("");
        this.villageName.set("");
        noticeActivityInterface.getVillage(this);
    }

    public void onContactVillageItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
        Village village = (Village) parent.getItemAtPosition(position);
        this.villageName.set(village.getVILLAGE_NAME());
        this.villageCode.set(village.getVILLAGE_CODE());
        villageError.set(null);
    }

    public void onClickSaveData(View view) {

    }

    public void onClickSaveAndNextData(View view) {

    }

    public void onClickHomeData(View view) {

    }

}
