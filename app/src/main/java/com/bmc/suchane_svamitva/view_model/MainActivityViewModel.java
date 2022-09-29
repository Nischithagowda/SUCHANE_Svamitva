package com.bmc.suchane_svamitva.view_model;
//
// Created by Nischitha on 22-09-2022.
// Copyright (c) 2022 BMC. All rights reserved.
//

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.bmc.suchane_svamitva.model.Hobli;
import com.bmc.suchane_svamitva.model.Village;
import com.bmc.suchane_svamitva.view.interfaces.MainActivityInterface;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableList;

public class MainActivityViewModel {
    MainActivityInterface mainActivityInterface;
    public final ObservableList<Village> districtNameList = new ObservableArrayList<>();
    public final ObservableInt selectedItemPosition = new ObservableInt(0);
    public final ObservableInt districtCode = new ObservableInt(0);
    public final ObservableField<String> districtError = new ObservableField<>(null);
    public final ObservableField<String> districtName = new ObservableField<>("");
    public final ObservableList<Village> talukNameList = new ObservableArrayList<>();
    public final ObservableField<String> talukCode = new ObservableField<>("");
    public final ObservableField<String> talukError = new ObservableField<>(null);
    public final ObservableField<String> talukName = new ObservableField<>("");
    public final ObservableList<Hobli> hobliNameList = new ObservableArrayList<>();
    public final ObservableField<String> hobliName = new ObservableField<>("");
    public final ObservableField<String> hobliError = new ObservableField<>(null);
    public final ObservableField<String> hobliCode = new ObservableField<>("");
    public final ObservableList<Village> villageNameList = new ObservableArrayList<>();
    public final ObservableField<String> villageName = new ObservableField<>("");
    public final ObservableField<String> villageError = new ObservableField<>(null);
    public final ObservableField<String> villageCode = new ObservableField<>("");

    public MainActivityViewModel(MainActivityInterface mainActivityInterface) {
        this.mainActivityInterface = mainActivityInterface;
    }

    public void onContactHobliItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
        Village village = (Village) parent.getItemAtPosition(position);
        this.hobliName.set(village.getBhoomiVillageName());
        this.hobliCode.set(village.getVillageCode());
        hobliError.set(null);
    }

    public void onContactVillageItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
        Village village = (Village) parent.getItemAtPosition(position);
        this.villageName.set(village.getBhoomiVillageName());
        this.villageCode.set(village.getVillageCode());
        villageError.set(null);
    }

    public void onClickFetchData(View view) {
        String villCode = this.villageCode.get();

        boolean status = false;
        if (TextUtils.isEmpty(villCode)) {
            status = true;
            villageError.set("Village is required");
        }

        if (!status) {
            //mainActivityInterface.getPropDetailsFromServer(this);
        }
    }
}
