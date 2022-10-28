package com.bmc.suchane_svamitva.view.callbacks;
//
// Created by Nischitha on 23-09-2022.
// Copyright (c) 2022 BMC. All rights reserved.
//

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bmc.suchane_svamitva.utils.Constant;
import com.bmc.suchane_svamitva.view.interfaces.SelectActivityInterface;
import com.bmc.suchane_svamitva.view.ui.DPR_FPR_LaunchActivity;
import com.bmc.suchane_svamitva.view.ui.NoticeMapsFragment;
import com.bmc.suchane_svamitva.view.ui.SelectActivity;
import com.bmc.suchane_svamitva.view_model.SelectActivityViewModel;

public class SelectActivityCallback implements SelectActivityInterface {
    SelectActivity activity;

    public SelectActivityCallback(SelectActivity activity) {
        this.activity = activity;
    }

    @Override
    public void loadJurisdiction(SelectActivityViewModel viewModel){
        Intent intent = activity.getIntent();
        viewModel.districtCode.set(intent.getStringExtra("districtCode"));
        viewModel.districtName.set(intent.getStringExtra("districtName"));
        viewModel.talukCode.set(intent.getStringExtra("talukCode"));
        viewModel.talukName.set(intent.getStringExtra("talukName"));
        viewModel.hobliCode.set(intent.getStringExtra("hobliCode"));
        viewModel.hobliName.set(intent.getStringExtra("hobliName"));
        viewModel.villageCode.set(intent.getStringExtra("villageCode"));
        viewModel.LGD_VILLAGE_CODE.set(intent.getStringExtra("LGD_VILLAGE_CODE"));
        viewModel.villageName.set(intent.getStringExtra("villageName"));
    }

    @Override
    public void onNavigateToNotice(SelectActivityViewModel viewModel){
        if (checkLocationPermission()) {
            Intent intent = new Intent(activity, NoticeMapsFragment.class);
            intent.putExtra("districtCode", ""+viewModel.districtCode.get());
            intent.putExtra("districtName", ""+viewModel.districtName.get());
            intent.putExtra("talukCode", ""+viewModel.talukCode.get());
            intent.putExtra("talukName", ""+viewModel.talukName.get());
            intent.putExtra("hobliCode", ""+viewModel.hobliCode.get());
            intent.putExtra("hobliName", ""+viewModel.hobliName.get());
            intent.putExtra("villageCode", ""+viewModel.villageCode.get());
            intent.putExtra("LGD_VILLAGE_CODE", ""+viewModel.LGD_VILLAGE_CODE.get());
            intent.putExtra("villageName", ""+viewModel.villageName.get());
            activity.startActivity(intent);
        }  else {
            ActivityCompat.requestPermissions(activity, new String[]{ACCESS_FINE_LOCATION, CAMERA},
                    Constant.LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onNavigateToDPR(SelectActivityViewModel viewModel){
        Intent intent = new Intent(activity, DPR_FPR_LaunchActivity.class);
        intent.putExtra("districtCode", ""+viewModel.districtCode.get());
        intent.putExtra("districtName", ""+viewModel.districtName.get());
        intent.putExtra("talukCode", ""+viewModel.talukCode.get());
        intent.putExtra("talukName", ""+viewModel.talukName.get());
        intent.putExtra("hobliCode", ""+viewModel.hobliCode.get());
        intent.putExtra("hobliName", ""+viewModel.hobliName.get());
        intent.putExtra("villageCode", ""+viewModel.villageCode.get());
        intent.putExtra("LGD_VILLAGE_CODE", ""+viewModel.LGD_VILLAGE_CODE.get());
        intent.putExtra("villageName", ""+viewModel.villageName.get());
        activity.startActivity(intent);
    }

    @Override
    public void onNavigateToFPR(SelectActivityViewModel viewModel){
//        Intent intent = new Intent(activity, DPR_FPR_LaunchActivity.class);
//        intent.putExtra("districtCode", ""+viewModel.districtCode.get());
//        intent.putExtra("districtName", ""+viewModel.districtName.get());
//        intent.putExtra("talukCode", ""+viewModel.talukCode.get());
//        intent.putExtra("talukName", ""+viewModel.talukName.get());
//        intent.putExtra("hobliCode", ""+viewModel.hobliCode.get());
//        intent.putExtra("hobliName", ""+viewModel.hobliName.get());
//        intent.putExtra("villageCode", ""+viewModel.villageCode.get());
//        intent.putExtra("LGD_VILLAGE_CODE", ""+viewModel.LGD_VILLAGE_CODE.get());
//        intent.putExtra("villageName", ""+viewModel.villageName.get());
//        activity.startActivity(intent);
        Toast.makeText(activity, "Service Not Given", Toast.LENGTH_SHORT).show();
    }

    private boolean checkLocationPermission() {
        return ContextCompat.checkSelfPermission(activity, ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }
}
