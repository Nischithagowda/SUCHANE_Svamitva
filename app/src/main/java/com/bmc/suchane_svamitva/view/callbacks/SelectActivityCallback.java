package com.bmc.suchane_svamitva.view.callbacks;
//
// Created by Nischitha on 23-09-2022.
// Copyright (c) 2022 BMC. All rights reserved.
//

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;

import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bmc.suchane_svamitva.utils.Constant;
import com.bmc.suchane_svamitva.view.interfaces.SelectActivityInterface;
import com.bmc.suchane_svamitva.view.ui.DPR_FPR_LaunchActivity;
import com.bmc.suchane_svamitva.view.ui.MainActivity;
import com.bmc.suchane_svamitva.view.ui.NoticeMapsFragment;
import com.bmc.suchane_svamitva.view.ui.SelectActivity;

public class SelectActivityCallback implements SelectActivityInterface {
    SelectActivity activity;

    public SelectActivityCallback(SelectActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onNavigateToNotice(){
        if (checkLocationPermission()) {
            Intent intent = new Intent(activity, NoticeMapsFragment.class);
            activity.startActivity(intent);
        }  else {
            ActivityCompat.requestPermissions(activity, new String[]{ACCESS_FINE_LOCATION, CAMERA},
                    Constant.LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onNavigateToDPR(){
        Intent intent = new Intent(activity, DPR_FPR_LaunchActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onNavigateToFPR(){
        Intent intent = new Intent(activity, DPR_FPR_LaunchActivity.class);
        activity.startActivity(intent);
    }

    private boolean checkLocationPermission() {
        return ContextCompat.checkSelfPermission(activity, ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }
}
