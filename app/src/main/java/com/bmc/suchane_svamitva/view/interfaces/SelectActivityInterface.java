package com.bmc.suchane_svamitva.view.interfaces;
//
// Created by Nischitha on 23-09-2022.
// Copyright (c) 2022 BMC. All rights reserved.
//

import com.bmc.suchane_svamitva.view_model.SelectActivityViewModel;

public interface SelectActivityInterface {
    void onNavigateToNotice(SelectActivityViewModel viewModel);
    void onNavigateToDocUpload(SelectActivityViewModel viewModel);
    void onNavigateToDPR(SelectActivityViewModel viewModel);
    void onNavigateToFPR(SelectActivityViewModel viewModel);
    void loadJurisdiction(SelectActivityViewModel viewModel);
}
