package com.bmc.suchane_svamitva.view.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.databinding.ActivityClickDocsBinding;
import com.bmc.suchane_svamitva.utils.Constant;
import com.bmc.suchane_svamitva.view.callbacks.ClickDocumentCallback;
import com.bmc.suchane_svamitva.view.callbacks.DocsUploadFinalCallback;
import com.bmc.suchane_svamitva.view.interfaces.ClickDocumentInterface;
import com.bmc.suchane_svamitva.view.interfaces.DocsUploadFinalInterface;
import com.bmc.suchane_svamitva.view_model.ClickDocumentViewModel;
import com.bmc.suchane_svamitva.view_model.DocsUploadFinalViewModel;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.Objects;

public class ClickDocumentActivity extends AppCompatActivity {

    ClickDocumentViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityClickDocsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_click_docs);
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ClickDocumentInterface clickDocumentInterface = new ClickDocumentCallback(this);
        viewModel = new ClickDocumentViewModel(clickDocumentInterface);
        binding.setViewModel(viewModel);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.CAMERA_REQUEST_PropertyOrLand) {
            if (resultCode == RESULT_OK) {
                viewModel.ProcessDocsImage();
            }
        } else if (requestCode == UCrop.REQUEST_CROP){
            if (resultCode == RESULT_OK){
                String filePath = UCrop.getOutput(data).getPath();
                File f = new File(filePath);
                if (f.length()/1024 <300)//Bytes to kb
                    viewModel.handleUCropResult(data);
                else {
                    viewModel.ProcessDocsImage();
                    Toast.makeText(this, "Image size is too large. Please resize the image.", Toast.LENGTH_SHORT).show();
                }

            } else {
                viewModel.setResultCancelled();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
