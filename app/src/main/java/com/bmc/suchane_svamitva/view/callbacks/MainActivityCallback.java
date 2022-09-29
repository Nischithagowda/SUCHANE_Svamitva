package com.bmc.suchane_svamitva.view.callbacks;
//
// Created by Nischitha on 22-09-2022.
// Copyright (c) 2022 BMC. All rights reserved.
//

import android.content.Intent;

import com.bmc.suchane_svamitva.view.interfaces.MainActivityInterface;
import com.bmc.suchane_svamitva.view.ui.MainActivity;
import com.bmc.suchane_svamitva.view.ui.NoticeMapsFragment;
import com.bmc.suchane_svamitva.view.ui.SelectActivity;

public class MainActivityCallback implements MainActivityInterface {
    MainActivity activity;

    public MainActivityCallback(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onNavigateToNext(){
        Intent intent = new Intent(activity, SelectActivity.class);
        activity.startActivity(intent);
    }
}
