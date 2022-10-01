package com.bmc.suchane_svamitva.view.interfaces;
//
// Created by Nischitha on 23-09-2022.
// Copyright (c) 2022 BMC. All rights reserved.
//

import com.bmc.suchane_svamitva.view_model.SelectActivityViewModel;

public interface SelectActivityInterface {
    void onNavigateToNotice();
    void onNavigateToDPR();
    void onNavigateToFPR();
    void loadJurisdiction(SelectActivityViewModel viewModel);
}
