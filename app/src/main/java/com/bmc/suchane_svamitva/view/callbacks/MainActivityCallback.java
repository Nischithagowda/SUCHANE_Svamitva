package com.bmc.suchane_svamitva.view.callbacks;
//
// Created by Nischitha on 22-09-2022.
// Copyright (c) 2022 BMC. All rights reserved.
//

import com.bmc.suchane_svamitva.view.interfaces.MainActivityInterface;
import com.bmc.suchane_svamitva.view.ui.MainActivity;

public class MainActivityCallback implements MainActivityInterface {
    MainActivity activity;

    public MainActivityCallback(MainActivity activity) {
        this.activity = activity;
    }
}
