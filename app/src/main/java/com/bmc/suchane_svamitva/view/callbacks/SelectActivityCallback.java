package com.bmc.suchane_svamitva.view.callbacks;
//
// Created by Nischitha on 23-09-2022.
// Copyright (c) 2022 BMC. All rights reserved.
//

import com.bmc.suchane_svamitva.view.interfaces.SelectActivityInterface;
import com.bmc.suchane_svamitva.view.ui.SelectActivity;

public class SelectActivityCallback implements SelectActivityInterface {
    SelectActivity activity;

    public SelectActivityCallback(SelectActivity activity) {
        this.activity = activity;
    }
}
