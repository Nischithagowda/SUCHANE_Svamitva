package com.bmc.suchane_svamitva.view.interfaces;

import com.bmc.suchane_svamitva.model.OwnerTbl;
import com.bmc.suchane_svamitva.view_model.DPR_FPR_LaunchActivityViewModel;

public interface DPR_FPR_LaunchActivityInterface {
    void loadOwnerList(DPR_FPR_LaunchActivityViewModel viewModel);
    void onNavigateToDPR_FPR_Final(DPR_FPR_LaunchActivityViewModel viewModel, OwnerTbl ownerTbl, String NoticeNo);
    void onTypeCheckChanged(int checkedId);
    void loadDefaultFragment();
}
