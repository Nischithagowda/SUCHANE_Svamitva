package com.bmc.suchane_svamitva.view.interfaces;

import com.bmc.suchane_svamitva.view_model.DPR_FPR_FinalActivityViewModel;
import com.bmc.suchane_svamitva.view_model.NoticeActivityViewModel;

public interface DPR_FPR_FinalActivityInterface {
    void getUserDistrict(DPR_FPR_FinalActivityViewModel viewModel);
    void getUserTaluk(DPR_FPR_FinalActivityViewModel viewModel);
    void getHobli(DPR_FPR_FinalActivityViewModel viewModel);
    void getVillage(DPR_FPR_FinalActivityViewModel viewModel);
    void capturePropertyOrLandPhoto(DPR_FPR_FinalActivityViewModel viewModel);
    void captureServingNoticePhoto(DPR_FPR_FinalActivityViewModel viewModel);
    void imageProcessPropertyOrLand(DPR_FPR_FinalActivityViewModel viewModel);
    void imageProcessServingNotice(DPR_FPR_FinalActivityViewModel viewModel);
    void loadData(DPR_FPR_FinalActivityViewModel viewModel);
    void showImagePropertyOrLand(DPR_FPR_FinalActivityViewModel viewModel);
    void showImageServingNotice(DPR_FPR_FinalActivityViewModel viewModel);
}
