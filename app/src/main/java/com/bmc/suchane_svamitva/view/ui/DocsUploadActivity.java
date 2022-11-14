package com.bmc.suchane_svamitva.view.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.databinding.ActivityDocsUploadBinding;
import com.bmc.suchane_svamitva.databinding.ActivitySignInBinding;
import com.bmc.suchane_svamitva.view.callbacks.DocsUploadCallback;
import com.bmc.suchane_svamitva.view.callbacks.SignInCallback;
import com.bmc.suchane_svamitva.view.interfaces.DocsUploadInterface;
import com.bmc.suchane_svamitva.view.interfaces.SignInInterface;
import com.bmc.suchane_svamitva.view_model.DocsUploadViewModel;
import com.bmc.suchane_svamitva.view_model.SignInViewModel;

import java.util.Objects;

public class DocsUploadActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDocsUploadBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_docs_upload);
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        DocsUploadInterface docsUploadInterface = new DocsUploadCallback(this);
        DocsUploadViewModel viewModel = new DocsUploadViewModel(docsUploadInterface);
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
