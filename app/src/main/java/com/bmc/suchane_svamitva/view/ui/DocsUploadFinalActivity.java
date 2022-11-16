package com.bmc.suchane_svamitva.view.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.databinding.ActivityDocsUploadFinalBinding;
import com.bmc.suchane_svamitva.view.callbacks.DocsUploadFinalCallback;
import com.bmc.suchane_svamitva.view.interfaces.DocsUploadFinalInterface;
import com.bmc.suchane_svamitva.view_model.DocsUploadFinalViewModel;

import java.util.Objects;

public class DocsUploadFinalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDocsUploadFinalBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_docs_upload_final);
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        DocsUploadFinalInterface docsUploadFinalInterface = new DocsUploadFinalCallback(this);
        DocsUploadFinalViewModel viewModel = new DocsUploadFinalViewModel(docsUploadFinalInterface);
        binding.setViewModel(viewModel);
    }
}
