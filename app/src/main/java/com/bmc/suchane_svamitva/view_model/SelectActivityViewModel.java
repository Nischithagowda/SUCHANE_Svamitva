package com.bmc.suchane_svamitva.view_model;
//
// Created by Nischitha on 23-09-2022.
// Copyright (c) 2022 BMC. All rights reserved.
//

import android.view.View;

import com.bmc.suchane_svamitva.view.interfaces.SelectActivityInterface;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

public class SelectActivityViewModel {
    SelectActivityInterface selectActivityInterface;
    public final ObservableField<String> districtCode = new ObservableField<>();
    public final ObservableField<String> districtName = new ObservableField<>("");
    public final ObservableField<String> talukCode = new ObservableField<>();
    public final ObservableField<String> talukName = new ObservableField<>("");
    public final ObservableField<String> hobliCode = new ObservableField<>();
    public final ObservableField<String> hobliName = new ObservableField<>("");
    public final ObservableField<String> villageCode = new ObservableField<>();
    public final ObservableField<String> villageName = new ObservableField<>("");

    public SelectActivityViewModel(SelectActivityInterface selectActivityInterface) {
        this.selectActivityInterface = selectActivityInterface;
        selectActivityInterface.loadJurisdiction(this);
    }

    public void onClickNotice(View view) {
        selectActivityInterface.onNavigateToNotice(this);
    }

    public void onClickDPR(View view) {
        selectActivityInterface.onNavigateToDPR();
    }

    public void onClickFPR(View view) {
        selectActivityInterface.onNavigateToFPR();
    }
}
