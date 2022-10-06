package com.bmc.suchane_svamitva.view.interfaces;

import com.bmc.suchane_svamitva.view_model.NoticeActivityViewModel;

public interface NoticeActivityInterface {
    void getUserDistrict(NoticeActivityViewModel viewModel);
    void getUserTaluk(NoticeActivityViewModel viewModel);
    void getHobli(NoticeActivityViewModel viewModel);
    void getVillage(NoticeActivityViewModel viewModel);
}
