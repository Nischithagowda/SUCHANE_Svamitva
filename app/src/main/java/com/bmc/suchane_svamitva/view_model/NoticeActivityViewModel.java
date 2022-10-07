package com.bmc.suchane_svamitva.view_model;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.bmc.suchane_svamitva.model.District;
import com.bmc.suchane_svamitva.model.Hobli;
import com.bmc.suchane_svamitva.model.Taluka;
import com.bmc.suchane_svamitva.model.Village;
import com.bmc.suchane_svamitva.view.interfaces.NoticeActivityInterface;

import java.io.File;

public class NoticeActivityViewModel {
    NoticeActivityInterface noticeActivityInterface;
    public final ObservableField<String> noticeNumber = new ObservableField<>("");
    public final ObservableField<String> addressCode = new ObservableField<>("");
    public final ObservableField<String> virtualID = new ObservableField<>("");
    public final ObservableField<String> ownerLat = new ObservableField<>("");
    public final ObservableField<String> ownerLong = new ObservableField<>("");
    public ObservableBoolean fieldEnable = new ObservableBoolean(true);
    public final ObservableField<String> doorNoError = new ObservableField<>();
    public final ObservableField<String> doorNo = new ObservableField<>("");
    public final ObservableField<String> buildingError = new ObservableField<>();
    public final ObservableField<String> building = new ObservableField<>("");
    public final ObservableField<String> streetError = new ObservableField<>();
    public final ObservableField<String> street = new ObservableField<>("");
    public final ObservableField<String> landmarkError = new ObservableField<>(null);
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
    public ObservableField<Bitmap> imageBitMapPropertyOrLand = new ObservableField<>();
    public ObservableField<String> mCurrentPropertyOrLandPhotoPath = new ObservableField<>();
    public final ObservableField<byte[]> imageDataPropertyOrLand = new ObservableField<>();
    public final ObservableField<File> imageFilePropertyOrLand = new ObservableField<File>();
    public ObservableField<Bitmap> imageBitMapServingNotice = new ObservableField<>();
    public ObservableField<String> mCurrentServingNoticePhotoPath = new ObservableField<>();
    public final ObservableField<byte[]> imageDataServingNotice = new ObservableField<>();
    public final ObservableField<File> imageFileServingNotice = new ObservableField<File>();

    public NoticeActivityViewModel(NoticeActivityInterface noticeActivityInterface) {
        this.noticeActivityInterface = noticeActivityInterface;
        noticeActivityInterface.loadData(this);
    }

    public void capturePropertyPicture(View view){
        noticeActivityInterface.capturePropertyOrLandPhoto(this);
    }

    public void captureNoticePicture(View view){
        noticeActivityInterface.captureServingNoticePhoto(this);
    }

    public void processImagePropertyOrLand() {
        noticeActivityInterface.imageProcessPropertyOrLand(this);
    }

    public void processImageServingNotice() {
        noticeActivityInterface.imageProcessServingNotice(this);
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
        boolean status = false;

        if (imageDataPropertyOrLand.get() == null) {
            Toast.makeText(view.getContext(), "Capture Property Or Land photo to proceed", Toast.LENGTH_LONG).show();
            status = true;
        } else if (imageDataServingNotice.get() == null) {
            Toast.makeText(view.getContext(), "Capture Serving Notice photo to proceed", Toast.LENGTH_LONG).show();
            status = true;
        } else if (TextUtils.isEmpty(doorNo.get()) || doorNo.get() == null){
            doorNoError.set("Enter door number");
            Toast.makeText(view.getContext(), "Enter door number to proceed", Toast.LENGTH_LONG).show();
            status = true;
        } else if (TextUtils.isEmpty(building.get()) || building.get() == null){
            buildingError.set("Enter building name");
            Toast.makeText(view.getContext(), "Enter building name to proceed", Toast.LENGTH_LONG).show();
            status = true;
        } else if (TextUtils.isEmpty(street.get()) || street.get() == null){
            streetError.set("Enter Street/Area");
            Toast.makeText(view.getContext(), "Enter Street/Area to proceed", Toast.LENGTH_LONG).show();
            status = true;
        } else if (TextUtils.isEmpty(landmark.get()) || landmark.get() == null){
            landmarkError.set("Enter Landmark");
            Toast.makeText(view.getContext(), "Enter landmark to proceed", Toast.LENGTH_LONG).show();
            status = true;
        }

        if (!status) {
            noticeActivityInterface.saveAndNext(this);
        }
    }

    public void onClickSaveAndNextData(View view) {
        boolean status = false;

        if (imageDataPropertyOrLand.get() == null) {
            Toast.makeText(view.getContext(), "Capture Property Or Land photo to proceed", Toast.LENGTH_LONG).show();
            status = true;
        } else if (imageDataServingNotice.get() == null) {
            Toast.makeText(view.getContext(), "Capture Serving Notice photo to proceed", Toast.LENGTH_LONG).show();
            status = true;
        } else if (TextUtils.isEmpty(doorNo.get()) || doorNo.get() == null){
            doorNoError.set("Enter door number");
            Toast.makeText(view.getContext(), "Enter door number to proceed", Toast.LENGTH_LONG).show();
            status = true;
        } else if (TextUtils.isEmpty(building.get()) || building.get() == null){
            buildingError.set("Enter building name");
            Toast.makeText(view.getContext(), "Enter building name to proceed", Toast.LENGTH_LONG).show();
            status = true;
        } else if (TextUtils.isEmpty(street.get()) || street.get() == null){
            streetError.set("Enter Street/Area");
            Toast.makeText(view.getContext(), "Enter Street/Area to proceed", Toast.LENGTH_LONG).show();
            status = true;
        } else if (TextUtils.isEmpty(landmark.get()) || landmark.get() == null){
            landmarkError.set("Enter Landmark");
            Toast.makeText(view.getContext(), "Enter landmark to proceed", Toast.LENGTH_LONG).show();
            status = true;
        }

        if (!status) {
            noticeActivityInterface.saveAndNext(this);
        }
    }

    public void onClickHomeData(View view) {
        noticeActivityInterface.goHome();
    }

}
