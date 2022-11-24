package com.bmc.suchane_svamitva.view.interfaces;

import android.content.Intent;
import android.net.Uri;

import com.bmc.suchane_svamitva.model.ImageTempTbl;
import com.bmc.suchane_svamitva.view_model.ClickDocumentViewModel;

import java.util.List;

public interface ClickDocumentInterface {
    void loadData(ClickDocumentViewModel viewModel);
    void captureDocumentPhoto(ClickDocumentViewModel viewModel);
    void onClickCreatePDF(ClickDocumentViewModel viewModel, List<ImageTempTbl> SelectedImagelist);
    void onClickDeletePhoto(ClickDocumentViewModel viewModel, List<ImageTempTbl> SelectedImagelist);
    void ProcessDocsImage(ClickDocumentViewModel viewModel);
    void handleUCropResult(Intent data, ClickDocumentViewModel viewModel);
    void setResultCancelled();
}
