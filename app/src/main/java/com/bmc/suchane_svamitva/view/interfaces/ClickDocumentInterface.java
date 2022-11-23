package com.bmc.suchane_svamitva.view.interfaces;

import android.content.Intent;
import android.net.Uri;

import com.bmc.suchane_svamitva.view_model.ClickDocumentViewModel;

import java.util.List;

public interface ClickDocumentInterface {
    void loadData(ClickDocumentViewModel viewModel);
    void captureDocumentPhoto(ClickDocumentViewModel viewModel);
    void onClickCreatePDF(ClickDocumentViewModel viewModel);
    void onClickDeletePhoto(ClickDocumentViewModel viewModel, List<Integer> DocumentID);
    void ProcessDocsImage(ClickDocumentViewModel viewModel);
    void handleUCropResult(Intent data, ClickDocumentViewModel viewModel);
    void setResultCancelled();
}
