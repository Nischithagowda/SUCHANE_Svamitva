package com.bmc.suchane_svamitva.view.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.databinding.ActivityClickDocsBinding;
import com.bmc.suchane_svamitva.view.callbacks.ClickDocumentCallback;
import com.bmc.suchane_svamitva.view.callbacks.DocsUploadFinalCallback;
import com.bmc.suchane_svamitva.view.interfaces.ClickDocumentInterface;
import com.bmc.suchane_svamitva.view.interfaces.DocsUploadFinalInterface;
import com.bmc.suchane_svamitva.view_model.ClickDocumentViewModel;
import com.bmc.suchane_svamitva.view_model.DocsUploadFinalViewModel;

import java.util.Objects;

public class ClickDocumentActivity extends AppCompatActivity {

    ClickDocumentViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityClickDocsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_docs_upload_final);
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ClickDocumentInterface clickDocumentInterface = new ClickDocumentCallback(this);
        viewModel = new ClickDocumentViewModel(clickDocumentInterface);
        binding.setViewModel(viewModel);
    }
}
