package com.bmc.suchane_svamitva.view_model;

import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.model.District;
import com.bmc.suchane_svamitva.model.DocumentTbl;
import com.bmc.suchane_svamitva.model.Hobli;
import com.bmc.suchane_svamitva.model.Taluka;
import com.bmc.suchane_svamitva.model.Village;
import com.bmc.suchane_svamitva.view.adapter.DocumentListAdapter;
import com.bmc.suchane_svamitva.view.interfaces.DocsUploadFinalInterface;
import com.bmc.suchane_svamitva.view.interfaces.DocumentListInterface;

import java.io.File;
import java.util.Objects;

public class DocsUploadFinalViewModel implements DocumentListInterface {
    DocsUploadFinalInterface docsUploadFinalInterface;
    public final ObservableField<String> noticeNumber = new ObservableField<>("");
    public final ObservableField<String> addressCode = new ObservableField<>("");
    public final ObservableField<String> Lat = new ObservableField<>("");
    public final ObservableField<String> Long = new ObservableField<>("");
    public ObservableBoolean fieldEnable = new ObservableBoolean(true);
    public final ObservableField<String> doorNoError = new ObservableField<>();
    public final ObservableField<String> doorNo = new ObservableField<>("");
    public final ObservableField<String> doorNo_UPD = new ObservableField<>("");
    public final ObservableField<String> buildingError = new ObservableField<>();
    public final ObservableField<String> building = new ObservableField<>("");
    public final ObservableField<String> building_UPD = new ObservableField<>("");
    public final ObservableField<String> streetError = new ObservableField<>();
    public final ObservableField<String> street = new ObservableField<>("");
    public final ObservableField<String> street_UPD = new ObservableField<>("");
    public final ObservableField<String> landmarkError = new ObservableField<>();
    public final ObservableField<String> landmark = new ObservableField<>("");
    public final ObservableField<String> landmark_UPD = new ObservableField<>("");
    public final ObservableList<String> documentNameList = new ObservableArrayList<>();
    public final ObservableField<String> documentNameError = new ObservableField<>();
    public final ObservableField<String> editDocsNameError = new ObservableField<>();
    public final ObservableField<String> documentName = new ObservableField<>("");
    public final ObservableField<String> editDocsName = new ObservableField<>("");
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
    public final ObservableField<String> LGD_VILLAGE_CODE = new ObservableField<>();
    public final ObservableField<String> mobileNumber = new ObservableField<>("0000000000");
    public final ObservableField<String> propertyNo = new ObservableField<>("");
    public final ObservableField<String> ownerName = new ObservableField<>("");
    public ObservableBoolean isChangesDone = new ObservableBoolean(false);
    public final ObservableField<DocumentListAdapter> documentListAdapter = new ObservableField<>();
    public final ObservableList<DocumentTbl> documentTblList = new ObservableArrayList<>();
    public ObservableBoolean isDocumentCaptured = new ObservableBoolean(false);

    public DocsUploadFinalViewModel(DocsUploadFinalInterface docsUploadFinalInterface) {
        this.docsUploadFinalInterface = docsUploadFinalInterface;
        this.docsUploadFinalInterface.loadData(this);
        documentListAdapter.set(new DocumentListAdapter(this));
    }

    public void onClickAddDocument(View view){
        if (TextUtils.isEmpty(this.documentName.get())){
            documentNameError.set("Select any document");
        } else {
            docsUploadFinalInterface.onClickAddDocument(this);
        }
    }

    public void onClickSetDocsName(View view) {
        if (TextUtils.isEmpty(this.editDocsName.get())){
            editDocsNameError.set(view.getContext().getString(R.string.enter_name_of_the_document));
        } else {
            this.documentName.set(this.editDocsName.get());
            docsUploadFinalInterface.onClickCancel();
        }
    }

    public void onClickCancel(View view) {
        this.documentName.set("");
        this.editDocsName.set("");
        this.editDocsNameError.set(null);
        docsUploadFinalInterface.onClickCancel();
    }

    public void onContactDocumentNameItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
        String docsName = parent.getItemAtPosition(position).toString();
        if (docsName.equals(arg1.getResources().getString(R.string.other_document))){
            docsUploadFinalInterface.captureDocumentName(this);
        } else {
            this.documentName.set(docsName);
            this.documentNameError.set(null);
        }
    }

    public void onContactDocsNameTextChanged(CharSequence s, int start, int before, int count) {
        this.editDocsName.set(s.toString());
        this.editDocsNameError.set(null);
    }

    public void onContactDoorNoTextChanged(CharSequence s, int start, int before, int count) {
        doorNo_UPD.set(s.toString());
        doorNoError.set(null);
    }

    public void onContactBuildingTextChanged(CharSequence s, int start, int before, int count) {
        building_UPD.set(s.toString());
        buildingError.set(null);
    }

    public void onContactStreetTextChanged(CharSequence s, int start, int before, int count) {
        street_UPD.set(s.toString());
        streetError.set(null);
    }

    public void onContactLandmarkTextChanged(CharSequence s, int start, int before, int count) {
        landmark_UPD.set(s.toString());
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
        docsUploadFinalInterface.getUserTaluk(this);
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
        docsUploadFinalInterface.getHobli(this);
    }

    public void onContactHobliItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
        Hobli hobli = (Hobli) parent.getItemAtPosition(position);
        this.hobliName.set(hobli.getHOBLI_NAME());
        this.hobliCode.set(hobli.getHOBLI_CODE());
        hobliError.set(null);
        this.villageCode.set("");
        this.villageName.set("");
        docsUploadFinalInterface.getVillage(this);
    }

    public void onContactVillageItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
        Village village = (Village) parent.getItemAtPosition(position);
        this.villageName.set(village.getVILLAGE_NAME());
        this.villageCode.set(village.getVILLAGE_CODE());
        villageError.set(null);
    }

    public void onClickSaveAndNextData(View view) {
        boolean status = false;

        if (this.documentTblList.size() <= 0) {
            Toast.makeText(view.getContext(), "Add document to proceed", Toast.LENGTH_LONG).show();
            status = true;
        } else if (TextUtils.isEmpty(doorNo_UPD.get()) || doorNo_UPD.get() == null){
            doorNoError.set("Enter door number");
            Toast.makeText(view.getContext(), "Enter door number to proceed", Toast.LENGTH_LONG).show();
            status = true;
        } else if (TextUtils.isEmpty(building_UPD.get()) || building_UPD.get() == null){
            buildingError.set("Enter building name");
            Toast.makeText(view.getContext(), "Enter building name to proceed", Toast.LENGTH_LONG).show();
            status = true;
        } else if (TextUtils.isEmpty(street_UPD.get()) || street_UPD.get() == null){
            streetError.set("Enter Street/Area");
            Toast.makeText(view.getContext(), "Enter Street/Area to proceed", Toast.LENGTH_LONG).show();
            status = true;
        } else if (TextUtils.isEmpty(landmark_UPD.get()) || landmark_UPD.get() == null){
            landmarkError.set("Enter Landmark");
            Toast.makeText(view.getContext(), "Enter landmark to proceed", Toast.LENGTH_LONG).show();
            status = true;
        }

        if (!status) {
            this.isChangesDone.set(!Objects.requireNonNull(this.doorNo.get()).equalsIgnoreCase(this.doorNo_UPD.get()) ||
                    !Objects.requireNonNull(this.building.get()).equalsIgnoreCase(this.building_UPD.get()) ||
                    !Objects.requireNonNull(this.street.get()).equalsIgnoreCase(this.street_UPD.get()) ||
                    !Objects.requireNonNull(this.landmark.get()).equalsIgnoreCase(this.landmark_UPD.get()));
            docsUploadFinalInterface.saveAndNext(this);
        }
    }

    public void onClickHomeData(View view) {
        docsUploadFinalInterface.goHome();
    }

    public void onClickViewPDF(DocumentTbl documentTbl){
        docsUploadFinalInterface.onClickViewPDF(this, documentTbl);
    }

    public void onClickDeletePDF(DocumentTbl documentTbl){
        docsUploadFinalInterface.onClickDeletePDF(this, documentTbl);
    }
}
