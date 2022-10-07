package com.bmc.suchane_svamitva.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.bmc.suchane_svamitva.R;
import com.bmc.suchane_svamitva.databinding.ActivityNoticeBinding;
import com.bmc.suchane_svamitva.model.UserLatLon;
import com.bmc.suchane_svamitva.utils.Constant;
import com.bmc.suchane_svamitva.view.callbacks.NoticeActivityCallback;
import com.bmc.suchane_svamitva.view.interfaces.NoticeActivityInterface;
import com.bmc.suchane_svamitva.view_model.NoticeActivityViewModel;

import java.util.Objects;

public class NoticeActivity extends AppCompatActivity {
    NoticeActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityNoticeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_notice);
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        NoticeActivityInterface noticeActivityCallback = new NoticeActivityCallback(this);
        viewModel = new NoticeActivityViewModel(noticeActivityCallback);
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
