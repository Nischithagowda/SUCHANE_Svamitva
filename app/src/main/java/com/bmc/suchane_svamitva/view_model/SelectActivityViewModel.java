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
    public final ObservableInt districtCode = new ObservableInt(0);
    public final ObservableField<String> districtName = new ObservableField<>("District Name");
    public final ObservableField<String> talukCode = new ObservableField<>("");
    public final ObservableField<String> talukName = new ObservableField<>("Taluk Name");
    public final ObservableInt hobliCode = new ObservableInt(0);
    public final ObservableField<String> hobliName = new ObservableField<>("Hobli Name");
    public final ObservableField<String> villageCode = new ObservableField<>("");
    public final ObservableField<String> villageName = new ObservableField<>("Village Name");

    public SelectActivityViewModel(SelectActivityInterface selectActivityInterface) {
        this.selectActivityInterface = selectActivityInterface;
    }

    public void onClickNotice(View view) {
        selectActivityInterface.onNavigateToNotice();
    }

    public void onClickDPR(View view) {
        selectActivityInterface.onNavigateToDPR();
    }

    public void onClickFPR(View view) {
        selectActivityInterface.onNavigateToFPR();
    }
}
