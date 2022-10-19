package com.bmc.suchane_svamitva.view.interfaces;

import android.view.View;

import com.bmc.suchane_svamitva.view_model.NoticeActivityViewModel;

public interface NoticeActivityInterface {
    void getUserDistrict(NoticeActivityViewModel viewModel);
    void getUserTaluk(NoticeActivityViewModel viewModel);
    void getHobli(NoticeActivityViewModel viewModel);
    void getVillage(NoticeActivityViewModel viewModel);
    void loadData(NoticeActivityViewModel viewModel);
    void capturePropertyOrLandPhoto(NoticeActivityViewModel viewModel);
    void captureServingNoticePhoto(NoticeActivityViewModel viewModel);
    void imageProcessPropertyOrLand(NoticeActivityViewModel viewModel);
    void imageProcessServingNotice(NoticeActivityViewModel viewModel);
    void saveAndNext(NoticeActivityViewModel viewModel);
    void goHome();
    void showImagePropertyOrLand(NoticeActivityViewModel viewModel);
    void showImageServingNotice(NoticeActivityViewModel viewModel);
    void sendOtp_Public(NoticeActivityViewModel viewModel);
    void verifyOtpWithServer(NoticeActivityViewModel viewModel);
    void onClickCancel();
}
