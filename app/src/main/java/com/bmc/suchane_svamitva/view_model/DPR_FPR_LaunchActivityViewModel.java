package com.bmc.suchane_svamitva.view_model;

import android.widget.RadioGroup;

import com.bmc.suchane_svamitva.view.interfaces.DPR_FPR_LaunchActivityInterface;

public class DPR_FPR_LaunchActivityViewModel {
    DPR_FPR_LaunchActivityInterface dpr_fpr_launchActivityInterface;

    public DPR_FPR_LaunchActivityViewModel(DPR_FPR_LaunchActivityInterface dpr_fpr_launchActivityInterface) {
        this.dpr_fpr_launchActivityInterface = dpr_fpr_launchActivityInterface;
    }

    public void onTypeCheckChanged(final RadioGroup group, int checkedId) {

    }
}
