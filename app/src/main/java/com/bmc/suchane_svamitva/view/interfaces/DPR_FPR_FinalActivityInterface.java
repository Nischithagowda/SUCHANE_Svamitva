package com.bmc.suchane_svamitva.view.interfaces;

import com.bmc.suchane_svamitva.view_model.DPR_FPR_FinalActivityViewModel;

public interface DPR_FPR_FinalActivityInterface {
    void getUserDistrict(DPR_FPR_FinalActivityViewModel viewModel);
    void getUserTaluk(DPR_FPR_FinalActivityViewModel viewModel);
    void getHobli(DPR_FPR_FinalActivityViewModel viewModel);
    void getVillage(DPR_FPR_FinalActivityViewModel viewModel);
    void capturePropertyOrLandPhoto(DPR_FPR_FinalActivityViewModel viewModel);
    void captureServingDPRPhoto(DPR_FPR_FinalActivityViewModel viewModel);
    void imageProcessPropertyOrLand(DPR_FPR_FinalActivityViewModel viewModel);
    void imageProcessServingDPR(DPR_FPR_FinalActivityViewModel viewModel);
    void loadData(DPR_FPR_FinalActivityViewModel viewModel);
    void showImagePropertyOrLand(DPR_FPR_FinalActivityViewModel viewModel);
    void showImageServingDPR(DPR_FPR_FinalActivityViewModel viewModel);
    void saveAndNext(DPR_FPR_FinalActivityViewModel viewModel);
    void goHome();
}
