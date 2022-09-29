package com.bmc.suchane_svamitva.view.ui;
//
// Created by Nischitha on 23-09-2022.
// Copyright (c) 2022 BMC. All rights reserved.
//

import android.os.Bundle;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.databinding.ActivitySelectActionBinding;
import com.bmc.suchane_svamitva.databinding.ActivitySignInBinding;
import com.bmc.suchane_svamitva.view.callbacks.SelectActivityCallback;
import com.bmc.suchane_svamitva.view.callbacks.SignInCallback;
import com.bmc.suchane_svamitva.view.interfaces.SelectActivityInterface;
import com.bmc.suchane_svamitva.view.interfaces.SignInInterface;
import com.bmc.suchane_svamitva.view_model.SelectActivityViewModel;
import com.bmc.suchane_svamitva.view_model.SignInViewModel;

import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

public class SelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySelectActionBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_select_action);
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        SelectActivityInterface selectActivityInterface = new SelectActivityCallback(this);
        SelectActivityViewModel viewModel = new SelectActivityViewModel(selectActivityInterface);
        binding.setViewModel(viewModel);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
