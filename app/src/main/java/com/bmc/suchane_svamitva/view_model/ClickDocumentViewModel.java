package com.bmc.suchane_svamitva.view_model;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableList;

import com.bmc.suchane_svamitva.model.ImageTempTbl;
import com.bmc.suchane_svamitva.view.adapter.ImageListAdapter;
import com.bmc.suchane_svamitva.view.interfaces.ClickDocumentInterface;
import com.bmc.suchane_svamitva.view.interfaces.ImageListInterface;

import java.util.ArrayList;
import java.util.List;

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
    public final ObservableField<String> docsName = new ObservableField<>("");
    public final ObservableInt ImageCapturedCount = new ObservableInt(0);
    public final ObservableField<ImageListAdapter> imageListAdapter = new ObservableField<>();
    public final ObservableList<ImageTempTbl> imageTempTblList = new ObservableArrayList<>();
    public ObservableBoolean canCreatePDF = new ObservableBoolean(false);
    public ObservableBoolean isCheckBoxChecked = new ObservableBoolean(false);
    public List<ImageTempTbl> SelectedImagelist = new ArrayList<>();

    public ClickDocumentViewModel(ClickDocumentInterface clickDocumentInterface) {
        this.clickDocumentInterface = clickDocumentInterface;
        this.clickDocumentInterface.loadData(this);
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

    public void onClickCapturePhoto(View view) {
        clickDocumentInterface.captureDocumentPhoto(this);
    }

    public void onClickGeneratePDF(View view) {
        if (this.SelectedImagelist.size()>0) {
            clickDocumentInterface.onClickCreatePDF(this, this.SelectedImagelist);
        } else {
            Toast.makeText(view.getContext(), "Please Select any photo to convert into PDF", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickDeletePhoto(View view) {
        if (this.SelectedImagelist.size()>0) {
            clickDocumentInterface.onClickDeletePhoto(this, this.SelectedImagelist);
        } else {
            Toast.makeText(view.getContext(), "Please Select the photo you want to delete", Toast.LENGTH_SHORT).show();
        }
    }

    public void onCheckedChangeListener(ImageTempTbl dataVariable, int position, boolean isChecked) {
        if (this.SelectedImagelist.contains(dataVariable))
            this.SelectedImagelist.remove(dataVariable);
        else
            this.SelectedImagelist.add(dataVariable);
        this.isCheckBoxChecked.set(isChecked);
        Log.d("onImageChecked", "position: "+position);
        Log.d("onImageChecked", "isCheckBoxChecked: "+this.isCheckBoxChecked.get());
        Log.d("onImageChecked", "list.Size: "+this.SelectedImagelist.size());
    }
}
