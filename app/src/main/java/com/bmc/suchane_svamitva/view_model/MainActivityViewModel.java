package com.bmc.suchane_svamitva.view_model;
//
// Created by Nischitha on 22-09-2022.
// Copyright (c) 2022 BMC. All rights reserved.
//

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.bmc.suchane_svamitva.model.District;
import com.bmc.suchane_svamitva.model.Hobli;
import com.bmc.suchane_svamitva.model.Taluka;
import com.bmc.suchane_svamitva.model.Village;
import com.bmc.suchane_svamitva.view.interfaces.MainActivityInterface;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableList;

import java.util.Objects;

public class MainActivityViewModel {
    MainActivityInterface mainActivityInterface;
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
    public final ObservableField<String> LGD_VILLAGE_CODE = new ObservableField<>("");

    public MainActivityViewModel(MainActivityInterface mainActivityInterface) {
        this.mainActivityInterface = mainActivityInterface;
        mainActivityInterface.getUserDistrict(this);
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
        this.LGD_VILLAGE_CODE.set("");
        this.villageName.set("");
        mainActivityInterface.getUserTaluk(this);
    }

    public void onContactTalukItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
        Taluka taluka = (Taluka) parent.getItemAtPosition(position);
        this.talukName.set(taluka.getTALUKA_NAME());
        this.talukCode.set(taluka.getTALUKA_CODE());
        talukError.set(null);
        this.hobliCode.set("");
        this.hobliName.set("");
        this.villageCode.set("");
        this.LGD_VILLAGE_CODE.set("");
        this.villageName.set("");
        mainActivityInterface.getHobli(this);
    }

    public void onContactHobliItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
        Hobli hobli = (Hobli) parent.getItemAtPosition(position);
        this.hobliName.set(hobli.getHOBLI_NAME());
        this.hobliCode.set(hobli.getHOBLI_CODE());
        hobliError.set(null);
        this.villageCode.set("");
        this.LGD_VILLAGE_CODE.set("");
        this.villageName.set("");
        mainActivityInterface.getVillage(this);
    }

    public void onContactVillageItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
        Village village = (Village) parent.getItemAtPosition(position);
        this.villageName.set(village.getVILLAGE_NAME());
        this.villageCode.set(village.getVILLAGE_CODE());
//        this.LGD_VILLAGE_CODE.set(village.getLGD_VILLAGE_CODE());
        this.LGD_VILLAGE_CODE.set("598473");
        villageError.set(null);
    }

    public void onClickFetchData(View view) {
        String distCode = this.districtCode.get();
        String talukCode = this.talukCode.get();
        String hobliCode = this.hobliCode.get();
        String villCode = this.villageCode.get();

        boolean status = false;
        if (TextUtils.isEmpty(distCode) || Objects.equals(distCode, "0")) {
            status = true;
            districtError.set("District is required");
        } else if (TextUtils.isEmpty(talukCode) || Objects.equals(talukCode, "0")) {
            status = true;
            talukError.set("Taluk is required");
        } else if (TextUtils.isEmpty(hobliCode)|| Objects.equals(hobliCode, "0")) {
            status = true;
            hobliError.set("Hobli is required");
        } else if (TextUtils.isEmpty(villCode)|| Objects.equals(villCode, "0")) {
            status = true;
            villageError.set("Village is required");
        }

        if (!status) {
//            mainActivityInterface.getPendingDPRDetails(this);
            mainActivityInterface.onNavigateToNext(this);
        }

    }
}
