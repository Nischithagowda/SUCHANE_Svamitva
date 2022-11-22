package com.bmc.suchane_svamitva.view.interfaces;

import android.content.Intent;
import android.net.Uri;

import com.bmc.suchane_svamitva.view_model.ClickDocumentViewModel;

public interface ClickDocumentInterface {
    void loadData(ClickDocumentViewModel viewModel);
    void captureDocumentPhoto(ClickDocumentViewModel viewModel);
    void onClickSetDocsName(ClickDocumentViewModel viewModel);
    void onClickCancel();
    void onClickCreatePDF(ClickDocumentViewModel viewModel);
    void ProcessDocsImage(ClickDocumentViewModel viewModel);
    void handleUCropResult(Intent data, ClickDocumentViewModel viewModel);
    void setResultCancelled();
}
