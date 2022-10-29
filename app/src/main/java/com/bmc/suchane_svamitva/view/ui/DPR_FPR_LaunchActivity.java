package com.bmc.suchane_svamitva.view.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.databinding.ActivityDprFprLaunchBinding;
import com.bmc.suchane_svamitva.view.callbacks.DPR_FPR_LaunchActivityCallback;
import com.bmc.suchane_svamitva.view.interfaces.DPR_FPR_LaunchActivityInterface;
import com.bmc.suchane_svamitva.view_model.DPR_FPR_LaunchActivityViewModel;

public class DPR_FPR_LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDprFprLaunchBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_dpr_fpr_launch);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//
        DPR_FPR_LaunchActivityInterface dpr_fpr_launchActivityInterface = new DPR_FPR_LaunchActivityCallback(this);
        DPR_FPR_LaunchActivityViewModel viewModel = new DPR_FPR_LaunchActivityViewModel(dpr_fpr_launchActivityInterface);
        dpr_fpr_launchActivityInterface.loadDefaultFragment();
        binding.setViewModel(viewModel);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.dpr_fpr_frame_layout);
        assert fragment != null;
        fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}