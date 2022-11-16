package com.bmc.suchane_svamitva.view.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.databinding.ActivityDocsUploadLaunchBinding;
import com.bmc.suchane_svamitva.view.callbacks.DocsUploadLaunchCallback;
import com.bmc.suchane_svamitva.view.interfaces.DocsUploadLaunchInterface;
import com.bmc.suchane_svamitva.view_model.DocsUploadLaunchViewModel;

import java.util.Objects;

public class DocsUploadLaunchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDocsUploadLaunchBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_docs_upload_launch);
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        DocsUploadLaunchInterface docsUploadLaunchInterface = new DocsUploadLaunchCallback(this);
        DocsUploadLaunchViewModel viewModel = new DocsUploadLaunchViewModel(docsUploadLaunchInterface);
        docsUploadLaunchInterface.loadDefaultFragment();
        binding.setViewModel(viewModel);
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
