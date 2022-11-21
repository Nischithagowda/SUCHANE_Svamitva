package com.bmc.suchane_svamitva.view.interfaces;

import android.content.Intent;
import android.net.Uri;

import com.bmc.suchane_svamitva.view_model.DocsUploadFinalViewModel;

public interface DocsUploadFinalInterface {
    void getUserDistrict(DocsUploadFinalViewModel viewModel);
    void getUserTaluk(DocsUploadFinalViewModel viewModel);
    void getHobli(DocsUploadFinalViewModel viewModel);
    void getVillage(DocsUploadFinalViewModel viewModel);
    void capturePropertyOrLandPhoto(DocsUploadFinalViewModel viewModel);
    void onClickAddDocument(DocsUploadFinalViewModel viewModel);
    void captureServingDPRPhoto(DocsUploadFinalViewModel viewModel);
    void imageProcessPropertyOrLand(DocsUploadFinalViewModel viewModel);
    void imageProcessServingDPR(DocsUploadFinalViewModel viewModel);
    void loadData(DocsUploadFinalViewModel viewModel);
    void showImagePropertyOrLand(DocsUploadFinalViewModel viewModel);
    void showImageServingDPR(DocsUploadFinalViewModel viewModel);
    void saveAndNext(DocsUploadFinalViewModel viewModel);
    void goHome();
    Uri handleUCropResult(Intent data, DocsUploadFinalViewModel viewModel);
}
