package com.bmc.suchane_svamitva.view.interfaces;
//
// Created by Nischitha on 22-09-2022.
// Copyright (c) 2022 BMC. All rights reserved.
//

import com.bmc.suchane_svamitva.view_model.MainActivityViewModel;

public interface MainActivityInterface {
    void onNavigateToNext(MainActivityViewModel viewModel);
    void getUserDistrict(MainActivityViewModel viewModel);
    void getUserTaluk(MainActivityViewModel viewModel);
    void getHobli(MainActivityViewModel viewModel);
    void getVillage(MainActivityViewModel viewModel);
    void logoutFromServer();
}
