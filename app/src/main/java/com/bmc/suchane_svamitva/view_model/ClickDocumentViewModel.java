package com.bmc.suchane_svamitva.view_model;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableList;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.model.ImageTempTbl;
import com.bmc.suchane_svamitva.model.OwnerTbl;
import com.bmc.suchane_svamitva.view.adapter.ImageListAdapter;
import com.bmc.suchane_svamitva.view.adapter.OwnerDocsUploadAdapterCompleted;
import com.bmc.suchane_svamitva.view.adapter.OwnerDocsUploadAdapterPending;
import com.bmc.suchane_svamitva.view.interfaces.ClickDocumentInterface;
import com.bmc.suchane_svamitva.view.interfaces.ImageListInterface;

public class ClickDocumentViewModel implements ImageListInterface {
    ClickDocumentInterface clickDocumentInterface;
    public final ObservableField<String> districtCode = new ObservableField<>();
    public final ObservableField<String> districtName = new ObservableField<>("");
    public final ObservableField<String> talukCode = new ObservableField<>("");
    public final ObservableField<String> talukName = new ObservableField<>("");
    public final ObservableField<String> hobliName = new ObservableField<>("");
    public final ObservableField<String> hobliCode = new ObservableField<>("");
    public final ObservableField<String> villageName = new ObservableField<>("");
    public final ObservableField<String> villageCode = new ObservableField<>("");
    public final ObservableField<String> LGD_VILLAGE_CODE = new ObservableField<>();
    public final ObservableField<String> propertyNo = new ObservableField<>("");
    public final ObservableField<String> noticeNumber = new ObservableField<>("");
    public ObservableField<String> mDocsImagePath = new ObservableField<>();
    public final ObservableField<String> docsNameError = new ObservableField<>();
    public final ObservableField<String> docsName = new ObservableField<>("");
    public final ObservableInt ImageCapturedCount = new ObservableInt(0);
    public final ObservableField<ImageListAdapter> imageListAdapter = new ObservableField<>();
    public final ObservableList<ImageTempTbl> imageTempTblList = new ObservableArrayList<>();
    public ObservableBoolean canCreatePDF = new ObservableBoolean(false);

    public ClickDocumentViewModel(ClickDocumentInterface clickDocumentInterface) {
        this.clickDocumentInterface = clickDocumentInterface;
        imageListAdapter.set(new ImageListAdapter(this));
    }

    public void ProcessDocsImage(){
        clickDocumentInterface.ProcessDocsImage(this);
    }

    public void handleUCropResult(Intent data){
        clickDocumentInterface.handleUCropResult(data, this);
    }

    public void setResultCancelled() {
        clickDocumentInterface.setResultCancelled();
    }

    public void onContactDocsNameTextChanged(CharSequence s, int start, int before, int count) {
        docsName.set(s.toString());
        docsNameError.set(null);
    }

    public void onClickSetDocsName(View view) {
        if (TextUtils.isEmpty(this.docsName.get())){
            docsNameError.set(view.getContext().getString(R.string.enter_name_of_the_document));
        } else {
            clickDocumentInterface.
                    onClickSetDocsName(this);
        }
    }

    public void onClickCancel(View view) {
        clickDocumentInterface.onClickCancel();
    }

    public void onClickCapturePhoto(View view) {
        clickDocumentInterface.captureDocumentPhoto(this);
    }

    public void onClickGeneratePDF(View view) {
        clickDocumentInterface.onClickCreatePDF(this);
    }
}
