package com.bmc.suchane_svamitva.view.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.databinding.ActivityDocsUploadFinalBinding;
import com.bmc.suchane_svamitva.utils.Constant;
import com.bmc.suchane_svamitva.view.callbacks.DocsUploadFinalCallback;
import com.bmc.suchane_svamitva.view.interfaces.DocsUploadFinalInterface;
import com.bmc.suchane_svamitva.view_model.DocsUploadFinalViewModel;
import com.yalantis.ucrop.UCrop;

import java.util.Objects;

public class DocsUploadFinalActivity extends AppCompatActivity {
    DocsUploadFinalViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDocsUploadFinalBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_docs_upload_final);
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        DocsUploadFinalInterface docsUploadFinalInterface = new DocsUploadFinalCallback(this);
        viewModel = new DocsUploadFinalViewModel(docsUploadFinalInterface);
        binding.setViewModel(viewModel);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.CAMERA_REQUEST_PropertyOrLand) {
            if (resultCode == RESULT_OK) {
                viewModel.processImagePropertyOrLand();
            }
        } else if (requestCode == Constant.CAMERA_REQUEST_ServingNotice) {
            if (resultCode == RESULT_OK) {
                viewModel.processImageServingDPR();
            }
        } else if (requestCode == UCrop.REQUEST_CROP){
            if (resultCode == RESULT_OK){
                viewModel.handleUCropResult(data);
            } else {
                //viewModel.setResultCancelled();
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
