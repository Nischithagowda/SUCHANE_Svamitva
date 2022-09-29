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
import com.bmc.suchane_svamitva.view.interfaces.DPR_FPR_FinalActivityInterface;

public class DPR_FPR_FinalActivityViewModel {
    DPR_FPR_FinalActivityInterface dpr_fpr_finalActivityInterface;
    public final ObservableField<String> noticeNumber = new ObservableField<>("1234");
    public final ObservableField<String> addressCode = new ObservableField<>("34567");
    public final ObservableField<String> Lat = new ObservableField<>("12.567");
    public final ObservableField<String> Long = new ObservableField<>("77.567");
    public ObservableBoolean fieldEnable = new ObservableBoolean(true);
    public final ObservableField<String> doorNoError = new ObservableField<>(null);
    public final ObservableField<String> doorNo = new ObservableField<>("");
    public final ObservableField<String> buildingError = new ObservableField<>(null);
    public final ObservableField<String> building = new ObservableField<>("");
    public final ObservableField<String> streetError = new ObservableField<>(null);
    public final ObservableField<String> street = new ObservableField<>("");
    public final ObservableField<String> landmarkError = new ObservableField<>(null);
    public final ObservableField<String> landmark = new ObservableField<>("");
    public final ObservableList<District> districtNameList = new ObservableArrayList<>();
    public final ObservableField<String> districtError = new ObservableField<>(null);
    public final ObservableField<String> district = new ObservableField<>("");
    public final ObservableField<String> districtId = new ObservableField<>("");
    public final ObservableList<Taluka> talukNameList = new ObservableArrayList<>();
    public final ObservableField<String> talukError = new ObservableField<>(null);
    public final ObservableField<String> taluk = new ObservableField<>("");
    public final ObservableField<String> talukId = new ObservableField<>("");
    public final ObservableList<Hobli> hobliNameList = new ObservableArrayList<>();
    public final ObservableField<String> hobliError = new ObservableField<>(null);
    public final ObservableField<String> hobli = new ObservableField<>("");
    public final ObservableField<String> hobliId = new ObservableField<>("");
    public final ObservableList<Village> villageNameList = new ObservableArrayList<>();
    public final ObservableField<String> villageError = new ObservableField<>(null);
    public final ObservableField<String> village = new ObservableField<>("");
    public final ObservableField<String> villageId = new ObservableField<>("");
    public final ObservableField<String> mobileNumber = new ObservableField<>("7894561230");
    public final ObservableField<String> ownerName = new ObservableField<>("Test");

    public DPR_FPR_FinalActivityViewModel(DPR_FPR_FinalActivityInterface dpr_fpr_finalActivityInterface) {
        this.dpr_fpr_finalActivityInterface = dpr_fpr_finalActivityInterface;
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

    public void onContactRuralDistrictItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
        District district = (District) parent.getItemAtPosition(position);
        this.district.set(district.getDistrictName());
        this.districtId.set(district.getDistrictId());
        //noticeActivityInterface.loadRuralTaluksList(this);
        this.taluk.set("");
        this.talukId.set("");
        this.hobli.set("");
        this.hobliId.set("");
        this.village.set("");
        this.villageId.set("");

    }

    public void onClickSaveData(View view) {

    }

    public void onClickSaveAndNextData(View view) {

    }

    public void onClickHomeData(View view) {

    }
}
