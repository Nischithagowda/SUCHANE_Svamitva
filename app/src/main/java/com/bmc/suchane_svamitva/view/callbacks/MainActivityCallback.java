package com.bmc.suchane_svamitva.view.callbacks;
//
// Created by Nischitha on 22-09-2022.
// Copyright (c) 2022 BMC. All rights reserved.
//

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.bmc.suchane_svamitva.database.DBConnection;
import com.bmc.suchane_svamitva.model.District;
import com.bmc.suchane_svamitva.model.Village;
import com.bmc.suchane_svamitva.view.interfaces.MainActivityInterface;
import com.bmc.suchane_svamitva.view.ui.MainActivity;
import com.bmc.suchane_svamitva.view.ui.NoticeMapsFragment;
import com.bmc.suchane_svamitva.view.ui.SelectActivity;
import com.bmc.suchane_svamitva.view_model.MainActivityViewModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivityCallback implements MainActivityInterface {
    MainActivity activity;

    public MainActivityCallback(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void getUserDistrict(MainActivityViewModel viewModel){

//        Observable
//                .fromCallable(() -> DBConnection.getConnection(activity)
//                        .getDataBaseDao()
//                        .getUserDetails())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(result ->{
//                    viewModel.USER_DistName.set(result.get(0).getBhoomiDistrictName());
//                    viewModel.USER_TalukName.set(result.get(0).getBhoomiTalukName());
//                    viewModel.USER_HobliName.set(result.get(0).getBhoomiHobliName());
//                    viewModel.USER_DesignationName.set(result.get(0).getDescription_EN());
//                    viewModel.USER_RoleName.set(result.get(0).getRoleDescription_EN());
//                }, error -> error.printStackTrace());

        Observable
                .fromCallable(() -> DBConnection.getConnection(activity)
                        .getDataBaseDao()
                        .getUserDistrict())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result ->{
                    if (result.size()>0) {
                        District district = new District();
                        district.setDISTRICT_CODE("0");
                        district.setDISTRICT_NAME("Select District");
                        result.add(0, district);
                        viewModel.districtNameList.addAll(result);

                    } else {
                        District district = new District();
                        district.setDISTRICT_CODE("No District");
                        district.setDISTRICT_NAME("NO District");
                        viewModel.districtNameList.add(district);
                    }
                }, error -> error.printStackTrace());
    }
    @Override
    public void onNavigateToNext(){
        Intent intent = new Intent(activity, SelectActivity.class);
        activity.startActivity(intent);
    }
}
