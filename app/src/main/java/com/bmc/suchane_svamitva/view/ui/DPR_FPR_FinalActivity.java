package com.bmc.suchane_svamitva.view.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.databinding.ActivityDprFprFinalBinding;
import com.bmc.suchane_svamitva.utils.Constant;
import com.bmc.suchane_svamitva.view.callbacks.DPR_FPR_FinalActivityCallback;
import com.bmc.suchane_svamitva.view.interfaces.DPR_FPR_FinalActivityInterface;
import com.bmc.suchane_svamitva.view_model.DPR_FPR_FinalActivityViewModel;

import java.util.Objects;

public class DPR_FPR_FinalActivity extends AppCompatActivity {

    DPR_FPR_FinalActivityViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDprFprFinalBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_dpr_fpr_final);
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        DPR_FPR_FinalActivityInterface dpr_fpr_finalActivityInterface = new DPR_FPR_FinalActivityCallback(this);
        viewModel = new DPR_FPR_FinalActivityViewModel(dpr_fpr_finalActivityInterface);
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
                viewModel.processImageServingNotice();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
